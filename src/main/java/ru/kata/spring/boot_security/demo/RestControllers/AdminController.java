package ru.kata.spring.boot_security.demo.RestControllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @GetMapping("/admin")
    ResponseEntity<String> getAdmin(){
        return ResponseEntity.ok("ADMIN");
    }


}
