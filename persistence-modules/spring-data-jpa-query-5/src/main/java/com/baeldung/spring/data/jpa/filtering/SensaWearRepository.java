package com.baeldung.spring.data.jpa.filtering;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensaWearRepository extends JpaRepository<SensaWearValidEntity, Long> {
    List<SensaWearValidEntity> findAllByOrderByPriceAscSensorTypeAscPopularityIndexDesc();

}

