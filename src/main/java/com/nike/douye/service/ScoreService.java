package com.nike.douye.service;

import com.nike.douye.Enum.Score;
import com.nike.douye.dto.ScoreDTO;

public interface ScoreService {

	/**
	 * 打分
	 * @param score
	 */
	void scoring(Score score,Integer filmId);

	/**
	 * 展示电影评分
	 * @param scoreDTO
	 * @return
	 */
	ScoreDTO showScore(ScoreDTO scoreDTO);
}
