package com.nike.douye.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentPo {
	Integer commentId;

	Integer filmId;

	Integer userId;

	String comment;

	Integer likes;

	Integer dislikes;

	Timestamp commentTime;
}
