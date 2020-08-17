package com.company;

public interface IPublisher<T> {
    boolean publish(T record, IService service);
}
