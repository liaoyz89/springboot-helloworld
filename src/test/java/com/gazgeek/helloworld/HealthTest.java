package com.gazgeek.helloworld;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
public class HealthTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void checkHealth() {
        getRequest("/health")
            .assertStatusCode(OK)
            .assertResponseBody("{\"groups\":[\"liveness\",\"readiness\"],\"status\":\"UP\"}");
    }

    private HealthResponse getRequest(String uri) {
        return new HealthResponse(restTemplate.getForEntity(getUri(uri), String.class));
    }

    protected URI getUri(String uri) {
        return UriComponentsBuilder
            .newInstance()
            .scheme("http")
            .host("localhost")
            .port(port)
            .path(uri)
            .build()
            .toUri();
    }

    private static class HealthResponse {

        private ResponseEntity<String> responseEntity;

        public HealthResponse(ResponseEntity<String> responseEntity) {
            this.responseEntity = responseEntity;
        }

        public HealthResponse assertStatusCode(HttpStatus expected) {
            assertThat(responseEntity.getStatusCode(), is(expected));
            return this;
        }

        public HealthResponse assertResponseBody(String expected) {
            assertThat(responseEntity.getBody(), is(expected));
            return this;
        }
    }
}
