package org.example.logbookservice.controller;

import org.example.logbookservice.service.LogBookService;
import org.example.common.model.Ship;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/log")
public class LogBookController {

    private final LogBookService logBookService;

    @Autowired
    public LogBookController(LogBookService logBookService) {
        this.logBookService = logBookService;
    }

    @PostMapping
    public String logShip(@RequestBody Ship ship) {
        return logBookService.logShip(ship);
    }
}
