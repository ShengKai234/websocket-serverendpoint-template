package com.signblast.websocketservice.controller;

import com.signblast.websocketservice.handler.OrderNotificationWebSocket;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class WebSocketMessageController {

  @GetMapping
  public void test(@RequestParam String orgId) {
    System.err.println("send new order");
    OrderNotificationWebSocket.sendMessage(orgId, "new Order !!!");
  }
}
