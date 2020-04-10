package com.nike.douye.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@NotBlank
public class FilmDTO {
	Integer id;

	String filmName;

	String filmDirector;

	String filmWriter;

	String filmActor;

	String filmType;

	String filmCountry;

	String filmLanguage;

	Date filmDate;

	String filmLength;

	String filmIntroduction;

	String filmPicture;

	char isNew;

	@NotNull
	Integer pageNum;
	@NotNull
	Integer pageSize;

	public FilmDTO(Integer pageNum, Integer pageSize) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}

	public FilmDTO(String filmName, Integer pageNum, Integer pageSize) {
		this.filmName = filmName;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}
}
