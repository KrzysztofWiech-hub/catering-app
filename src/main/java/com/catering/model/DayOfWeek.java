package com.catering.model;

import com.catering.constants.Day;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "day_of_week")
public class DayOfWeek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @Enumerated(EnumType.ORDINAL)
//    private Day dayId;

    @Enumerated(EnumType.STRING)
    private Day dayName;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "product_id")
    private Product product;

}
