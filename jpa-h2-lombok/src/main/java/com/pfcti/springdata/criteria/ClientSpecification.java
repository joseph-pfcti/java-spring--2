package com.pfcti.springdata.criteria;

import com.pfcti.springdata.dto.ClientDto;
import com.pfcti.springdata.model.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Locale;

@Component
public class ClientSpecification {

    public <T>Specification<T> equals(String fieldName, String fieldValue) {
        return fieldValue == null ? null : (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(fieldName), fieldValue);
    }

    public static <T>Specification<T> like(String fieldName, String fieldValue) {
        if (fieldValue != null) {
            String uppercaseValue = MessageFormat.format("%{0}%", fieldValue.trim().toUpperCase(Locale.ROOT)).replaceAll(" ", "%");
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.upper(root.get(fieldName)), uppercaseValue);
        }

        return null;
    }

    private Specification<Client> lastNamesCriteria (ClientDto clientDto) {
        return like("lastNames", clientDto.getLastNames());
    }

    private Specification<Client> nameCriteria (ClientDto clientDto) {
        return equals("name", clientDto.getName());
    }

    private Specification<Client> dniCriteria (ClientDto clientDto) {
        return equals("dni", clientDto.getDni());
    }

    private Specification<Client> phoneCriteria (ClientDto clientDto) {
        return equals("phone", clientDto.getPhone());
    }

    private Specification<Client> countryCriteria (ClientDto clientDto) {
        return equals("country", clientDto.getCountry());
    }

    public Specification<Client> buildFilter (ClientDto clientDto) {
        System.out.println(clientDto + "CRITERIA");
        return Specification
                .where(lastNamesCriteria(clientDto))
                .and(nameCriteria(clientDto))
                .and(dniCriteria(clientDto))
                .and(phoneCriteria(clientDto))
                .and(countryCriteria(clientDto));
    }
}
