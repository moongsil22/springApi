package com.siri.springapi.common;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UsageAttributeConverter implements AttributeConverter<String, String> {

    /**
     * 엔티티를 데이터베이스에 저장할 떄 값을 변경하여 저장하는 메소드
     */
    @Override
    public String convertToDatabaseColumn(String s) {
        if (s.equals("운전")) {
            return "1";
        } else if (s.equals("시설")) {
            return "2";
        } else if (s.contains("및")) {
            return "3";
        }
        return "0";
    }
    
    @Override
    public String convertToEntityAttribute(String code) {
        return code;
    }
}
