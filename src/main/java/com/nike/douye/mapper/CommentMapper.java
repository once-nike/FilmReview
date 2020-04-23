package com.nike.douye.mapper;

import com.nike.douye.entity.CommentPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

	/**
	 * 添加评论
	 * @param commentPo
	 */
	void insertComment(CommentPo commentPo);

	/**
	 * 添加子评论
	 * @param commentPo
	 */
	void insertSonComment(CommentPo commentPo);

	/**
	 * 添加个人评论记录
	 * @param userId
	 * @param commentId
	 */
	void insertMyCommentRecord(@Param("userId")Integer userId,@Param("commentId")Integer commentId);

	/**
	 * 根据用户id和电影id查询CommentId
	 * @param userId
	 * @param filmId
	 * @return
	 */
	List<Integer> queryCommentIdByUserIdAndFilmId(@Param("userId")Integer userId,@Param("filmId")Integer filmId);

	/**
	 * 根据电影id查询评论信息
	 * @param filmId
	 * @return
	 */
	List<CommentPo> queryCommentByFilmId(@Param("filmId")Integer filmId);

	/**
	 * 根据用户id查询影评
	 * @param userId
	 * @return
	 */
	List<CommentPo> queryCommentByUserId(@Param("userId")Integer userId);

	/**
	 *
	 * @param like
	 * @return
	 */
	void updateCommentLike(@Param("like")String like,@Param("commentId")Integer commentId);

	/**
	 *
	 * @param like
	 * @return
	 */
	void updateSonCommentLike(@Param("like")String like,@Param("commentId")Integer commentId);

	/**
	 * 根据父评论id查询子评论
	 * @param fatherId
	 * @return
	 */
	List<CommentPo> querySonCommentByFatherId(@Param("fatherId")Integer fatherId);

	/**
	 * 添加评论收藏
	 * @param commentId
	 */
	void insertCommentCollection(@Param("userId")Integer userId,@Param("commentId")Integer commentId);

	/**
	 * 根据userid查询个人收藏影评
	 * @param userId
	 * @return
	 */
	List<CommentPo> queryCommentCollectionByUserId(@Param("userId")Integer userId);

	/**
	 * 删除个人收藏
	 * @param commentId
	 */
	void deleteCollectionById(@Param("commentId")Integer commentId,@Param("userId")Integer userId);
}
