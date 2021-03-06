package com.nike.douye.service.Impl;

import com.auth0.jwt.JWT;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nike.douye.Enum.Code;
import com.nike.douye.Enum.FilmCountry;
import com.nike.douye.Enum.FilmLanguage;
import com.nike.douye.Enum.FilmType;
import com.nike.douye.ValidGroup.ValidGroupA;
import com.nike.douye.dto.AddFilmDTO;
import com.nike.douye.dto.FilmDTO;
import com.nike.douye.entity.FilmInformation;
import com.nike.douye.exception.BaseException;
import com.nike.douye.mapper.FilmMapper;
import com.nike.douye.service.FilmService;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {
	@Autowired
	private FilmMapper filmMapper;

	private String path = "C:\\Users\\86153\\Desktop\\myGit\\FilmReview\\src\\main\\resources\\static\\";
	@Autowired
	private HttpServletRequest request;
	@Override
	public void addFilm(AddFilmDTO addFilmDTO) {
		FilmDTO filmDTO = new FilmDTO(addFilmDTO.getFilmName(),addFilmDTO.getFilmDirector(),addFilmDTO.getFilmWriter(),
				addFilmDTO.getFilmActor(),addFilmDTO.getFilmType(),addFilmDTO.getFilmCountry(),addFilmDTO.getFilmLanguage(),
				addFilmDTO.getFilmDate(),addFilmDTO.getFilmLength(),addFilmDTO.getFilmIntroduction(),"",
				addFilmDTO.getIsNew());
		FilmDTO film = enumToString(filmDTO);
		List<FilmType> filmTypeList = film.getFilmType();
		String filmType = StringUtils.join(filmTypeList, ",");
		List<FilmCountry> filmCountryList = film.getFilmCountry();
		String filmCountry = StringUtils.join(filmCountryList, ",");
		List<FilmLanguage> filmLanguageList = film.getFilmLanguage();
		String filmLanguage = StringUtils.join(filmLanguageList, ",");

		film.setFilmPicture("http://localhost:9520/film/"+addFilmDTO.getFilmName()+".jpg");
		filmMapper.insertFilm(film,new FilmInformation(filmType,filmCountry,filmLanguage));
		Integer filmId = filmMapper.queryFilmIdByFilmName(filmDTO.getFilmName());
		filmMapper.insertScore(filmId);
	}

	@Override
	public void addPicture(MultipartFile file) throws Exception{
		String originalFilename = file.getOriginalFilename();
		System.out.println("--------------------------------"+originalFilename);
		InputStream inputStream = file.getInputStream();
		int i= 0;
		File file1 = new File(path+originalFilename);
		FileOutputStream outputStream = new FileOutputStream(file1);
		while ((i=inputStream.read()) != -1){
			outputStream.write(i);
		}
		inputStream.close();
		outputStream.close();
	}


	@Override
	public void updateFilm(FilmDTO filmDTO) {
		FilmDTO film = enumToString(filmDTO);
		List<FilmType> filmTypeList = film.getFilmType();
		String filmType = StringUtils.join(filmTypeList, ",");
		List<FilmCountry> filmCountryList = film.getFilmCountry();
		String filmCountry = StringUtils.join(filmCountryList, ",");
		List<FilmLanguage> filmLanguageList = film.getFilmLanguage();
		String filmLanguage = StringUtils.join(filmLanguageList, ",");
		filmMapper.updateFilm(film,new FilmInformation(filmType,filmCountry,filmLanguage));
	}

	@Override
	public PageInfo<List<FilmDTO>> getFilmByName(FilmDTO filmDTO) {
		PageHelper.startPage(filmDTO.getPageNum(),filmDTO.getPageSize());
		//拿到用电影查询出的所有电影信息
		List<FilmDTO> filmDTOs = filmMapper.queryFilmByFilmName(filmDTO.getFilmName());
		List<FilmInformation> filmInformations = filmMapper.queryInformationOfFilm(filmDTO.getFilmName());
		if(!isEmpty(filmDTOs)&&!isEmpty(filmInformations)){
			filmDTOs = fuse(filmDTOs, filmInformations);
		}else {
			throw new BaseException("未找到相关电影，请确认您输入的名称", Code.PARAM_ERROR.getValue());
		}
		return new PageInfo(filmDTOs);
	}

	@Override
	public PageInfo<List<FilmDTO>> getFilmByIsNew(FilmDTO filmDTO) {
		PageHelper.startPage(filmDTO.getPageNum(),filmDTO.getPageSize());
		List<FilmDTO> filmDTOS = filmMapper.queryFilmByIsNew(filmDTO.getIsNew());
		List<FilmInformation> filmInformations = filmMapper.queryInformationOfFilmByIsNew(filmDTO.getIsNew());
		if(!isEmpty(filmDTOS)&&!isEmpty(filmInformations)){
			filmDTOS = fuse(filmDTOS, filmInformations);
		}else {
			throw new BaseException("暂无新增电影", Code.PARAM_ERROR.getValue());
		}
		return new PageInfo(filmDTOS);
	}

	@Override
	public PageInfo<List<FilmDTO>> getFilmByType(FilmDTO filmDTO) {
		FilmDTO film = enumToString(filmDTO);
		List<FilmType> filmTypeList = film.getFilmType();
		if(filmTypeList!=null && filmTypeList.size()>1){
			throw new BaseException("电影类型只能选一个",Code.PARAM_TOO_LONG.getValue());
		}
		String filmType = StringUtils.join(filmTypeList, "");
		List<FilmCountry> filmCountryList = film.getFilmCountry();
		if(filmCountryList!=null && filmCountryList.size()>1){
			throw new BaseException("电影国家只能选一个",Code.PARAM_TOO_LONG.getValue());
		}
		String filmCountry = StringUtils.join(filmCountryList, "");
		List<FilmLanguage> filmLanguageList = film.getFilmLanguage();
		if(filmLanguageList!=null && filmLanguageList.size()>1){
			throw new BaseException("电影语言只能选一个",Code.PARAM_TOO_LONG.getValue());
		}
		String filmLanguage = StringUtils.join(filmLanguageList, "");
		FilmInformation filmInformation = new FilmInformation(filmType, filmCountry, filmLanguage);
		List<FilmDTO> filmDTOS = filmMapper.queryFilmByType(filmInformation);
		List<FilmInformation> filmInformations = filmMapper.queryInformationOfFilmByType(filmInformation);
		List<FilmDTO> fuse = fuse(filmDTOS, filmInformations);
		return new PageInfo(fuse);
	}

	@Override
	public void collectFilm(Integer filmId) {
		//取userId
		String token = request.getHeader("token");
		Integer userId = Integer.valueOf(JWT.decode(token).getClaim("id").asString());
		filmMapper.insertCollection(userId,filmId);
	}

	@Override
	public PageInfo<List<FilmDTO>> getFilmCollection(FilmDTO filmDTO) {
		PageHelper.startPage(filmDTO.getPageNum(),filmDTO.getPageSize());
		//取userId
		String token = request.getHeader("token");
		Integer userId = Integer.valueOf(JWT.decode(token).getClaim("id").asString());
		List<FilmDTO> filmDTOS = filmMapper.queryCollectionByUserId(userId);
		List<FilmInformation> filmInformations = filmMapper.queryCollectionTypesByUserId(userId);
		List<FilmDTO> fuse = fuse(filmDTOS, filmInformations);
		return new PageInfo(fuse);
	}

	@Override
	public void deleteCollection(Integer filmId) {
		//取userId
		String token = request.getHeader("token");
		Integer userId = Integer.valueOf(JWT.decode(token).getClaim("id").asString());
		filmMapper.deleteCollection(filmId,userId);
	}

	@Override
	public PageInfo<List<FilmDTO>> getAllFilms(FilmDTO filmDTO){
		PageHelper.startPage(filmDTO.getPageNum(),filmDTO.getPageSize());
		List<FilmDTO> filmDTOs = filmMapper.queryAllFilms();
		List<FilmInformation> filmInformations = filmMapper.queryInformationOfAllFilm();
		if(!isEmpty(filmDTOs)&&!isEmpty(filmInformations)){
			filmDTOs = fuse(filmDTOs, filmInformations);
		}else {
			throw new BaseException("暂无电影信息", Code.DATA_NOT_EXIT.getValue());
		}
		return new PageInfo(filmDTOs);
	}



	//枚举转化成字符串
	public FilmDTO enumToString(FilmDTO filmDTO){
		List<FilmType> filmTypeEnums = filmDTO.getFilmType();
		List<FilmCountry> filmCountryEnums = filmDTO.getFilmCountry();
		List<FilmLanguage> filmLanguageEnums = filmDTO.getFilmLanguage();
		//如果前端传入的枚举集合不是空，则将枚举转化成字符串
		if (filmCountryEnums!=null){
			List filmCountry = new ArrayList(filmCountryEnums.size());
			for (FilmCountry filmCountryEnum : filmCountryEnums){
				filmCountry.add(filmCountryEnum.getValue());
			}
			filmDTO.setFilmCountry(filmCountry);
		}

		if (filmTypeEnums!=null){
			List filmType = new ArrayList(filmTypeEnums.size());
			for (FilmType filmTypeEnum : filmTypeEnums){
				filmType.add(filmTypeEnum.getValue());
			}
			filmDTO.setFilmType(filmType);
		}

		if(filmLanguageEnums!=null){
			List filmLanguage = new ArrayList(filmLanguageEnums.size());
			for (FilmLanguage filmLanguageEnum : filmLanguageEnums){
				filmLanguage.add(filmLanguageEnum.getValue());
			}
			filmDTO.setFilmLanguage(filmLanguage);
		}
		return filmDTO;
	}

	//判断集合是否为空
	public static boolean isEmpty(List array){
		return array==null || array.size()==0;
	}

	//电影基本信息和3个枚举融合
	public static List<FilmDTO> fuse(List<FilmDTO> filmDTOs,List<FilmInformation> filmInformations){
		for (FilmInformation filmInformation : filmInformations){
			//将数据库中存储的type、Country、Language字段拆分成字符数组，并转化为枚举
			List<String> filmType = Arrays.asList(filmInformation.getFilmType().split(","));
			List<String> filmCountry = Arrays.asList(filmInformation.getFilmCountry().split(","));
			List<String> filmLanguage = Arrays.asList(filmInformation.getFilmLanguage().split(","));
			List<FilmType> filmTypes = FilmType.StringToEnum(filmType);
			List<FilmCountry> filmCountries = FilmCountry.stringToEnum(filmCountry);
			List<FilmLanguage> filmLanguages = FilmLanguage.stringToEnum(filmLanguage);
			//由于type、Country、Language是分开查的，所以在融合的时候需要对应id
			for (FilmDTO filmDto : filmDTOs){
				if(filmInformation.getId()==filmDto.getId()){
					filmDto.setFilmType(filmTypes);
					filmDto.setFilmCountry(filmCountries);
					filmDto.setFilmLanguage(filmLanguages);
				}
			}
		}
		return filmDTOs;
	}
	@Test
	public void test(){
		double i = 10;
		double pow = Math.pow(10, 3);
		System.out.println((double)Math.round(i*pow)/pow);
	}
}
