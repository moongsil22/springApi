package com.siri.springapi.web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.siri.springapi.domain.PostsRepository;
import com.siri.springapi.dto.PostsSaveRequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@RestController
@AllArgsConstructor
public class WebRestController {

    private PostsRepository postsRepository;
   	private static final String SAMPLE_CSV_FILE_PATH = "C:/git/springApi/csv/테스트1.csv";


    @GetMapping("/hello")
    public String hello() {
        return "HelloWorld";
    }
    
    @GetMapping("/hello/json")
    @ResponseBody
    public Hello helloJson() {
    	Hello hello = new Hello();
    	hello.message = "helloworld";
        return hello;
    }

    @PostMapping("/posts")
    public void savePosts(@RequestBody PostsSaveRequestDto dto){
        postsRepository.save(dto.toEntity());
    }
    
    @PostMapping("/save")
    public List<PostsSaveRequestDto> save() throws UnsupportedEncodingException, FileNotFoundException{
    	  
    	
    	CSVReader reader = new CSVReaderBuilder(new InputStreamReader(new FileInputStream(SAMPLE_CSV_FILE_PATH), "EUC-KR"))
                  .withSkipLines(1)
                  .build();	
    	  
    	  CsvToBean<PostsSaveRequestDto> csvToBean = new CsvToBeanBuilder(reader)
                  .withType(PostsSaveRequestDto.class)
                  .withIgnoreLeadingWhiteSpace(true)
                  .build();

       // Reads all CSV contents into memory (Not suitable for large CSV files)
          List<PostsSaveRequestDto> dtos = csvToBean.parse();
     
          for(PostsSaveRequestDto dto: dtos) {
        
        	  System.out.println("==========================");
        	  System.out.println("저자 : " + dto.getAuthor());
              System.out.println("이름 : " + dto.getContent());
              System.out.println("제목 : " + dto.getTitle());
              System.out.println("==========================");        	  
        	  
        	  postsRepository.save(dto.toEntity());
        	  
          }
		return dtos; 
          
    
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