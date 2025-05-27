package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private DoctorRepository doctorRepository;

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

    // Новый endpoint для получения всех записей пользователя с данными о враче
    @GetMapping("/appointments")
    public ResponseEntity<List<Map<String, Object>>> getUserAppointments(@RequestParam Long userId) {
        List<Book> books = bookRepository.findAll().stream()
                .filter(b -> b.getUserId().equals(userId))
                .collect(Collectors.toList());
        List<Map<String, Object>> result = books.stream().map(book -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", book.getId());
            map.put("date", book.getDate());
            map.put("time", book.getTime());
            map.put("doctorId", book.getDoctorId()); // добавляем doctorId
            var doctorOpt = doctorRepository.findById(book.getDoctorId());
            if (doctorOpt.isPresent()) {
                var doctor = doctorOpt.get();
                map.put("doctorName", doctor.getName());
                map.put("service", doctor.getService());
                map.put("photoUrl", doctor.getPhotoUrl());
            } else {
                map.put("doctorName", "—");
                map.put("service", "");
                map.put("photoUrl", null);
            }
            return map;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }
}
