package com.nike.douye.mapper;

import com.nike.douye.dto.FilmDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FilmMapper {

	/**
	 * 添加电影信息
	 * @param filmDTO
	 */
	void insertFilm(@Param("filmDTO") FilmDTO filmDTO);

	/**
	 * 更新电影信息
	 * @param filmDTO
	 */
	void updateFilm(@Param("filmDTO") FilmDTO filmDTO);

	/**
	 * 查询所有电影信息
	 * @return
	 */
	List<FilmDTO> queryAllFilms();

	/**
	 * 根据电影名字查询电影信息
	 * @param filmName
	 * @return
	 */
	List<FilmDTO> queryFilmByFilmName(@Param("filmName") String filmName);
}
