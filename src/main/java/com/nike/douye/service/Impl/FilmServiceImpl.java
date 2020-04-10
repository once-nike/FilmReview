package com.nike.douye.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nike.douye.dto.FilmDTO;
import com.nike.douye.mapper.FilmMapper;
import com.nike.douye.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FilmServiceImpl implements FilmService {
	@Autowired
	private FilmMapper filmMapper;
	@Override
	public void addFilm(FilmDTO filmDTO) {
		filmMapper.insertFilm(filmDTO);
	}

	@Override
	public void updateFilm(FilmDTO filmDTO) {
		filmMapper.updateFilm(filmDTO);
	}

	@Override
	public PageInfo<List<FilmDTO>> getFilmByName(FilmDTO filmDTO) {
		PageHelper.startPage(filmDTO.getPageNum(),filmDTO.getPageSize());
		List<FilmDTO> filmDTOs = filmMapper.queryFilmByFilmName(filmDTO.getFilmName());
		return new PageInfo(filmDTOs);
	}

	@Override
	public PageInfo<List<FilmDTO>> getAllFilms(FilmDTO filmDTO){
		PageHelper.startPage(filmDTO.getPageNum(),filmDTO.getPageSize());
		List<FilmDTO> filmDTOs = filmMapper.queryAllFilms();
		return new PageInfo(filmDTOs);
	}
}
