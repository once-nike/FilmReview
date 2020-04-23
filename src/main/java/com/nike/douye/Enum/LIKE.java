package com.nike.douye.Enum;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public enum LIKE {

	LIKE("y"),
	DISLIKE("n");

	@Getter
	@Setter
	private String value;

	@JsonValue
	public String getValue() {
		return value;
	}

}
