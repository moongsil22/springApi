
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

 * @Project     : springApi (īī������ ��������1 - ����ü �������� api ����)

 * @Date         : 2019. 11. 24.

 * @�ۼ���      : siri

 * @�����̷� :

 * @���α׷� ���� : ����ü ���� ���� Entity ����

 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "t_support_info")
public class SupportInfo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_seq_support_info")
    @SequenceGenerator(name = "gen_seq_support_info", sequenceName = "seq_support_info" )
    //����
    private Long program_id; 

    //����ü �ڵ�
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_code", foreignKey = @ForeignKey(name = "FK_SUPPORTINFO_SUPPORTREG"))
    private SupportReg supportreg;
    
    //�������
    @Column(columnDefinition = "TEXT", nullable = false)
    private String target;
    
    //�뵵
    @Column(length = 10, nullable = false)
    private String usage;
    
    //�뵵Ÿ�� ( 1 : ���� , 2 : �ü� , 3 : ���� �� �ü�
    @Convert(converter = UsageAttributeConverter.class )
    @Column(length = 1, nullable = false)
    private String usage_type;
    
    //�����ѵ�
    @Column(length = 20, nullable = false)
    private String limit_s;

    //�����ѵ��ݾ�
    //@Column(nullable = false)
    //private String limit_amt;
    
    //��������
    @Column(length = 20, nullable = false)
    private String rate;

    //�������� �ּҺ���
    //@Column (nullable = false)
    //private String min_rate;
    
    //�������� �ִ����
    //@Column(nullable = false)
    //private String max_rate;
    
    //�������� ��պ���
    //@Column(nullable = false)
    //private String avg_rate;
    
    //��õ���
    @Column(length = 50, nullable = false)
    private String institute;
    
    //������
    @Column(length = 20, nullable = false)
    private String mgmt;
    
    //�����
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
