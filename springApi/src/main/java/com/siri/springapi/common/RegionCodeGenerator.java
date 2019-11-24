package com.siri.springapi.common;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class RegionCodeGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
	      String prefix = "region";
	        Connection connection = session.connection();
	        try {
	 
	            PreparedStatement ps = connection
	                    .prepareStatement("SELECT nextval ('seq_id') as nextval");
	 
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	                int id = rs.getInt("nextval");
	                String code = prefix + StringUtils.leftPad("" + id,3, '0');
	                return code;
	            }
	 
	        } catch (SQLException e) {
	            throw new HibernateException(
	                    "Unable to generate region_code Sequence");
	        }
		return null;
	}
 

}