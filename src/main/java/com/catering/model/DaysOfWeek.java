package com.catering.model;

import com.catering.util.Days;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "days_of_week")
public class DaysOfWeek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @Enumerated(EnumType.ORDINAL)
//    private Days dayId;

    @Enumerated(EnumType.STRING)
    private Days dayName;

    @JsonIgnore
    private Integer productId;
}
