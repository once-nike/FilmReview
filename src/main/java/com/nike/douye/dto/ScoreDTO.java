package com.nike.douye.dto;

import com.nike.douye.Enum.Score;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScoreDTO {
	Integer filmId;

	Score myScore;
}
