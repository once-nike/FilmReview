package com.nike.douye.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmInformation {
	Integer id;
	String filmType;
	String filmCountry;
	String filmLanguage;

	public FilmInformation(String filmType, String filmCountry, String filmLanguage) {
		this.filmType = filmType;
		this.filmCountry = filmCountry;
		this.filmLanguage = filmLanguage;
	}
}
