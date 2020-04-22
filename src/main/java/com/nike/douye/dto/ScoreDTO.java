package com.nike.douye.dto;

import com.nike.douye.Enum.Score;
import com.nike.douye.entity.ScorePo;
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

	ScorePo scorePo;

	public ScoreDTO(Integer filmId, Score myScore) {
		this.filmId = filmId;
		this.myScore = myScore;
	}

	public ScoreDTO(Integer filmId) {
		this.filmId = filmId;
	}

	public ScoreDTO(Score myScore, ScorePo scorePo) {
		this.myScore = myScore;
		this.scorePo = scorePo;
	}
}
