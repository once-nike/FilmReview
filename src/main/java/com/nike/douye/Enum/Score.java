package com.nike.douye.Enum;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public enum Score {

	SCORE_ONE(1),
	SCORE_TWO(2),
	SCORE_THREE(3),
	SCORE_FOUR(4),
	SCORE_FIVE(5);

	@Getter
	@Setter
	private Integer value;

	@JsonValue
	public Integer getValue() {
		return value;
	}
}
