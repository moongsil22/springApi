package com.siri.springapi.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.siri.springapi.dto.PostsSaveRequestDto;


 
public class CsvRead {
 
	private static final String SAMPLE_CSV_FILE_PATH = "C:/git/springApi/csv/테스트1.csv";
 
    public CsvRead() {
    }
    
    public static void main(String[] args) throws IOException {
        try (
            //Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
        		//Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));	
        		//// UTF-8
                 CSVReader reader = new CSVReaderBuilder(new InputStreamReader(new FileInputStream(SAMPLE_CSV_FILE_PATH), "EUC-KR"))
                         .withSkipLines(1)
                         .build();
        ) {
            CsvToBean<PostsSaveRequestDto> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(PostsSaveRequestDto.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

         // Reads all CSV contents into memory (Not suitable for large CSV files)
            List<PostsSaveRequestDto> dtos = csvToBean.parse();
            
            System.out.println(dtos.toString());

            for(PostsSaveRequestDto dto: dtos) {
                System.out.println("저자 : " + dto.getAuthor());
                System.out.println("이름 : " + dto.getContent());
                System.out.println("제목 : " + dto.getTitle());
                System.out.println("==========================");
            }
        }
    }
}
