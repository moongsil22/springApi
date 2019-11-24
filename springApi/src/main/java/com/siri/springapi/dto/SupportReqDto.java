package com.siri.springapi.dto;

import com.opencsv.bean.CsvBindByPosition;
import com.siri.springapi.domain.SupportInfo;
import com.siri.springapi.domain.SupportReg;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class SupportReqDto {

	@CsvBindByPosition(position = 1)
    private String region;
	
	private Long region_code;
	
	@CsvBindByPosition(position = 2)
    private String target;
	@CsvBindByPosition(position = 3)
    private String usage;
	//@CsvBindByPosition(position = 3)
	private String usage_type;
	
	@CsvBindByPosition(position = 4)
    private String limit_s;
	
	//private double limit_amt;
	
	@CsvBindByPosition(position = 5)
    private String rate;
	
	//private double min_rate;
	//private double max_rate;
	//private double avg_rate;
	
	@CsvBindByPosition(position = 6)
    private String institute;
	@CsvBindByPosition(position = 7)
    private String mgmt;
	@CsvBindByPosition(position = 8)
    private String reception;
		
    public SupportInfo toSupportInfoEntity(){
        return SupportInfo.builder()
                .target(target)
                .usage(usage)
                .usage_type(usage_type)
                .limit_s(limit_s)
             //   .limit_amt(limit_s)
                .rate(rate)
             //   .min_rate(rate)
             //   .max_rate(rate)
             //   .avg_rate(rate)
                .institute(institute)
                .mgmt(mgmt)
                .reception(reception)
                .build();
    }
    
    public SupportReg toSupportRegEntity(){
        return SupportReg.builder()
                .region(region)
                .build();
    }
    
    
}