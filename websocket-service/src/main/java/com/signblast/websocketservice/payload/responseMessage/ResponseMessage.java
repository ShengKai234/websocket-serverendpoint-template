package com.signblast.websocketservice.payload.responseMessage;

import lombok.Data;

@Data
public class ResponseMessage {
  String message;

  public ResponseMessage(String message) {
    this.message = message;
  }
}
