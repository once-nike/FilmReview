package com.nike.douye.service;

import com.nike.douye.Enum.Score;

public interface ScoreService {

	/**
	 * 打分
	 * @param score
	 */
	void scoring(Score score,Integer filmId);
}
