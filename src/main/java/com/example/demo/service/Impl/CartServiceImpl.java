package com.example.demo.service.Impl;

import com.example.demo.entity.CartBook;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CartBookRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private final CartBookRepository cartBookRepository;
    private final CartRepository cartRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;


    public CartServiceImpl(CartBookRepository cartBookRepository, CartRepository cartRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.cartBookRepository = cartBookRepository;
        this.cartRepository = cartRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Boolean addBookToCart(int cartID, int bookID, int numberBook) {
        CartBook cartBook = new CartBook();
        // kiểm tra xem sách đã có trong giỏ hàng chưa
        int countRow = cartBookRepository.checkBookInCart(cartID, bookID);
        if (countRow > 0) return false;
        else {
            cartBook.setBook(bookRepository.findById(bookID).get());
            cartBook.setCart(cartRepository.findById(cartID).get());
            cartBook.setNumberBook(numberBook);
            cartBookRepository.save(cartBook);
            return true;
        }
    }

    @Override
    public List<CartBook> getCartBooks(int cartID) {
        return cartBookRepository.findAll();
    }

    @Override
    public CartBook updateNumberBookInCart(int cartBookID, int numberBookInCart) {
        CartBook cartBook = cartBookRepository.findById(cartBookID).get();
        cartBook.setNumberBook(numberBookInCart);
        return cartBookRepository.save(cartBook);
    }

    @Override
    public Optional<Integer> getCartIDByUserID(int userID) {
        return Optional.of(cartRepository.getCartByUser(userRepository.findById(userID).get()).getId());
    }

    @Override
    public CartBook deleteBookInCart(int cartBookID) {
        cartBookRepository.deleteById(cartBookID);
        return cartBookRepository.findById(cartBookID).get();
    }
}
