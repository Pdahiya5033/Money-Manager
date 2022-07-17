package com.example.moneymanager;

public class ChatsModel {
    private String msg;
    private String sender;
    public ChatsModel(String msg, String sender) {
        this.msg = msg;
        this.sender = sender;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }



}
