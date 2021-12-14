package com.example.takslistbackend.controller;

import com.example.takslistbackend.entity.StatEntity;
import com.example.takslistbackend.repo.StatRepository;
import com.example.takslistbackend.service.StatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatController {

    private StatService statService;

    public StatController(StatService statService) {
        this.statService = statService;
    }

    private final Long defaultId = 1L;

    @GetMapping("/stat")
    public ResponseEntity<StatEntity> findById(){
        return ResponseEntity.ok(statService.findById(defaultId));
    }
}
