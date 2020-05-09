package com.nike.douye.api;

import com.github.pagehelper.PageInfo;
import com.nike.douye.Enum.Code;
import com.nike.douye.annotation.AdminToken;
import com.nike.douye.annotation.CheckToken;
import com.nike.douye.dto.AddFilmDTO;
import com.nike.douye.dto.FilmDTO;
import com.nike.douye.dto.ResponseDTO;
import com.nike.douye.dto.UserDTO;
import com.nike.douye.exception.BaseException;
import com.nike.douye.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/film")
public class FilmApi {

	@Autowired
	FilmService filmService;
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@AdminToken
	public ResponseDTO<String> addFilm(@Validated @RequestBody AddFilmDTO addFilmDTO){
		filmService.addFilm(addFilmDTO);
		return new ResponseDTO<>(Code.SUCCESS.getValue(),"添加成功啦");
	}

	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@AdminToken
	public ResponseDTO<String> updateFilm(@Validated @RequestBody FilmDTO filmDTO){
		filmService.updateFilm(filmDTO);
		return new ResponseDTO<>(Code.SUCCESS.getValue(),"更新成功啦");
	}

	@RequestMapping(value = "/list/all",method = RequestMethod.POST)
	@CheckToken
	public ResponseDTO<PageInfo<List<FilmDTO>>> getFilmList(@RequestBody FilmDTO filmDTO){
		PageInfo<List<FilmDTO>> allFilms = filmService.getAllFilms(filmDTO);
		return new ResponseDTO(Code.SUCCESS.getValue(),allFilms);
	}

	@RequestMapping(value = "/list/byFilmName",method = RequestMethod.POST)
	@CheckToken
	public ResponseDTO<PageInfo<List<FilmDTO>>> getFilmByFilmName(@RequestBody FilmDTO filmDTO){
		PageInfo<List<FilmDTO>> filmByName = filmService.getFilmByName(filmDTO);
		return new ResponseDTO(Code.SUCCESS.getValue(),filmByName);
	}

	@RequestMapping(value = "/list/byIsNew",method = RequestMethod.POST)
	@CheckToken
	public ResponseDTO<PageInfo<List<FilmDTO>>> getFilmByIsNew(@RequestBody FilmDTO filmDTO){
		PageInfo<List<FilmDTO>> filmByIsNew = filmService.getFilmByIsNew(filmDTO);
		return new ResponseDTO(Code.SUCCESS.getValue(),filmByIsNew);
	}

	@RequestMapping(value = "/list/byType",method = RequestMethod.POST)
	@CheckToken
	public ResponseDTO<PageInfo<List<FilmDTO>>> getFilmByType(@RequestBody FilmDTO filmDTO){
		PageInfo<List<FilmDTO>> filmByType = filmService.getFilmByType(filmDTO);
		return new ResponseDTO(Code.SUCCESS.getValue(),filmByType);
	}

	@RequestMapping(value = "/collect/film",method = RequestMethod.GET)
	@CheckToken
	public ResponseDTO<PageInfo<List<FilmDTO>>> collectFilm(@RequestParam Integer filmId){
		if (filmId == null){
			throw new BaseException("filmId不可以为空哦",Code.PARAM_MISSING.getValue());
		}
		filmService.collectFilm(filmId);
		return new ResponseDTO(Code.SUCCESS.getValue(),"电影收藏成功啦");
	}

	@RequestMapping(value = "/show/filmCollection",method = RequestMethod.POST)
	@CheckToken
	public ResponseDTO<PageInfo<List<FilmDTO>>> showfilmCollection(@RequestBody FilmDTO filmDTO){
		if (filmDTO == null){
			throw new BaseException("filmDTO不可以为空哦",Code.PARAM_MISSING.getValue());
		}
		PageInfo<List<FilmDTO>> filmCollection = filmService.getFilmCollection(filmDTO);
		return new ResponseDTO(Code.SUCCESS.getValue(),filmCollection);
	}

	@RequestMapping(value = "/delete/colliection",method = RequestMethod.GET)
	@CheckToken
	public ResponseDTO<String> deleteCollection(@RequestParam Integer filmId){
		if (filmId == null){
			throw new BaseException("filmId不可以为空哦",Code.PARAM_MISSING.getValue());
		}
		filmService.deleteCollection(filmId);
		return new ResponseDTO(Code.SUCCESS.getValue(),"删除成功");
	}
}
