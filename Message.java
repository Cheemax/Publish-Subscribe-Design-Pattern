package com.company;

import java.util.Objects;

public class Message {
    private String tellTitle;
    private String tellerName;
    private String keyword;

    @Override
    public String toString() {

        return String.format("Message{%n\ttitle = %s,%n\tteller name = %s%n\tkeyword = %s%n}", tellTitle, tellerName, keyword);
    }
    public Message(String tellTitle, String tellerName, String keyword) {
        this.tellTitle = tellTitle;
        this.tellerName = tellerName;
        this.keyword = keyword;
    }

    public String getTellTitle() {
        return tellTitle;
    }

    public String getTellerName() {
        return tellerName;
    }

    public String getKeyword() {
        return keyword;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tellTitle, tellerName, keyword);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;

        if(obj == null || obj.getClass() != getClass())
            return false;

        Message message = (Message)obj;
        return Objects.equals(tellTitle, message.tellTitle) &&
        Objects.equals(tellerName,message.tellerName) &&
                Objects.equals(keyword,message.keyword);
    }
}
