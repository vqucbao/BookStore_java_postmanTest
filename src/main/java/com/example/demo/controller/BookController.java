package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.service.Impl.BookServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class BookController {
    private final BookServiceImpl bookService;


    public BookController(BookServiceImpl bookServiceImpl) {
        this.bookService = bookServiceImpl;
    }

    @GetMapping("/Book/getBooks/{pageNumber}/{pageSize}")
    public ResponseEntity<List<Book>> getBooks(@PathVariable(name = "pageNumber") int pageNumber, @PathVariable(name = "pageSize") int pageSize) {
        List<Book> books = bookService.getBooks(pageNumber, pageSize);
        if (books == null) return new ResponseEntity<>(null, HttpStatus.OK);
        else return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/Book/keyword/{keyword}")
    public ResponseEntity<List<Book>> getBooksByKeyword(@PathVariable String keyword) {
        List<Book> books = bookService.getBooksByKeyword(keyword);
        if (books == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/Book/category/{category}")
    public ResponseEntity<List<Book>> getBooksByCategory(@PathVariable String category) {
        List<Book> books = bookService.getBooksByCategory(category);
        if (books == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/Book/getCategory")
    public ResponseEntity<List<String>> getCategory() {
        List<String> category = bookService.getCategory();
        if (category == null) return new ResponseEntity<>(null, HttpStatus.OK);
        else return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/Book/pageSize/{pageSize}")
    public ResponseEntity<Integer> getPageNumber(@PathVariable int pageSize) {
        int pageNumber = bookService.getPageNumber(pageSize);
        return new ResponseEntity<>(pageNumber, HttpStatus.OK);
    }

    @PostMapping(value = "/Book", consumes = "multipart/form-data")
    public ResponseEntity<Book> addBook(final Book book, @RequestParam(value = "image_ok") final MultipartFile image_ok) throws IOException {
        Book book1 = bookService.addBook(book, image_ok);
        return new ResponseEntity<>(book1, HttpStatus.OK);
    }

    @PutMapping(value = "/Book", consumes = "multipart/form-data")
    public ResponseEntity<Book> updateBook(final Book book, @RequestParam(value = "image_ok") final MultipartFile image_ok) throws IOException {
        Book book1 = bookService.updateBook(book, image_ok);
        return new ResponseEntity<>(book1, HttpStatus.OK);
    }

    @DeleteMapping("/Book/{bookID}")
    public ResponseEntity<Book> deleteBook(@PathVariable Integer bookID) {
        bookService.deleteBook(bookID);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
