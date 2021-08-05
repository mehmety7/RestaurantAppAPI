package com.finartz.restaurantapp.model.converter;

public interface GenericConverter<S, T> {
    T convert(S source);
}
