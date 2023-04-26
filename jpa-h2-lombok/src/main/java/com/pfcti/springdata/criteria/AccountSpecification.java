package com.pfcti.springdata.criteria;

import com.pfcti.springdata.dto.AccountDto;
import com.pfcti.springdata.model.Account;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class AccountSpecification {
    public <T> Specification<T> equals(String fieldName, String fieldValue) {
        return fieldValue == null ? null : (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(fieldName), fieldValue);
    }

    public <T>Specification<T> isTrue(String fieldName, Boolean fieldValue) {
        return fieldValue == null ? null : (root, query, criteriaBuilder) -> criteriaBuilder.isTrue(root.get(fieldName));
    }

    private Specification<Account> typeSpecification (AccountDto accountDto) {
        return equals("type", accountDto.getType());
    }

    private Specification<Account> accountNumberSpecification (AccountDto accountDto) {
        return equals("accountNumber", accountDto.getAccountNumber());
    }

    private Specification<Account> stateSpecification (AccountDto accountDto) {
        return isTrue("state", accountDto.getState());
    }

    private Specification<Account> clientIdSpecification (AccountDto accountDto) {
        return equals("client", Integer.toString(accountDto.getClientId()));
    }

    public Specification<Account> buildFilter (AccountDto accountDto) {
        System.out.println(accountDto + " Account criteria");
        return Specification
                .where(clientIdSpecification(accountDto))
                .and(typeSpecification(accountDto))
                .and(accountNumberSpecification(accountDto))
                .and(stateSpecification(accountDto));
    }
}
