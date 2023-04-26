package com.pfcti.springbeans.business;

import com.pfcti.springbeans.dto.AccountQueryDto;
import com.pfcti.springbeans.dto.AccountQueryType;
import com.pfcti.springdata.dto.AccountDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountSearcherTest {

    @Autowired
    private AccountSearcher databaseAccountService;

    @Autowired
    private AccountSearcher accountExternalService;

    @Test
    void getDatabaseAccounts() {
        String accountNumber = "22222";
        AccountQueryDto accountQueryDto = new AccountQueryDto();
        accountQueryDto.setAccountQueryType(AccountQueryType.ACCOUNT_NUMBER);
        accountQueryDto.setAccountNumber(accountNumber);
        List<AccountDto> accountDtosByAccountNumber = databaseAccountService.getAccounts(accountQueryDto);

        assertEquals(1, accountDtosByAccountNumber.size());

        accountQueryDto.setAccountQueryType(AccountQueryType.STATE);
        accountQueryDto.setState(true);
        List<AccountDto> accountDtosByState = databaseAccountService.getAccounts(accountQueryDto);

        assertEquals(1, accountDtosByState.size());
    }

    @Test
    void getExternalAccounts() {
        String accountNumber = "CR3700102100zz";
        AccountQueryDto accountQueryDto = new AccountQueryDto();
        accountQueryDto.setAccountQueryType(AccountQueryType.ACCOUNT_NUMBER);
        accountQueryDto.setAccountNumber(accountNumber);
        List<AccountDto> accountDtosByAccountNumber = accountExternalService.getAccounts(accountQueryDto);

        assertEquals(1, accountDtosByAccountNumber.size());

        accountQueryDto.setAccountQueryType(AccountQueryType.STATE);
        accountQueryDto.setState(true);
        List<AccountDto> accountDtosByState = accountExternalService.getAccounts(accountQueryDto);

        assertEquals(1, accountDtosByState.size());
    }
}