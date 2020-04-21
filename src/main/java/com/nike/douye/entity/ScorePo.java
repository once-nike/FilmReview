package com.nike.douye.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScorePo {
	Integer id;

	Integer filmId;

	Integer scoreFive;

	Integer scoreFour;

	Integer scoreThree;

	Integer scoreTwo;

	Integer scoreOne;

	double totalScore;
}
