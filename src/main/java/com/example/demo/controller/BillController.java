package com.example.demo.controller;

import com.example.demo.entity.Bill;
import com.example.demo.service.BillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class BillController {
    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping("/Bill")
    public ResponseEntity<Bill> pay(@RequestParam List<Integer> cartBookIDs) {
//        http://localhost:8085/Bill?cartBookIDs=1,5,6
        Bill bill = billService.pay(cartBookIDs);
        if (bill == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(bill, HttpStatus.OK);
    }
}
