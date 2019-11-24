
package com.siri.springapi.domain;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.siri.springapi.common.UsageAttributeConverter;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**

 * @FileName  : SupportInfo.java

 * @Project     : springApi (카카오페이 사전과제1 - 지자체 협약지원 api 개발)

 * @Date         : 2019. 11. 24.

 * @작성자      : siri

 * @변경이력 :

 * @프로그램 설명 : 지자체 지원 정보 Entity 설정

 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "t_support_info")
public class SupportInfo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_seq_support_info")
    @SequenceGenerator(name = "gen_seq_support_info", sequenceName = "seq_support_info" )
    //구분
    private Long program_id; 

    //지자체 코드
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_code", foreignKey = @ForeignKey(name = "FK_SUPPORTINFO_SUPPORTREG"))
    private SupportReg supportreg;
    
    //지원대상
    @Column(columnDefinition = "TEXT", nullable = false)
    private String target;
    
    //용도
    @Column(length = 10, nullable = false)
    private String usage;
    
    //용도타입 ( 1 : 운전 , 2 : 시설 , 3 : 운전 및 시설
    @Convert(converter = UsageAttributeConverter.class )
    @Column(length = 1, nullable = false)
    private String usage_type;
    
    //지원한도
    @Column(length = 20, nullable = false)
    private String limit_s;

    //지원한도금액
    //@Column(nullable = false)
    //private String limit_amt;
    
    //이차보전
    @Column(length = 20, nullable = false)
    private String rate;

    //이차보전 최소비율
    //@Column (nullable = false)
    //private String min_rate;
    
    //이차보전 최대비율
    //@Column(nullable = false)
    //private String max_rate;
    
    //이차보전 평균비율
    //@Column(nullable = false)
    //private String avg_rate;
    
    //추천기관
    @Column(length = 50, nullable = false)
    private String institute;
    
    //관리점
    @Column(length = 20, nullable = false)
    private String mgmt;
    
    //취급점
    @Column(length = 50, nullable = false)
    private String reception;
    

    
    @Builder
    public SupportInfo(String target, SupportReg supportreg, String usage, String usage_type, String limit_s, String limit_amt, String rate , String min_rate, String max_rate, String avg_rate, String institute, String mgmt, String reception) {
        this.target = target;
        this.supportreg = supportreg;
        this.usage = usage;
        this.usage_type = usage_type;
        this.limit_s = limit_s;
       // this.limit_amt = limit_amt;
        this.rate = rate;
       // this.min_rate = min_rate;
       // this.max_rate = max_rate;
       // this.avg_rate = avg_rate;
        this.institute = institute;
        this.mgmt = mgmt;
        this.reception = reception;
    }
    
    public void updateSupportReg(SupportReg supportreg){
        this.supportreg = supportreg;
    }
    /*
    public SupportInfo(String usage, String usage_type, String limit) {
        this.title = title;
        this.content = content;
        this.author = author;
    }*/
}
