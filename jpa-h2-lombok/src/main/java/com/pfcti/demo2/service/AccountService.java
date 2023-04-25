package com.pfcti.demo2.service;

import com.pfcti.demo2.criteria.AccountSpecification;
import com.pfcti.demo2.dto.AccountDto;
import com.pfcti.demo2.model.Account;
import com.pfcti.demo2.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountService {
    private AccountRepository accountRepository;

    private AccountSpecification accountSpecification;

    private Account fromAccountDtoToAccountEntity (AccountDto accountDto) {
        Account account = new Account();
        BeanUtils.copyProperties(accountDto, account);
        return account;
    }

    private AccountDto fromAccountEntityToAccountDto (Account account) {
        AccountDto accountDto = new AccountDto();
        BeanUtils.copyProperties(account, accountDto);
        return accountDto;
    }

    public List<AccountDto> findAccountByCriteria (AccountDto accountDto) {
        return accountRepository
                .findAll(accountSpecification.buildFilter(accountDto))
                .stream()
                .map(this::fromAccountEntityToAccountDto)
                .collect(Collectors.toList());
    }

    public List<AccountDto> convertAccountListToAccountListDto (List<Account> accountList) {
        return accountList
                .stream()
                .map(this::fromAccountEntityToAccountDto)
                .collect(Collectors.toList());
    }
}
