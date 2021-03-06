package com.nike.douye.service;

import com.nike.douye.Enum.LIKE;
import com.nike.douye.dto.CommentDTO;
import com.nike.douye.dto.FilmCommentDTO;
import com.nike.douye.dto.SonCommentDTO;

public interface CommentService {

	/**
	 * 添加评论
	 * @param commentDTO
	 */
	void insertFatherComment(CommentDTO commentDTO,Integer filmId);

	/**
	 * 添加子评论
	 * @param commentDTO
	 * @param fatherId
	 */
	void insertSonComment(CommentDTO commentDTO,Integer fatherId);

	/**
	 * 根据电影查看影评
	 * @param filmId
	 * @return
	 */
	FilmCommentDTO queryCommentByFilmId(Integer filmId);

	/**
	 * 根据父评论id查询子评论
	 * @param fatherId
	 * @return
	 */
	SonCommentDTO queryCommentByFatherId(Integer fatherId);

	/**
	 * 查看个人影评记录
	 * @return
	 */
	FilmCommentDTO queryCommentRecord();

	/**
	 * 更新父评论赞和踩
	 * @param commentDTO
	 * @return
	 */
	void updateCommentLike(CommentDTO commentDTO);

	/**
	 * 更新子评论赞和踩
	 * @param commentDTO
	 * @return
	 */
	void updateSonCommentLike(CommentDTO commentDTO);

	/**
	 * 收藏影评
	 * @param commentId
	 */
	void collectComment(Integer commentId);

	/**
	 * 查看个人收藏影评
	 * @return
	 */
	FilmCommentDTO queryCommentCollection();

	/**
	 * 删除个人影评收藏
	 * @param commentId
	 */
	void deleteMyCollection(Integer commentId);
}
