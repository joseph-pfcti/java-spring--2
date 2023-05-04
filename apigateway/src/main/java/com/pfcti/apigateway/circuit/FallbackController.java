package com.pfcti.apigateway.circuit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {
    @GetMapping("/service-fallback")
    public Mono<String> fallbackService () {
        return Mono.just("The service is unavailable");
    }
}
