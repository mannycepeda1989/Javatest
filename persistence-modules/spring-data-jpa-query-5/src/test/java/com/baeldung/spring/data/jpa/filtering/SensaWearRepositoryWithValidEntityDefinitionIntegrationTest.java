package com.baeldung.spring.data.jpa.filtering;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = FilteringApplication.class, properties = {
        "spring.jpa.defer-datasource-initialization=true",
        "spring.sql.init.data-locations=classpath:testdata.sql",
        "spring.jpa.hibernate.ddl-auto=none"
})
@EntityScan(basePackages = "com.baeldung.spring.data.jpa.filtering")
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
        value = {SensaWearInvalidEntity.class}))
@EnableJpaRepositories(
        basePackageClasses = SensaWearRepository.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = SensaWearBrokenRepository.class)
)
public class SensaWearRepositoryWithValidEntityDefinitionIntegrationTest {

    @Autowired
    private SensaWearRepository sensaWearRepository;

    @Test
    public void fetchWearablesListUsingCustomQueryThenAllOfThemPresent() {
        List<SensaWearValidEntity> wearables = sensaWearRepository.findAllByOrderByPriceAscSensorTypeAscPopularityIndexDesc();
        assertThat(wearables).hasSize(4);

        assertEntityFields(wearables.get(0), "SensaTag", "120.00", "Proximity", 2);
        assertEntityFields(wearables.get(1), "SensaShirt", "150.00", "Human Activity Recognition", 2);
        assertEntityFields(wearables.get(2), "SensaBelt", "300.00", "Heart Rate", 3);
        assertEntityFields(wearables.get(3), "SensaWatch", "500.00", "Accelerometer", 5);
    }

    private static void assertEntityFields(
            SensaWearValidEntity wearable,
            String name,
            String price,
            String sensorType,
            Integer popularityIndex
    ) {
        assertThat(wearable.getSensorType()).isEqualTo(sensorType);
        assertThat(wearable.getName()).isEqualTo(name);
        assertThat(wearable.getPrice()).isEqualTo(price);
        assertThat(wearable.getPopularityIndex()).isEqualTo(popularityIndex);
    }


}