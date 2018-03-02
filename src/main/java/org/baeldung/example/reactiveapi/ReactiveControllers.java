package org.baeldung.example.reactiveapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@EnableWebFlux
public class ReactiveControllers {

    @Autowired
    private DataHandler dataHandler;

    /**
     * Http request routing
     *
     * @return RouterFunction
     */
    @Bean
    public RouterFunction<ServerResponse> routes() {
        return route(GET ("/foo"), dataHandler::streamFoo);
    }
}
