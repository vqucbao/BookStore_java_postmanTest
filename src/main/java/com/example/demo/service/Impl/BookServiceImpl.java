package com.example.demo.service.Impl;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import javax.servlet.ServletContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private ServletContext servletContext;


    public BookServiceImpl(BookRepository bookRepository, ServletContext servletContext) {

        this.bookRepository = bookRepository;
        this.servletContext = servletContext;
    }

    @Override
    public List<Book> getBooks(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return bookRepository.findBooks(pageable);
    }

    @Override
    public Book addBook(Book book, MultipartFile image) throws IOException {
        Book book1 = new Book();
        book1.setName(book.getName());
        book1.setCategory(book.getCategory());
        book1.setAuthor(book.getAuthor());
        book1.setPrice(book.getPrice());
        book1 = bookRepository.save(book1);
        if (image != null) {
            Path pathDir = Paths.get("images");
            Files.createDirectories(pathDir);
            // trả về tên đường dẫn tuyệt đối ban đầu
            String extension = StringUtils.getFilenameExtension(image.getOriginalFilename());
            // trích dẫn đuôi tên tệp ảnh
            String imgName = "book" + book1.getId() + "." + extension;
            Files.copy(image.getInputStream(), Paths.get(pathDir + File.separator + imgName), StandardCopyOption.REPLACE_EXISTING);
            // lưu ảnh vào folder
//            System.out.println("http://localhost:8085" + servletContext.getContextPath() + "/images/" + imgName);
            book1.setImage("http://localhost:8085" + servletContext.getContextPath() + "/images/" + imgName);
        }
        return bookRepository.save(book1);
    }

    @Override
    public Book updateBook(Book book, MultipartFile image) throws IOException {
        Book book1 = bookRepository.findById(book.getId()).get();
        book1.setName(book.getName());
        book1.setCategory(book.getCategory());
        book1.setAuthor(book.getAuthor());
        book1.setPrice(book.getPrice());
        if (image != null) {
            Path pathDir = Paths.get("images");
            Files.createDirectories(pathDir);
            // trả về tên đường dẫn tuyệt đối ban đầu
            String extension = StringUtils.getFilenameExtension(image.getOriginalFilename());
            // trích dẫn đuôi tên tệp ảnh
            String imgName = "book" + book1.getId() + "." + extension;
            Files.copy(image.getInputStream(), Paths.get(pathDir + File.separator + imgName), StandardCopyOption.REPLACE_EXISTING);
            // lưu ảnh vào folder
//            System.out.println("http://localhost:8085" + servletContext.getContextPath() + "/images/" + imgName);
            book1.setImage("http://localhost:8085" + servletContext.getContextPath() + "/images/" + imgName);
        }
        return bookRepository.save(book1);
    }


    @Override
    public Book deleteBook(Integer bookID) {
        bookRepository.deleteById(bookID);
        return bookRepository.findById(bookID).get();
    }

    @Override
    public List<Book> getBooksByKeyword(String keyword) {
        return bookRepository.findBookByKeywords("%" + keyword + "%");
    }

    @Override
    public List<Book> getBooksByCategory(String category) {
        return bookRepository.findBookByCategory("%" + category + "%");
    }

    @Override
    public int getPageNumber(int pageSize) {
        List<Book> books = bookRepository.findAll();
        return (int) Math.ceil((float) books.size() / pageSize);
    }

    @Override
    public List<String> getCategory() {
        return bookRepository.findCategory();
    }
}
