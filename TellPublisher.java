package com.company;

public class TellPublisher implements IPublisher<Tell> {

    @Override
    public boolean publish(Tell teller, IService service) {
            return service.addToQueue(teller);
    }
}
