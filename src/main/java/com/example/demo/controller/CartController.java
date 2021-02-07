package com.example.demo.controller;


import com.example.demo.model.Cart;
import com.example.demo.repository.CartRepository;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    Logger log = LoggerFactory.getLogger(CartController.class);
    private final String validateCartResponseTopic = "validate_cart_response_topic";
    private final String validateCartTopic = "validate_cart_topic";
    private final String validateCartTopic_Key = "validate_cart_topic_key";

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private Gson jsonConverter;

    @GetMapping("/fetchAll")
    public Flux<Cart> fetchAll() {
        log.info("fetchAll");
        return cartRepository.findAll();
    }

    @GetMapping("/fetchByCartId/{cartid}")
    public Flux<Cart> fetchByCartId(@PathVariable int cartid) {
        log.info("fetchByCartId, cartid: {}", cartid);

        return cartRepository.fetchByCartId(cartid);
    }

    @GetMapping("/fetchByCartIdItemId/{cartid}/{itemid}")
    public Mono<Cart> fetchByCartIdItemId(@PathVariable int cartid,
                                          @PathVariable int itemid) {
        log.info("fetchByCartIdItemId, cartid: {}, itemid: {}", cartid, itemid);

        return cartRepository.fetchByCartIdItemId(cartid, itemid)
                .doOnNext(cart -> {
                    log.info(cart.toString());
                });
    }

//    @GetMapping("/update/{cartid}/{itemid}/{quantity}")
//    public Mono<Boolean> updateCart(@PathVariable int cartid,
//                                    @PathVariable int itemid,
//                                    @PathVariable int quantity) {
//        log.info("updateCart with cartid: {}, itemid: {}, quantity: {}", cartid, itemid, quantity);
//
//        return cartRepository.updateCart(cartid, itemid, quantity);
//    }

//    @KafkaListener(topics = validateCartResponseTopic)
//    public void validatedCartResponse(String stringifiedEvent) {
//        log.info("KafkaListener from validateCartResponseTopic: {}", stringifiedEvent);
//        CartItemValidationResponseEvent cartItemValidationResponseEvent = (CartItemValidationResponseEvent)
//                jsonConverter.fromJson(stringifiedEvent, CartItemValidationResponseEvent.class);
//
//        cartService.updateCartItemQuantity(cartItemValidationResponseEvent);
//    }

}
