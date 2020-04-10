package com.nike.douye.Enum;

public enum  FilmLanguage {

	ENGLISH("英语"),
	CHINAESE("中文"),
	RUSSIAN("俄语"),
	JAPANESE("日语"),
	KOREAN("韩语"),
	HINDI("印度语"),
	THAI("泰语");


	private final String value;


	public String getValue() {
		return value;
	}

	FilmLanguage(String value) {
		this.value = value;
	}
}
