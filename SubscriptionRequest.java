package com.company;

public class SubscriptionRequest {

    private String subscriberName;
    private Message message;

    public SubscriptionRequest(String subscriberName, Message message) {
        this.subscriberName = subscriberName;
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

    public String getSubscriberName() {
        return subscriberName;
    }



}
