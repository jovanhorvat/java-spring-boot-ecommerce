package com.example.shop.domain;

import javax.persistence.*;

@Entity
@Table(name="accessory")
public class Accessory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name="description")
    private String desc;
}
