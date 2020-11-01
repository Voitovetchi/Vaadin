package com.Voitovetchi.vaadinProj.repository;

import com.Voitovetchi.vaadinProj.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Long> {
    //@Query("select from ")
    List<Book> findByTitle(@Param("title") String title);
    //List<Book> findAll();
}
