package edu.kmaooad.models;

import lombok.Data;

@Data
public class Response {

    private boolean isOk;
    private Long messageId;
    private String errorMessage;

    /**
    Error constructor
     */
    public Response(String errorMessage){
      this.isOk=false;
      this.errorMessage = errorMessage;
    }
    /**
    Success constructor
     */
    public Response(Long messageId){
      this.isOk=true;
      this.messageId = messageId;
    }

    public String getResponse(){
        if(isOk) return messageId.toString();
        else return errorMessage;
    }

}
