package com.pfcti.springwebservices.api;

import com.pfcti.springdata.dto.AccountDto;
import com.pfcti.springdata.service.AccountService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Create
 * Deactivate
 * Select
 *
 */

@RestController
@RequestMapping("/v1/api")
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/accounts/{id}/client")
    public List<AccountDto> getAllAccountByClientId (@PathVariable int id) {
        return this.accountService.findAllAccountsByClientId(id);
    }

    @PostMapping(path = "/accounts")
    public void saveAccount (@Valid @RequestBody AccountDto accountDto) {
        log.info("Creating account {}", accountDto);
        this.accountService.insert(accountDto);
    }

    @PutMapping(path = "/accounts")
    public void updateAccount (@Valid @RequestBody AccountDto accountDto) {
        log.info("Updating account {}", accountDto);
        this.accountService.update(accountDto);
    }
}
