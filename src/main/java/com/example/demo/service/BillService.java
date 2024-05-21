package com.example.demo.service;

import com.example.demo.entity.Bill;

import java.util.List;

public interface BillService {
    Bill pay(List<Integer> cartBookIDs);
}
