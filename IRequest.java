package com.company;

import java.util.List;

public interface IRequest<T> {
    boolean addRequest(T request);
    boolean addRequests(List<T> requests);
    boolean removeRequest(T request);
    boolean removeRequests(List<T> requests);
}
