package com.nike.douye.api;

import com.nike.douye.Enum.Code;
import com.nike.douye.Enum.Score;
import com.nike.douye.dto.ResponseDTO;
import com.nike.douye.dto.ScoreDTO;
import com.nike.douye.exception.BaseException;
import com.nike.douye.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/score")
public class ScoreApi {

	@Autowired
	ScoreService scoreService;

	@RequestMapping("/add")
	public ResponseDTO<String>  scoring(@RequestBody ScoreDTO scoreDTO){
		if(scoreDTO != null){
			scoreService.scoring(scoreDTO.getMyScore(),scoreDTO.getFilmId());
		}else {
			throw new BaseException("scoreDTO不可以为空", Code.PARAM_MISSING.getValue());
		}
		return new ResponseDTO<>(Code.SUCCESS.getValue(),"打分成功");
	}
	@RequestMapping("/show")
	public ResponseDTO<ScoreDTO>  showScore(@RequestBody ScoreDTO scoreDTO){
		ScoreDTO scoreDTO1;
		if(scoreDTO != null){
			scoreDTO1= scoreService.showScore(scoreDTO);
		}else {
			throw new BaseException("scoreDTO不可以为空", Code.PARAM_MISSING.getValue());
		}
		return new ResponseDTO<>(Code.SUCCESS.getValue(),scoreDTO1);
	}

}
