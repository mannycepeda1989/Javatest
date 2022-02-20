package com.baeldung.read_only_transactions.mysql.dao;

import com.baeldung.read_only_transactions.mysql.spring.repositories.TransactionRepository;

import java.util.SplittableRandom;

public class MyRepoSpring extends BaseRepo {

    private TransactionRepository repository;

    public MyRepoSpring(TransactionRepository repository) {
        this.repository = repository;
    }

    private final SplittableRandom random = new SplittableRandom();

    public long runQuery() {
        return this.execQuery((count) -> {
            repository.get(1L + random.nextLong(0, 1000000));
            count.incrementAndGet();
        });
    }
}
