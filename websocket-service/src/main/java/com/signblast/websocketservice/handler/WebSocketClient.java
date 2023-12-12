package com.signblast.websocketservice.handler;

import jakarta.websocket.Session;
import lombok.Data;


/**
 * @author Kai
 * @description WebSocket client connect
 * @date 2023-12-12
 */
@Data
public class WebSocketClient {
  private Session session;
  private String uri;
}
