package com.pfcti.springbeans.business.service;

import com.pfcti.springbeans.business.AccountSearcher;
import com.pfcti.springbeans.dto.AccountQueryDto;
import com.pfcti.springbeans.dto.AccountQueryType;
import com.pfcti.springdata.dto.AccountDto;
import com.pfcti.springdata.model.Account;
import com.pfcti.springdata.repository.AccountRepository;
import com.pfcti.springdata.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("databaseAccountService")
public class AccountSearcherDB implements AccountSearcher {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Override
    public List<AccountDto> getAccounts(AccountQueryDto accountQueryDto) {
        List<Account> accounts = new ArrayList<>();

        if (accountQueryDto.getAccountQueryType().equals(AccountQueryType.ACCOUNT_NUMBER)) {
            accounts = accountRepository.findByAccountNumber(accountQueryDto.getAccountNumber());
        }

        if (accountQueryDto.getAccountQueryType().equals(AccountQueryType.STATE)) {
            accounts = accountRepository.findByState(accountQueryDto.getState());
        }

        return this.accountService.convertAccountListToAccountListDto(accounts);
    }
}
