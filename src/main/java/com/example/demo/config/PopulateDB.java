package com.example.demo.config;

import com.example.demo.model.Cart;
import com.example.demo.repository.CartRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class PopulateDB implements ApplicationRunner {
    Logger log = LoggerFactory.getLogger(PopulateDB.class);
    Random r = new Random();

    @Autowired
    private CartRepository cartRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.debug("Populating the database ... In Progress");
        List<Cart> students = new ArrayList<>();

        cartRepository.truncate()
                .thenMany(pushCartEntries())
                .doOnComplete(() -> {
                    log.info("Database filled up!");
                })
                .subscribe();
    }

    private Flux<Cart> pushCartEntries() {
        List<Cart> cartList = new ArrayList<>();

        for(int i=1;i<=100;i++){
            for(int j=1;j<=10;j++){
                Cart cart = new Cart(i, j, 1, "itemName: item" + i);
                cartList.add(cart);
            }
        }

        return cartRepository.saveAll(cartList);
    }

    private int genItemId() {
        return r.nextInt(10) + 1;
    }

    private int genCartId() {
        return r.nextInt(100) + 1;
    }
}