package com.example.bike.service;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface LogService {
    public void save(String logs);
}
