package com.siri.springapi.dto;

import com.siri.springapi.domain.Posts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.opencsv.bean.CsvBindByPosition;


@Getter
@Setter
@NoArgsConstructor
public class PostsSaveRequestDto {

	@CsvBindByPosition(position = 0)
    private String title;
	@CsvBindByPosition(position = 1)
    private String content;
	@CsvBindByPosition(position = 2)
    private String author;

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}