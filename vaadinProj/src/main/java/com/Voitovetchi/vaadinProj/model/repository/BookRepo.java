package com.Voitovetchi.vaadinProj.model.repository;

import com.Voitovetchi.vaadinProj.model.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Interface that holds data from table book in database.
 *
 * @author Iurii Voitovetchi.
 */
public interface BookRepo extends JpaRepository<Book, String> {
    /**
     * Method to find books by title.
     *
     * @param title title of a book.
     * @return list of books with specified title.
     */
    @Query(value = "select * from book where title like concat('%', :title, '%')", nativeQuery = true)
    List<Book> findByTitle(@Param("title") String title);
}
