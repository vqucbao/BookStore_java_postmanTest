package com.example.demo.controller;

import com.example.demo.entity.CartBook;
import com.example.demo.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:8080")
@RestController("cartBook")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/cart/addBook/{cartID}/{bookID}/{numberBook}")
    public ResponseEntity<Boolean> addBookToCart(@PathVariable(name = "cartID") int cartID, @PathVariable(name = "bookID") int bookID, @PathVariable(name = "numberBook") int numberBook) {
        Boolean checkCartBook = cartService.addBookToCart(cartID, bookID, numberBook);
        if (checkCartBook == false) return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/cart/getCartBooks/{cartID}")
    public ResponseEntity<List<CartBook>> getCartBooks(@PathVariable int cartID) {
        List<CartBook> cartBooks = cartService.getCartBooks(cartID);
        if (cartBooks == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(cartBooks, HttpStatus.OK);
    }

    @PutMapping("/cart/{cartBookID}/{numberBookInCart}")
    public ResponseEntity<CartBook> updateNumberBookInCart(@PathVariable(name = "cartBookID") int cartBookID, @PathVariable(name = "numberBookInCart") int numberBookInCart) {
        CartBook cartBook = cartService.updateNumberBookInCart(cartBookID, numberBookInCart);
        return new ResponseEntity<>(cartBook, HttpStatus.OK);
    }

    @GetMapping("/cart/getCartID/{userID}")
    public ResponseEntity<Optional<Integer>> getCartIDByUserID(@PathVariable int userID) {
        Optional<Integer> cartID = cartService.getCartIDByUserID(userID);
        if (cartID == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(cartID, HttpStatus.OK);
    }

    @DeleteMapping("/cart/{cartBookID}")
    public ResponseEntity<CartBook> deleteBookInCart(@PathVariable int cartBookID) {
        cartService.deleteBookInCart(cartBookID);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
