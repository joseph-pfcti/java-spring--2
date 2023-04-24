package com.pfcti.demo2.service;

import com.pfcti.demo2.repository.InvestmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InvestmentService {
    private InvestmentRepository investmentRepository;
}
