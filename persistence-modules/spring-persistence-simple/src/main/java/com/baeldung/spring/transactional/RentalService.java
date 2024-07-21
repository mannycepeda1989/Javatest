package com.baeldung.spring.transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;

@Service
@Transactional(Transactional.TxType.SUPPORTS)
public class RentalService {

    @Autowired
    private CarRepository carRepository;

    @Transactional(rollbackOn = IllegalArgumentException.class, dontRollbackOn = EntityExistsException.class)
    public Car rent(Car car) {
        return carRepository.save(car);
    }
}
