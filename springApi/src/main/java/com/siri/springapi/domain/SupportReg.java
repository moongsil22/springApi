package com.siri.springapi.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**

 * @FileName  : SupportReg.java

 * @Project     : springApi (카카오페이 사전과제1 - 지자체 협약지원 api 개발)

 * @Date         : 2019. 11. 24.

 * @작성자      : siri

 * @변경이력 :

 * @프로그램 설명 : 지원 지자체 기관 엔티티

 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "t_support_reg")
public class SupportReg {

    @Id
    /*
    @GeneratedValue(strategy= GenerationType.TABLE, generator = "string_prefix_generator")
    @GenericGenerator(name = "string_prefix_generator", strategy = "com.siri.springapi.common.StringPrefixTableGenerator", parameters = {
            @Parameter(name = "table_name", value = "t_support_reg"),
            @Parameter(name = "value_column_name", value = "region_code"),
            @Parameter(name = "segment_column_name", value = "region"),
            @Parameter(name = "segment_value", value = "regions_key"),
            @Parameter(name = "prefix_key", value = "region")})
    */
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_seq_support_reg")
    @SequenceGenerator(name = "gen_seq_support_reg", sequenceName = "seq_support_reg" )
    //지자체코드
    private Long region_code;

    //지자체명
    @Column(length = 100, nullable = false)
    private String region;
    
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="region_code")
    private List<SupportInfo> supportInfos = new ArrayList<>();;

    
    @Builder
    public SupportReg(String region, List<SupportInfo> supportInfos ) {
        this.region = region;
        if(supportInfos != null){
            this.supportInfos = supportInfos;
        }
    }
    
    public void addSupportInfo(SupportInfo supportInfo){
        this.supportInfos.add(supportInfo);
        supportInfo.updateSupportReg(this);
    }
    
}
