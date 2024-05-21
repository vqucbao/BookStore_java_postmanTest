package com.example.demo.repository;

import com.example.demo.entity.CartBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartBookRepository extends JpaRepository<CartBook, Integer> {
    @Query(value = "select count(*) from cart_book where cart_id = :cartID and book_id = :bookID", nativeQuery = true)
    int checkBookInCart(@Param("cartID") int cartID,@Param("bookID") int bookID);
}
