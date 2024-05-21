package com.example.demo.service.Impl;

import com.example.demo.entity.Bill;
import com.example.demo.entity.Book;
import com.example.demo.entity.CartBook;
import com.example.demo.repository.BillRepository;
import com.example.demo.repository.CartBookRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.service.BillService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    private final CartBookRepository cartBookRepository;
    private final BillRepository billRepository;
    private final CartRepository cartRepository;

    public BillServiceImpl(CartBookRepository cartBookRepository, BillRepository billRepository, CartRepository cartRepository) {
        this.cartBookRepository = cartBookRepository;
        this.billRepository = billRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public Bill pay(List<Integer> cartBookIDs) {
        int totalPrice = 0;
        int cartID = 0;
        LocalDateTime now = LocalDateTime.now();
        for (int cartBookID : cartBookIDs) {
            // lấy cartID và bookID
            CartBook cartBook = cartBookRepository.getById(cartBookID);
            Book book = cartBook.getBook();
            cartID = cartBook.getCart().getId();
            totalPrice += cartBook.getNumberBook() * book.getPrice();
            // xoá sách ra khỏi giỏ hàng
            cartBookRepository.deleteById(cartBook.getId());
        }
        Bill bill = new Bill();
        bill.setDate(now);
        bill.setCart(cartRepository.getById(cartID));
        bill.setTotalPrice(totalPrice);
        return billRepository.save(bill);
    }
}
