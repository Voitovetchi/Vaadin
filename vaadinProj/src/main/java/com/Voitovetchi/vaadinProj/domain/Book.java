package com.Voitovetchi.vaadinProj.domain;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Book {
    @Id
    private String isbn;
    private String title;
    private String price;
}
