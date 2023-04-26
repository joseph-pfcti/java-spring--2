package com.pfcti.springbeans.business;

import com.pfcti.springbeans.dto.AccountQueryDto;
import com.pfcti.springdata.dto.AccountDto;

import java.util.List;

public interface AccountSearcher {
    List<AccountDto> getAccounts(AccountQueryDto accountQueryDto);
}
