package com.nike.douye.dto;

import com.nike.douye.Enum.FilmCountry;
import com.nike.douye.Enum.FilmLanguage;
import com.nike.douye.Enum.FilmType;
import com.nike.douye.ValidGroup.ValidGroupA;
import com.nike.douye.ValidGroup.ValidGroupB;
import com.nike.douye.ValidGroup.ValidGroupC;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddFilmDTO {
	Integer id;

	@NotBlank(groups = {ValidGroupA.class,ValidGroupB.class})
	String filmName;

	@NotBlank(groups = {ValidGroupA.class})
	String filmDirector;

	@NotBlank(groups = {ValidGroupA.class})
	String filmWriter;

	@NotBlank(groups = {ValidGroupA.class})
	String filmActor;

	@NotEmpty(groups = {ValidGroupA.class})
	List<FilmType> filmType;

	@NotEmpty(groups = {ValidGroupA.class})
	List<FilmCountry> filmCountry;

	@NotEmpty(groups = {ValidGroupA.class})
	List<FilmLanguage> filmLanguage;

	@NotBlank(groups = {ValidGroupA.class})
	String filmDate;

	@NotBlank(groups = {ValidGroupA.class})
	String filmLength;

	@NotBlank(groups = {ValidGroupA.class})
	String filmIntroduction;

	@NotBlank(groups = {ValidGroupA.class})
	MultipartFile filmPicture;

	@NotBlank(groups = {ValidGroupA.class})
	char isNew;

	@NotNull(groups = {ValidGroupB.class, ValidGroupC.class})
	Integer pageNum;
	@NotNull(groups = {ValidGroupB.class, ValidGroupC.class})
	Integer pageSize;

	public AddFilmDTO(Integer pageNum, Integer pageSize) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}

	public AddFilmDTO(String filmName, Integer pageNum, Integer pageSize) {
		this.filmName = filmName;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}

	public AddFilmDTO(String filmName, String filmDirector, String filmWriter, String filmActor, List<FilmType> filmType, List<FilmCountry> filmCountry, List<FilmLanguage> filmLanguage, String filmDate, String filmLength, String filmIntroduction, MultipartFile filmPicture, char isNew) {
		this.filmName = filmName;
		this.filmDirector = filmDirector;
		this.filmWriter = filmWriter;
		this.filmActor = filmActor;
		this.filmType = filmType;
		this.filmCountry = filmCountry;
		this.filmLanguage = filmLanguage;
		this.filmDate = filmDate;
		this.filmLength = filmLength;
		this.filmIntroduction = filmIntroduction;
		this.filmPicture = filmPicture;
		this.isNew = isNew;
	}

	public AddFilmDTO(char isNew, Integer pageNum, Integer pageSize) {
		this.isNew = isNew;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}

	public AddFilmDTO(List<FilmType> filmType, List<FilmCountry> filmCountry, List<FilmLanguage> filmLanguage, Integer pageNum, Integer pageSize) {
		this.filmType = filmType;
		this.filmCountry = filmCountry;
		this.filmLanguage = filmLanguage;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}
}
