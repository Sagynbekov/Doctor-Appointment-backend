package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book saved = bookRepository.save(book);
        return ResponseEntity.ok(saved);
    }

    // Новый метод для получения занятых времен по доктору и дате
    @GetMapping("/busy-times")
    public ResponseEntity<List<String>> getBusyTimes(@RequestParam Long doctorId, @RequestParam String date) {
        List<Book> books = bookRepository.findAll();
        List<String> busyTimes = books.stream()
                .filter(b -> b.getDoctorId().equals(doctorId) && b.getDate().equals(date))
                .map(Book::getTime)
                .collect(Collectors.toList());
        return ResponseEntity.ok(busyTimes);
    }
}
