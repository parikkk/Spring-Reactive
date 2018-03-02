package org.baeldung.example.reactiveapi;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReactiveApiApplicationTests {

    @Autowired
    ReactiveControllers reactiveControllers;

    private WebTestClient webTestClient;

    @Before
    public void setUp() throws Exception {
        this.webTestClient = WebTestClient.bindToRouterFunction(reactiveControllers.routes()).build();
    }

    @After
    public void tearDown() throws Exception {
        this.reactiveControllers = null;
        this.webTestClient = null;
    }

    @Test
    public void fooEndpointTest() {
        webTestClient.get().uri("/foo").exchange()
                .expectStatus().isOk().expectHeader().contentType(MediaType.APPLICATION_STREAM_JSON);
    }

}
