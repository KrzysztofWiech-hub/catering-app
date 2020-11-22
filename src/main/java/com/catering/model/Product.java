package com.catering.model;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Name may not be null")
    private String name;

    private String description;

    private double cost;

    private double kcal;

    private double mass;

    private double height;

    private double weight;

//    test for check branch name

//    @OneToMany(mappedBy = "product")
//    private DaysOfWeek daysOfWeek;

}
