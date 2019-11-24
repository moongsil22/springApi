package com.siri.springapi.common;

import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.TableGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;

/**

 * @FileName  : StringPrefixTableGenerator.java

 * @Project     : springApi (카카오페이 사전과제1 - 지자체 협약지원 api 개발)

 * @Date         : 2019. 11. 24.

 * @작성자      : siri

 * @변경이력 :

 * @프로그램 설명 : prefix + sequence pk 생성

 */
public class StringPrefixTableGenerator extends TableGenerator {


    private final String PREFIX_KEY = "prefix_key";
    private final int DEF_NUMBER_LENGTH = 4;
    private String prefixKey = "";

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {

        prefixKey = ConfigurationHelper.getString( PREFIX_KEY, params);
        super.configure(StandardBasicTypes.LONG, params, serviceRegistry);
    }

    @Override
    public Serializable generate(final SharedSessionContractImplementor session, final Object obj) {
        Serializable v =  super.generate(session, obj);

        Long longValue = (Long)v;

        int valueLength = String.valueOf(longValue).length();
        valueLength = valueLength > DEF_NUMBER_LENGTH ? valueLength : DEF_NUMBER_LENGTH;
        valueLength = valueLength > 9 ? 9 : valueLength;

        return String.format(prefixKey + "%0" + valueLength + "d", v);
    }

}