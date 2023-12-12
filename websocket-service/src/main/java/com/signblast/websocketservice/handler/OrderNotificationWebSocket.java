package com.signblast.websocketservice.handler;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
@ServerEndpoint("/ws/order_notification/{orgId}")
public class OrderNotificationWebSocket {

  static final ConcurrentHashMap<String, List<WebSocketClient>> webSocketClientMap = new ConcurrentHashMap<>();

  /**
   * when connect success
   * @Param session with client connect, use to send msg
   * @Param orgId organization id
   */
  @OnOpen
  public void onOpen(Session session, @PathParam("orgId") String orgId) {
    WebSocketClient client = new WebSocketClient();
    client.setSession(session);
    client.setUri(session.getRequestURI().toString());

    List<WebSocketClient> webSocketClientList = webSocketClientMap.get(orgId);
    if (webSocketClientList == null) {
      webSocketClientList = new ArrayList<>();
    }
    webSocketClientList.add(client);
    webSocketClientMap.put(orgId, webSocketClientList);
  }

  /**
   * when connect close, can not send msg to client
   * @param orgId
   */
  @OnClose
  public void onClose(@PathParam("orgId") String orgId) {
    // TODO check if one client leave, it will close all connection.
    webSocketClientMap.remove(orgId);
  }

  @OnError
  public void onError(Session session, Throwable error) {
    // TODO use logger
    System.err.println("Error");
    error.printStackTrace();
  }

  /**
   * Send message to client by orgId
   * @param orgId
   * @param message
   */
  public static void sendMessage(String orgId, String message) {
    try {
      List<WebSocketClient> webSocketClientList = webSocketClientMap.get(orgId);
      if (webSocketClientList != null) {
        for (WebSocketClient webSocketClient: webSocketClientList) {
          webSocketClient.getSession().getBasicRemote().sendText(message);
        }
      }
    } catch (IOException e) {
      // TODO exception handler
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    }
  }
}
