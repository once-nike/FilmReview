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
}
