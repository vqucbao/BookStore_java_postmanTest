package com.example.demo.repository;

import com.example.demo.entity.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("select e from Book e where e.name like :keyword or e.author like :keyword or e.category like :keyword")
    List<Book> findBookByKeywords(@Param("keyword") String keyword);

    @Query("select e from Book e where e.category like :category")
    List<Book> findBookByCategory(@Param("category") String category);


    @Query("SELECT e FROM Book e")
    List<Book> findBooks(Pageable pageable);

    @Query("SELECT category FROM Book GROUP BY category")
    List<String> findCategory();
}
