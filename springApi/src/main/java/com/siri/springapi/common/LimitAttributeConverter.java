package com.siri.springapi.common;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class LimitAttributeConverter implements AttributeConverter<String,Double> {

    public Double convertToDatabaseColumn(String s) {
        if(s.contains("��")) {
            return 1.1;
        } else if ("�ü�".equals(s)) {
            return 2.2;
        } else if ("���� �� �ü�".equals(s)) {
            return 3.3;
        }
        return 0.0;
    }
    
    public String convertToEntityAttribute(Double code) {
        if ("1".equals(code)) {
            return "����";
        } else if ("2".equals(code)) {
            return "�ü�";
	    } else if ("3".equals(code)) {
	        return "���� �� �ü�";
	    }
        return "��Ÿ";
    }
}
