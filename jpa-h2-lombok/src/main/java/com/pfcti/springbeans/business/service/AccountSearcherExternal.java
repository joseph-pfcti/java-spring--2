package com.pfcti.springbeans.business.service;

import com.pfcti.springbeans.business.AccountSearcher;
import com.pfcti.springbeans.dto.AccountQueryDto;
import com.pfcti.springbeans.dto.AccountQueryType;
import com.pfcti.springdata.dto.AccountDto;
import com.pfcti.springdata.dto.ClientDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("accountExternalService")
public class AccountSearcherExternal implements AccountSearcher {
    @Override
    public List<AccountDto> getAccounts(AccountQueryDto accountQueryDto) {

        List<AccountDto> accountDtos = List.of(
                new AccountDto(1, "CR3700102100zz", "SAVING", true, 1),
                new AccountDto(2, "CR23065165100zz", "SAVING", false, 2)
        );

        return accountDtos.stream().filter(filter ->
                accountQueryDto.getAccountQueryType() == AccountQueryType.ACCOUNT_NUMBER
                        ? filter.getAccountNumber().equals(accountQueryDto.getAccountNumber())
                        : filter.getState().equals(accountQueryDto.getState())
        ).toList();
    }
}
