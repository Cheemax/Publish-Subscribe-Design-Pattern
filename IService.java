package com.company;

import java.util.List;

public interface IService<T>{
        boolean addToQueue(T record);
        boolean removeFromQueue(T record);
}
