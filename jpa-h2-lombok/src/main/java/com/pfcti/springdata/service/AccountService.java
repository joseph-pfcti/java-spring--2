package com.pfcti.springdata.service;

import com.pfcti.springdata.criteria.AccountSpecification;
import com.pfcti.springdata.dto.AccountDto;
import com.pfcti.springdata.model.Account;
import com.pfcti.springdata.model.Client;
import com.pfcti.springdata.repository.AccountRepository;
import com.pfcti.springjms.dto.NotificationDto;
import com.pfcti.springjms.senders.NotificationSender;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@AllArgsConstructor
@Slf4j
public class AccountService {
    private AccountRepository accountRepository;
    private AccountSpecification accountSpecification;

    private ClientService clientService;

    private NotificationSender notificationSender;


    public List<AccountDto> findAllAccountsByClientId (int clientId) {
        return this.convertAccountListToAccountListDto(this.accountRepository.findByClient_Id(clientId));
    }

    private Account fromAccountDtoToAccountEntity (AccountDto accountDto) {
        Account account = new Account();
        BeanUtils.copyProperties(accountDto, account);

        Client client = new Client();
        client.setId(accountDto.getClientId());
        account.setClient(client);

        return account;
    }

    private AccountDto fromAccountEntityToAccountDto (Account account) {
        AccountDto accountDto = new AccountDto();
        BeanUtils.copyProperties(account, accountDto);
        accountDto.setClientId(account.getClient().getId());
        return accountDto;
    }

    public List<AccountDto> findAccountByCriteria (AccountDto accountDto) {
        return this.accountRepository
                .findAll(this.accountSpecification.buildFilter(accountDto))
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

    public void insert(AccountDto accountDto) {
        Account account = fromAccountDtoToAccountEntity(accountDto);
        this.accountRepository.save(account);
        log.info("Account created {}", accountDto);

        this.sendAccountNotification(accountDto);
    }

    public void update(AccountDto accountDto) {
        Account account = fromAccountDtoToAccountEntity(accountDto);
        this.accountRepository.save(account);
    }

    public void sendAccountNotification (AccountDto accountDto) {
        Client client = this.clientService.find(accountDto.getClientId());

        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setPhoneNumber(client.getPhone());
        notificationDto.setSmsBody("Dear " + client.getName() + " your account has been created");
        this.notificationSender.sendSMS(notificationDto);
    }
}
