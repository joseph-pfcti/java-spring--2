package com.pfcti.springdata.service;

import com.pfcti.springdata.dto.AccountDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Test
    void findAccountByCriteria() {
        AccountDto accountDto = new AccountDto();
        accountDto.setClientId(1);
        accountDto.setAccountNumber("22222");
        accountDto.setState(true);
        List<AccountDto> accountDtoList = accountService.findAccountByCriteria(accountDto);

        assertEquals(1, accountDtoList.size());
    }

    @Test
    void insert() {
        AccountDto accountDto = new AccountDto();
        accountDto.setClientId(1);
        accountDto.setAccountNumber("370632r");
        accountDto.setState(true);
        accountDto.setType("SAVING");
        accountService.insert(accountDto);

        assertEquals(1, 1);
    }
}