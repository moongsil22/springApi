package com.siri.springapi.web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.siri.springapi.domain.SupportInfo;
import com.siri.springapi.domain.SupportInfoRepository;
import com.siri.springapi.domain.SupportRegRepository;
import com.siri.springapi.dto.CsvReqDto;
import com.siri.springapi.dto.SupportReqDto;
import com.siri.springapi.service.SupportInfoService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@RestController
@AllArgsConstructor
public class SupportInfoController {

    private SupportInfoService supportInfoService;
    private SupportInfoRepository supportInfoRepository;
    private SupportRegRepository supportRegRepository;
   	private static final String SAMPLE_CSV_FILE_PATH = "C:/git/springApi/csv/사전과제1.csv";


    @GetMapping("/s/hello")
    public String hello() {
        return "HelloWorld";
    }
    
    @GetMapping("/s/hello/json")
    @ResponseBody
    public Hello helloJson() {
    	Hello hello = new Hello();
    	hello.message = "helloworld";
        return hello;
    }

    @PostMapping("/s/save")
    public void save() throws UnsupportedEncodingException, FileNotFoundException{
    	  
    	
    	CSVReader reader = new CSVReaderBuilder(new InputStreamReader(new FileInputStream(SAMPLE_CSV_FILE_PATH), "utf-8"))
                  .withSkipLines(1)
                  .build();	
    	  
    	  CsvToBean<CsvReqDto> csvToBean = new CsvToBeanBuilder(reader)
                  .withType(CsvReqDto.class)
                  .withIgnoreLeadingWhiteSpace(true)
                  .build();

       // Reads all CSV contents into memory (Not suitable for large CSV files)
          List<CsvReqDto> dtos = csvToBean.parse();
     
          for(CsvReqDto dto: dtos) {
        
        	  System.out.println("==========================");
        	  System.out.println("지자체명 : " + dto.getRegion());
              System.out.println("지원대상 : " + dto.getTarget());
              System.out.println("용도 : " + dto.getUsage());
              System.out.println("==========================");        	  
        	  
              
              supportRegRepository.save(dto.toSupportRegEntity());
              supportInfoRepository.save(dto.toSupportInfoEntity());
             // supportInfoService.saveReg(dto.toSupportRegEntity());
              
            //  supportInfoService.saveInfo(dto.toSupportInfoEntity());

        	  
          }
		//return supportInfoRepository.findAll(); 
          
    
       /* Connection conn = null;
        Statement stmt = null;
 
        Class.forName("org.h2.Driver");
        conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
       // conn = DriverManager.getConnection("jdbc:h2:~/test", "", "");
        stmt = conn.createStatement();
 
        stmt.execute("drop table if exists csvdata");
        stmt.execute("create table csvdata (id int primary key, name varchar(100), age int)");
        stmt.execute("insert into csvdata ( id, name, age )     select convert( \"id\",int ), \"name\", convert( \"age\", int)   from CSVREAD( 'C:\\git\\springApi\\csv\\test.csv', 'id,name,age', null ) ");
        ResultSet rs = stmt.executeQuery("select * from csvdata");
 
        while (rs.next()) {
            System.out.println("id " + rs.getInt("id") + " name " + rs.getString("name") + " age " + rs.getInt("age") );
        }
        stmt.close();
    	*/
    }
    
    @Setter
    @Getter
    public static class Hello{
        private String message;
    }
}