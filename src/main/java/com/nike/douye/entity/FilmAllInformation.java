package com.nike.douye.entity;

import com.nike.douye.dto.FilmDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmAllInformation {
	List<FilmDTO> filmDTOS;
	List<FilmInformation> filmInformations;
	Double filmScore;
}
