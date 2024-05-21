package com.example.demo.service;

import com.example.demo.entity.Book;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BookService {
    List<Book> getBooks(int pageNumber, int pageSize);

    Book addBook(Book book, MultipartFile image) throws IOException;

    Book updateBook(Book book, MultipartFile image) throws IOException;

    Book deleteBook(Integer bookID);

    List<Book> getBooksByKeyword(String keyword);

    List<Book> getBooksByCategory(String category);

    int getPageNumber(int pageSize);

    List<String> getCategory();
}
