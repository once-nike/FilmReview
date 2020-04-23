package com.nike.douye.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmCommentDTO {
	Integer filmId;

	List<CommentDTO> comments;

	public FilmCommentDTO(List<CommentDTO> comments) {
		this.comments = comments;
	}
}
