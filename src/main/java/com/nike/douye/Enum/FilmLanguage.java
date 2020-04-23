package com.nike.douye.Enum;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
public enum  FilmLanguage {

	ENGLISH("英语"),
	CHINAESE("中文"),
	RUSSIAN("俄语"),
	JAPANESE("日语"),
	KOREAN("韩语"),
	HINDI("印度语"),
	THAI("泰语");

	@Getter
	@Setter
	private String value;


	@JsonValue
	public String getValue() {
		return value;
	}

	public static List<FilmLanguage> stringToEnum(List<String> strs){
		List filmLanguages = new ArrayList<FilmLanguage>();
		for(String str : strs){
			switch (str){
				case "英语":
					filmLanguages.add(ENGLISH);
					break;
				case "中文":
					filmLanguages.add(CHINAESE);
					break;
				case "俄语":
					filmLanguages.add(RUSSIAN);
					break;
				case "日语":
					filmLanguages.add(JAPANESE);
					break;
				case "韩语":
					filmLanguages.add(KOREAN);
					break;
				case "印度语":
					filmLanguages.add(HINDI);
					break;
				case "泰语":
					filmLanguages.add(THAI);
					break;
				default:
					break;
			}
		}
		return filmLanguages;
	}
}
