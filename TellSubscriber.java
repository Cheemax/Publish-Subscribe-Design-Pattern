package com.company;

import java.util.List;
import java.util.Objects;

public class TellSubscriber {
    private String name;
    public TellSubscriber(String name){
        this.name = name;
    }

    public void receiveNotification(List<Tell> tell, Message message){

            System.out.println("\nThe Tell record(s)\n" + tell + "\nis now available for subscriber " + name + " subscribed to based on the subscription request \n" + message + "\n");


    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;

        if(obj == null || obj.getClass() != getClass())
            return false;

        TellSubscriber subscriber = (TellSubscriber)obj;
        return Objects.equals(name,subscriber.name);
    }
}
