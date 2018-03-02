package org.baeldung.example.reactiveapi;

import org.apache.commons.lang.RandomStringUtils;
import org.baeldung.example.reactiveapi.Foo;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.UUID;
import java.util.stream.Stream;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class DataHandler {

    /**
     * Http server request handler
     *
     * @param serverRequest Http server request
     * @return Mono<ServerResponse> as stream for Foo objects
     */
    public Mono<ServerResponse> streamFoo(ServerRequest serverRequest) {
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
        Flux<Foo> fooFlux = Flux.fromStream(Stream.generate(() -> new Foo(UUID.randomUUID(), RandomStringUtils.randomAlphabetic(10))));

        return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body( Flux.zip(interval, fooFlux).map(Tuple2::getT2), Foo.class);
    }
}
