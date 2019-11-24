package com.siri.springapi.common;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class LimitAttributeConverter implements AttributeConverter<String,Double> {

    public Double convertToDatabaseColumn(String s) {
        if(s.contains("억")) {
            return 1.1;
        } else if ("시설".equals(s)) {
            return 2.2;
        } else if ("운전 및 시설".equals(s)) {
            return 3.3;
        }
        return 0.0;
    }
    
    public String convertToEntityAttribute(Double code) {
        if ("1".equals(code)) {
            return "운전";
        } else if ("2".equals(code)) {
            return "시설";
	    } else if ("3".equals(code)) {
	        return "운전 및 시설";
	    }
        return "기타";
    }
}
