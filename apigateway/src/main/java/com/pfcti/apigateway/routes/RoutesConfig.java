package com.pfcti.apigateway.routes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Configuration
public class RoutesConfig {
    /**
     * Control rate limit by request
     * @return
     */
    @Bean
    public KeyResolver keyResolver () {
        return exchange -> Mono.just(Objects.requireNonNull(
                exchange
                        .getRequest().
                        getRemoteAddress())
                .getAddress()
                .getHostAddress()
        );
    }

    /**
     * The next commented code is based on solution of router or mapping routes by code
     */

//    @Value("${uri.api.clients}")
//    private String clientUri;

//    @Bean
//    public RouteLocator clientsRouteLocator (RouteLocatorBuilder routeLocatorBuilder) {
//        return routeLocatorBuilder
//                .routes()
//                .route("allClients", r -> (Buildable<Route>) this.builderPath(r, "/v1/api/client/all", HttpMethod.GET))
//                .route("clientById", r -> (Buildable<Route>) this.builderPath(r, "/v1/api/client/{id}", HttpMethod.GET))
//                .route("clientByAnotherPath",
//                        r -> r.path("/pfcti/v1/api/clients")
//                                .filters(fl -> fl.rewritePath("/pfcti/v1/api/clients", "/v1/api/client/all"))
//                                .uri(clientUri)
//                )
//                .build();
//    }

//    private Buildable<Route> builderPath (
//            PredicateSpec spec,
//            String route,
//            HttpMethod httpMethod
//    ) {
//       return (Buildable<Route>) spec
//               .path(route)
//               .and()
//               .method(httpMethod)
//               .filters(fl -> fl
//                   .addRequestHeader("X-GATEWAY-REQUEST-HEADER", "GATEWAY_REQUEST")
//                   .addResponseHeader("X-GATEWAY-RESPONSE-HEADER", "GATEWAY_RESPONSE")
//               )
//               .uri(clientUri);
//    }


}
