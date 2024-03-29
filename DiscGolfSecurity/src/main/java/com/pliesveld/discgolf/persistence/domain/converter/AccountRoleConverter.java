package com.pliesveld.discgolf.persistence.domain.converter;

import com.pliesveld.discgolf.security.domain.AccountRole;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class AccountRoleConverter implements AttributeConverter<AccountRole, Integer> {
    @Override
    public Integer convertToDatabaseColumn(AccountRole attribute) {
        return attribute.getId();
    }

    @Override
    public AccountRole convertToEntityAttribute(Integer roleId) {
        return AccountRole.fromInteger(roleId);
    }
}
