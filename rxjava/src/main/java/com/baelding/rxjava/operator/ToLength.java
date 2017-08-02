package com.baelding.rxjava.operator;

import rx.Observable;
import rx.Observable.Transformer;

public class ToLength implements Transformer<String, Integer> {

    public static ToLength toLength() {
        return new ToLength();
    }

    public ToLength() {
        super();
    }

    @Override
    public Observable<Integer> call(Observable<String> source) {
        return source.map(String::length);
    }
}