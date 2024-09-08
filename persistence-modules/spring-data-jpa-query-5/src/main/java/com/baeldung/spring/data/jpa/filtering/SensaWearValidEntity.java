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
public class SensaWearValidEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "sensor_type")
    private String sensorType;

    @Column(name = "popularity_index")
    private Integer popularityIndex;

}
