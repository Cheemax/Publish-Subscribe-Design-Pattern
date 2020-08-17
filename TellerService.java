package com.company;

import java.util.*;
import java.util.stream.Collectors;


public class TellerService implements IRequest<SubscriptionRequest>, IService<Tell> {

    Map<Message, Set<TellSubscriber>> messageSubscribers = new HashMap<>();
    Set<Tell> tellers = new HashSet<>();



    public TellerService(){


    }

    private boolean addRecord(Tell record) {
        if(!tellers.contains(record)){
            tellers.add(record);
            return true;
        }
        return false;
    }


    public boolean addRecords(List<Tell> records) {
        return false;
    }


    public boolean removeRecord(Tell record) {
        return false;
    }


    public boolean removeRecords(List<Tell> records) {
        return false;
    }

    @Override
    public boolean addRequest(SubscriptionRequest request) {

        if(messageSubscribers.containsKey(request.getMessage())){
            boolean success =  messageSubscribers.get(request.getMessage()).add(new TellSubscriber(request.getSubscriberName()));
           if(success){
               triggerNewRecordNotification(request.getMessage());
               return true;
           }else{
               return false;
           }
        }else{
            Set<TellSubscriber> tellSubscribers = new HashSet<>();
            tellSubscribers.add(new TellSubscriber(request.getSubscriberName()));
            messageSubscribers.put(request.getMessage(), tellSubscribers);
            triggerNewRecordNotification(request.getMessage());
            return true;
        }

    }

    @Override
    public boolean addRequests(List<SubscriptionRequest> requests) {
        return false;
    }

    @Override
    public boolean removeRequest(SubscriptionRequest request) {
        return false;
    }

    @Override
    public boolean removeRequests(List<SubscriptionRequest> requests) {
        return false;
    }


    @Override
    public boolean addToQueue(Tell record) {
            if(addRecord(record)){
                triggerNewRecordNotification(record);
                return true;
            }
        return false;
    }

    @Override
    public boolean removeFromQueue(Tell record) {

        return removeRecord(record);
    }

    private void triggerNewRecordNotification(Tell record){
        List<Message> messages = getMatchingMessages(record);
        for(Message message: messages){
            Set<TellSubscriber> subscribers = messageSubscribers.get(message);
            subscribers.forEach(sub -> sub.receiveNotification(Arrays.asList(record), message));
        }
    }

    private void triggerNewRecordNotification(Message message){
        List<Tell> tells = getMatchingTells(message);
        if(tells.size() >= 1) {
            Set<TellSubscriber> subscribers = messageSubscribers.get(message);
            subscribers.forEach(sub -> sub.receiveNotification(tells, message));
        }

    }

    private List<Message> getMatchingMessages(Tell record) {
        return messageSubscribers.keySet().stream().filter(m -> {
            boolean match = (m.getTellTitle().equals(null) || m.getTellTitle().trim().equals("") || m.getTellTitle().equals(record.getTitle()) )&&
                    (m.getTellerName().equals(null) || m.getTellerName().trim().equals("") || m.getTellerName().equals(record.getTellerName())) &&
                    (m.getKeyword().equals(null) || m.getKeyword().trim().equals("") || m.getKeyword().equals(record.getKeyword()));
            return match;
        }).collect(Collectors.toList());
    }

    private List<Tell> getMatchingTells(Message message) {

        return tellers.stream().filter(m -> {
            boolean match = (message.getTellTitle().equals(null) || message.getTellTitle().trim().equals("") || message.getTellTitle().equals(m.getTitle())) &&
                    (message.getTellerName().equals(null) || message.getTellerName().trim().equals("") || message.getTellerName().equals(m.getTellerName())) &&
                    (message.getKeyword().equals(null) || message.getKeyword().trim().equals("") || message.getKeyword().equals(m.getKeyword()));
            return match;
        }).collect(Collectors.toList());
    }
}
