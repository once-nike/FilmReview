package com.nike.douye.service.Impl;

import com.auth0.jwt.JWT;
import com.nike.douye.Enum.Code;
import com.nike.douye.Enum.Score;
import com.nike.douye.dto.ScoreDTO;
import com.nike.douye.entity.ScorePo;
import com.nike.douye.exception.BaseException;
import com.nike.douye.mapper.ScoreMapper;
import com.nike.douye.service.ScoreService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;

@Service
public class ScoreServiceImpl implements ScoreService {
	@Autowired
	ScoreMapper scoreMapper;

	@Autowired
	HttpServletRequest httpServletRequest;

	Integer score;

	@Override
	public void scoring(Score score,Integer filmId) {

		//从token里拿到id
		String token = httpServletRequest.getHeader("token");
		Integer userId = Integer.valueOf(JWT.decode(token).getClaim("id").asString());

		//判断此用户是否已为该电影打过分
		if (scoreMapper.checkUserScore(userId,filmId) != null){
			throw new BaseException("您已经为该电影打过了分了哦",Code.REPEAT_OPERATION.getValue());
		}
		//拿到该电影的评分信息，并重新计算总分
		ScorePo scorePo = scoreMapper.findScoreInformationByFilmId(filmId);
		double totalScore = finalScoring(getScore(score,scorePo));
		ScorePo newScorePo = new ScorePo();
		newScorePo.setFilmId(filmId);
		newScorePo.setTotalScore(totalScore);

		//更新电影总评分信息
		newScorePo = getScore(score,scorePo,newScorePo);
		scoreMapper.updateScore(newScorePo);

		//添加个人评分信息
		scoreMapper.addScore(this.score,filmId,userId);
	}

	@Override
	public ScoreDTO showScore(ScoreDTO scoreDTO) {
		//从token里拿到id
		String token = httpServletRequest.getHeader("token");
		Integer userId = Integer.valueOf(JWT.decode(token).getClaim("id").asString());

		ScorePo scoreInformation;
		Score myScoreEnum;
		if (scoreDTO.getFilmId() != null){
			scoreInformation = scoreMapper.findScoreInformationByFilmId(scoreDTO.getFilmId());
			Integer myScore = scoreMapper.queryMyScoreByUserIdAndFilmId(userId, scoreDTO.getFilmId());
			myScoreEnum = Score.StringToEnum(myScore);
		}else {
			throw new BaseException("filmId必传哦",Code.PARAM_MISSING.getValue());
		}
		return new ScoreDTO(myScoreEnum,scoreInformation);
	}


	//计算总分
	public static double finalScoring(ScorePo scorePo){
		Integer scoreFive = scorePo.getScoreFive();
		Integer scoreFour = scorePo.getScoreFour();
		Integer scoreThree = scorePo.getScoreThree();
		Integer scoreTwo = scorePo.getScoreTwo();
		Integer scoreOne = scorePo.getScoreOne();
		double totalScore =(double)((scoreFive*5)+(scoreFour*4)+(scoreThree*3)+(scoreTwo*2)+(scoreOne*1))
				/(scoreFive+scoreFour+scoreThree+scoreTwo+scoreOne);
		return totalScore;
	}

	//枚举转化成int
	public ScorePo getScore(Score score,ScorePo scorePo,ScorePo newScorePo){
		switch (score){
			case SCORE_ONE:
				newScorePo.setScoreOne(scorePo.getScoreOne());
				this.score = 1;
				return newScorePo;
			case SCORE_TWO:
				newScorePo.setScoreTwo(scorePo.getScoreTwo());
				this.score = 2;
				return newScorePo;
			case SCORE_THREE:
				newScorePo.setScoreThree(scorePo.getScoreThree());
				this.score = 3;
				return newScorePo;
			case SCORE_FOUR:
				newScorePo.setScoreFour(scorePo.getScoreFour());
				this.score = 4;
				return newScorePo;
			case SCORE_FIVE:
				newScorePo.setScoreFive(scorePo.getScoreFive());
				this.score = 5;
				return newScorePo;
			default:
				return newScorePo;
		}
	}
	public ScorePo getScore(Score score,ScorePo scorePo){
		switch (score){
			case SCORE_ONE:
				scorePo.setScoreOne(scorePo.getScoreOne()+1);
				return scorePo;
			case SCORE_TWO:
				scorePo.setScoreTwo(scorePo.getScoreTwo()+1);
				return scorePo;
			case SCORE_THREE:
				scorePo.setScoreThree(scorePo.getScoreThree()+1);
				return scorePo;
			case SCORE_FOUR:
				scorePo.setScoreFour(scorePo.getScoreFour()+1);
				return scorePo;
			case SCORE_FIVE:
				scorePo.setScoreFive(scorePo.getScoreFive()+1);
				return scorePo;
			default:
				return scorePo;
		}
	}

	@Test
	public void test(){
		double a = (double) 9/2;
		DecimalFormat decimalFormat = new DecimalFormat("0.0");
		String format = decimalFormat.format(a);

		System.out.println(a);
	}
}
