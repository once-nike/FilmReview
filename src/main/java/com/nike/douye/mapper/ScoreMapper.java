package com.nike.douye.mapper;

import com.nike.douye.entity.ScorePo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ScoreMapper {
	/**
	 * 根据电影id添加我的评分和总分
	 * @param score
	 * @param userId
	 * @param filmId
	 */
	void addScore(@Param("score") Integer score,@Param("filmId") Integer filmId,@Param("userId") Integer userId);

	/**
	 * 根据电影id查询分数信息
	 * @param filmId
	 * @return
	 */
	ScorePo findScoreInformationByFilmId(@Param("filmId") Integer filmId);

	/**
	 * 更新电影评分总表
	 * @param scorePo
	 */
	void updateScore(@Param("scorePo") ScorePo scorePo);

	/**
	 * 根据用户id和film id查询有无评分
	 * @param userId
	 * @param filmId
	 * @return
	 */
	Integer checkUserScore(@Param("userId")Integer userId,@Param("filmId")Integer filmId);

	/**
	 * 根据用户id和电影id查询我的评分
	 * @param userId
	 * @param filmId
	 * @return
	 */
	Integer queryMyScoreByUserIdAndFilmId(@Param("userId")Integer userId,@Param("filmId")Integer filmId);
}
