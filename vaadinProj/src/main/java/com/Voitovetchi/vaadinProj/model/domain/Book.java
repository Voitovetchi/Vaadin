package com.Voitovetchi.vaadinProj.model.domain;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Class that represents book table in database as an entity.
 *
 * @author Iurii Voitovetchi.
 */
@Entity
@Data
public class Book {
    @Id
    private String isbn;
    private String title;
    private String price;
}
