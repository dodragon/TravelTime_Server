package com.dod.traveltime.entity.data;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.EnumSet;
import java.util.NoSuchElementException;

@Converter
public class EnumConverter<E extends Enum<E> & EnumCodeValue> implements AttributeConverter<E, String> {

    private final Class<E> clz;

    public EnumConverter(Class<E> clz) {
        this.clz = clz;
    }

    @Override
    public String convertToDatabaseColumn(E attribute) {
        return attribute.getCode();
    }

    @Override
    public E convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(clz).stream()
                .filter(e->e.getCode().equals(dbData))
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }
}
