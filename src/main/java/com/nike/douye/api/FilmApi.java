package com.nike.douye.api;

import com.github.pagehelper.PageInfo;
import com.nike.douye.Enum.Code;
import com.nike.douye.dto.FilmDTO;
import com.nike.douye.dto.ResponseDTO;
import com.nike.douye.dto.UserDTO;
import com.nike.douye.exception.BaseException;
import com.nike.douye.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/film")
public class FilmApi {

	@Autowired
	FilmService filmService;
	@RequestMapping("/add")
	public ResponseDTO<String> addFilm(@Validated @RequestBody FilmDTO filmDTO){
		filmService.addFilm(filmDTO);
		return new ResponseDTO<>(Code.SUCCESS.getValue(),"添加成功啦");
	}

	@RequestMapping("/update")
	public ResponseDTO<String> updateFilm(@Validated @RequestBody FilmDTO filmDTO){
		filmService.updateFilm(filmDTO);
		return new ResponseDTO<>(Code.SUCCESS.getValue(),"更新成功啦");
	}

	@RequestMapping("/list/all")
	public ResponseDTO<PageInfo<List<FilmDTO>>> getFilmList(@RequestBody FilmDTO filmDTO){
		PageInfo<List<FilmDTO>> allFilms = filmService.getAllFilms(filmDTO);
		return new ResponseDTO(Code.SUCCESS.getValue(),allFilms);
	}

	@RequestMapping("/list/byFilmName")
	public ResponseDTO<PageInfo<List<FilmDTO>>> getFilmByFilmName(@RequestBody FilmDTO filmDTO){
		PageInfo<List<FilmDTO>> filmByName = filmService.getFilmByName(filmDTO);
		return new ResponseDTO(Code.SUCCESS.getValue(),filmByName);
	}

	@RequestMapping("/list/byIsNew")
	public ResponseDTO<PageInfo<List<FilmDTO>>> getFilmByIsNew(@RequestBody FilmDTO filmDTO){
		PageInfo<List<FilmDTO>> filmByIsNew = filmService.getFilmByIsNew(filmDTO);
		return new ResponseDTO(Code.SUCCESS.getValue(),filmByIsNew);
	}

	@RequestMapping("/list/byType")
	public ResponseDTO<PageInfo<List<FilmDTO>>> getFilmByType(@RequestBody FilmDTO filmDTO){
		PageInfo<List<FilmDTO>> filmByType = filmService.getFilmByType(filmDTO);
		return new ResponseDTO(Code.SUCCESS.getValue(),filmByType);
	}
}
