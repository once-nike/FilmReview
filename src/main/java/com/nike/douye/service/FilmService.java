package com.nike.douye.service;

import com.github.pagehelper.PageInfo;
import com.nike.douye.dto.FilmDTO;

import java.util.List;

public interface FilmService {

	/**
	 * 添加电影信息
	 * @param filmDTO
	 */
	void addFilm(FilmDTO filmDTO);

	/**
	 * 更新电影信息
	 * @param filmDTO
	 */
	void updateFilm(FilmDTO filmDTO);

	/**
	 * 查询所有电影信息
	 * @return
	 */
	PageInfo<List<FilmDTO>> getAllFilms(FilmDTO filmDTO);

	/**
	 * 根据电影名查询电影信息
	 * @param filmDTO
	 * @return
	 */
	PageInfo<List<FilmDTO>> getFilmByName(FilmDTO filmDTO);

	/**
	 * 查询所有新电影
	 * @param filmDTO
	 * @return
	 */
	PageInfo<List<FilmDTO>> getFilmByIsNew(FilmDTO filmDTO);

	/**
	 * 根据类型搜索电影
	 * @param filmDTO
	 * @return
	 */
	PageInfo<List<FilmDTO>> getFilmByType(FilmDTO filmDTO);

	/**
	 * 收藏电影
	 * @param filmId
	 */
	void collectFilm(Integer filmId);

	/**
	 * 展示电影收藏
	 * @return
	 */
	PageInfo<List<FilmDTO>> getFilmCollection(FilmDTO filmDTO);

	/**
	 * 删除电影收藏
	 * @param filmId
	 */
	void deleteCollection(Integer filmId);
}
