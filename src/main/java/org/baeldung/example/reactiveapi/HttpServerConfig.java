package org.baeldung.example.reactiveapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.ipc.netty.NettyContext;
import reactor.ipc.netty.http.server.HttpServer;

@Configuration
@EnableWebFlux
public class HttpServerConfig {

    @Value("${netty.server.port}")
    private int port;

    @Value("${netty.bind.address}")
    private String bindAddress;

    /**
     * Configure reactive embedded server
     *
     * @param handler       http web handler
     * @return NettyContext
     */
    @Bean
    public NettyContext nettyContext(HttpHandler handler) {
        ReactorHttpHandlerAdapter reactorHttpHandlerAdapter = new ReactorHttpHandlerAdapter(handler);
        HttpServer httpServer = HttpServer.create("localhost", port);
        return httpServer.newHandler(reactorHttpHandlerAdapter).block();
    }
}
