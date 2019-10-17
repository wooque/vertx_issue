package com.example.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;

public class MainVerticle extends AbstractVerticle {
  @Override
  public void start() {
    HttpClientOptions httpOpts = new HttpClientOptions().setSsl(true);
    HttpClient client = vertx.createHttpClient(httpOpts);

    client.webSocket(443, "echo.websocket.org", "/", res -> {
      if (res.failed()) {
        res.cause().printStackTrace();
        return;
      }
      System.out.println("SUCCESS");
      res.result().handler(System.out::println);
    });
  }
}
