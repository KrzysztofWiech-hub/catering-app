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
    private int id;

    @Enumerated(EnumType.ORDINAL)
    private Days dayId;

    @Enumerated(EnumType.STRING)
    private Days dayName;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "product_id")
    private Product product;

//    private String monday;
//    private String tuesday;
//    private String wednesday;
//    private String thursday;
//    private String friday;
//    private String saturday;
//    private String sunday;

}
