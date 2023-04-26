package com.pfcti.springdata.service;

import com.pfcti.springdata.repository.InvestmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InvestmentService {
    private InvestmentRepository investmentRepository;
}
