package com.village.coder.gymmanagementsystem.controllers;

import com.village.coder.gymmanagementsystem.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gym")
public class BookingMaster {
    @Autowired
    IService service;
}
