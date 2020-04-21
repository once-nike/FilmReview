package com.nike.douye.mapper;

import com.nike.douye.dto.FilmDTO;
import com.nike.douye.entity.FilmInformation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FilmMapper {

	/**
	 * 添加电影信息
	 * @param filmDTO
	 */
	void insertFilm(@Param("filmDTO") FilmDTO filmDTO, @Param("filmInformation")FilmInformation filmInformation);

	/**
	 * 更新电影信息
	 * @param filmDTO
	 */
	void updateFilm(@Param("filmDTO") FilmDTO filmDTO,@Param("filmInformation")FilmInformation filmInformation);

	/**
	 * 查询所有电影信息
	 * @return
	 */
	List<FilmDTO> queryAllFilms();

	/**
	 * 查询电影类别、国家、语言
	 * @return
	 */
	List<FilmInformation> queryInformationOfAllFilm();

	/**
	 * 根据电影名字查询电影信息
	 * @param filmName
	 * @return
	 */
	List<FilmDTO> queryFilmByFilmName(@Param("filmName") String filmName);

	/**
	 * 根据电影名字查询电影类别、国家、语言
	 * @param filmName
	 * @return
	 */
	List<FilmInformation> queryInformationOfFilm(@Param("filmName") String filmName);

	/**
	 * 查询所有新电影
	 * @param isNew
	 * @return
	 */
	List<FilmDTO> queryFilmByIsNew(@Param("isNew") char isNew);

	/**
	 * 查询所有新电影的电影类别、国家、语言
	 * @param isNew
	 * @return
	 */
	List<FilmInformation> queryInformationOfFilmByIsNew(@Param("isNew") char isNew);

	/**
	 * 根据类型查询电影
	 * @param filmInformation
	 * @return
	 */
	List<FilmDTO> queryFilmByType(FilmInformation filmInformation);

	/**
	 * 根据类型查询电影类别、国家、语言
	 * @param filmInformation
	 * @return
	 */
	List<FilmInformation> queryInformationOfFilmByType(FilmInformation filmInformation);

	/**
	 * 根据电影名称查询电影id
	 * @param filmName
	 * @return
	 */
	Integer queryFilmIdByFilmName(@Param("filmName")String filmName);

	/**
	 * 添加电影评分信息记录
	 * @param filmId
	 */
	void insertScore(@Param("filmId")Integer filmId);
}
