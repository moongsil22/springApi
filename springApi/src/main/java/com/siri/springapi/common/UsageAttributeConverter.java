package com.siri.springapi.common;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UsageAttributeConverter implements AttributeConverter<String, String> {

    /**
     * ��ƼƼ�� �����ͺ��̽��� ������ �� ���� �����Ͽ� �����ϴ� �޼ҵ�
     */
    @Override
    public String convertToDatabaseColumn(String s) {
        if (s.equals("����")) {
            return "1";
        } else if (s.equals("�ü�")) {
            return "2";
        } else if (s.contains("��")) {
            return "3";
        }
        return "0";
    }
    
    @Override
    public String convertToEntityAttribute(String code) {
        return code;
    }
}
