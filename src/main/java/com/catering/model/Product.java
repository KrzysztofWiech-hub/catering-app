package com.catering.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Name may not be null")
    private String name;

    private String description;

    private double cost;

    private double kcal;

    private double mass;

    private double height;

    private double weight;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "day_name")
    private DayOfWeek dayOfWeek;
}
