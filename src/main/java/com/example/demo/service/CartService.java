package com.example.demo.service;


import com.example.demo.entity.CartBook;

import java.util.List;
import java.util.Optional;

public interface CartService {
    Boolean addBookToCart(int cartID, int bookID, int numberBook);

    List<CartBook> getCartBooks(int cartID);

    CartBook updateNumberBookInCart(int cartBookID, int numberBookInCart);

    Optional<Integer> getCartIDByUserID(int userID);

    CartBook deleteBookInCart(int cartBookID);
}
