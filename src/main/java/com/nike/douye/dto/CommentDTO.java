package com.nike.douye.dto;

import com.nike.douye.Enum.LIKE;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
	Integer commentId;

	Integer userId;

	String comment;

	Integer likes;

	Integer dislikes;

	Timestamp commentTime;

	LIKE like;
	public CommentDTO(String comment) {
		this.comment = comment;
	}

	public CommentDTO(Integer commentId, Integer userId, String comment, Integer likes, Integer dislikes, Timestamp commentTime) {
		this.commentId = commentId;
		this.userId = userId;
		this.comment = comment;
		this.likes = likes;
		this.dislikes = dislikes;
		this.commentTime = commentTime;
	}

	public CommentDTO(Integer commentId, LIKE like) {
		this.commentId = commentId;
		this.like = like;
	}
}
