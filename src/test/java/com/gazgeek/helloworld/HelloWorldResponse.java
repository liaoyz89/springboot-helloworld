package com.gazgeek.helloworld;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;

public class HelloWorldResponse {

    private ResponseEntity<String> responseEntity;

    public HelloWorldResponse(ResponseEntity<String> entity) {
        this.responseEntity = entity;
    }

    public HelloWorldResponse assertStatusCode(HttpStatus expected) {
        assertThat(responseEntity.getStatusCode(), is(expected));
        return this;
    }

    public HelloWorldResponse assertResponseBody(String expected) {
        assertThat(responseEntity.getBody(), is(expected));
        return this;
    }

    public HelloWorldResponse assertResponseBodyStartsWith(String prefix) {
        assertThat(responseEntity.getBody(), startsWith(prefix));
        return this;
    }
}
