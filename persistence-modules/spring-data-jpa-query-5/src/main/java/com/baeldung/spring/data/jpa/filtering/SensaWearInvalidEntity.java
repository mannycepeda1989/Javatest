package com.baeldung.spring.data.jpa.filtering;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
@Entity
@Table(name = "wearables")
public class SensaWearInvalidEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String Name;

    @Column(name = "price")
    private BigDecimal Price;

    @Column(name = "sensor_type")
    private String SensorType;

    @Column(name = "popularity_index")
    private Integer PopularityIndex;

}
