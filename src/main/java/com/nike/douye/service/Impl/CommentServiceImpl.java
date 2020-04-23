package com.nike.douye.service.Impl;

import com.auth0.jwt.JWT;
import com.nike.douye.Enum.LIKE;
import com.nike.douye.dto.CommentDTO;
import com.nike.douye.dto.FilmCommentDTO;
import com.nike.douye.dto.SonCommentDTO;
import com.nike.douye.entity.CommentPo;
import com.nike.douye.mapper.CommentMapper;
import com.nike.douye.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	CommentMapper commentMapper;

	@Autowired
	HttpServletRequest request;

	@Override
	public void insertFatherComment(CommentDTO commentDTO,Integer filmId) {
		//从dto中取值并赋给po
		CommentPo commentPo = new CommentPo();
		commentPo.setComment(commentDTO.getComment());
		commentPo.setFilmId(filmId);
		//从token中拿到userId,并放入po
		String token = request.getHeader("token");
		Integer userId = Integer.valueOf(JWT.decode(token).getClaim("id").asString());
		commentPo.setUserId(userId);
		commentMapper.insertComment(commentPo);

		List<Integer> commentIds = commentMapper.queryCommentIdByUserIdAndFilmId(userId, filmId);
		//添加个人评论记录
		commentMapper.insertMyCommentRecord(userId,commentIds.get(0));
	}

	@Override
	public void insertSonComment(CommentDTO commentDTO, Integer fatherId) {
		//从dto中取值并赋给po
		CommentPo commentPo = new CommentPo();
		commentPo.setComment(commentDTO.getComment());
		commentPo.setFilmId(fatherId);
		//从token中拿到userId,并放入po
		String token = request.getHeader("token");
		Integer userId = Integer.valueOf(JWT.decode(token).getClaim("id").asString());
		commentPo.setUserId(userId);
		commentMapper.insertSonComment(commentPo);
	}

	@Override
	public FilmCommentDTO queryCommentByFilmId(Integer filmId) {
		List<CommentPo> commentPos = commentMapper.queryCommentByFilmId(filmId);
		List<CommentDTO> commentDTOS = poToDto(commentPos);
		return new FilmCommentDTO(commentDTOS);
	}

	@Override
	public SonCommentDTO queryCommentByFatherId(Integer fatherId) {
		List<CommentPo> commentPos = commentMapper.querySonCommentByFatherId(fatherId);
		List<CommentDTO> commentDTOS = poToDto(commentPos);
		return new SonCommentDTO(commentDTOS);
	}

	@Override
	public FilmCommentDTO queryCommentCollection() {
		//取userId
		String token = request.getHeader("token");
		Integer userId = Integer.valueOf(JWT.decode(token).getClaim("id").asString());
		//查出所有该id收藏的评论并转化为dto
		List<CommentPo> commentPos = commentMapper.queryCommentByUserId(userId);
		List<CommentDTO> commentDTOS = poToDto(commentPos);
		return new FilmCommentDTO(commentDTOS);
	}

	@Override
	public void updateCommentLike(CommentDTO commentDTO) {
		String like;
		switch (commentDTO.getLike()){
			case LIKE:
				like = "y";
				break;
			case DISLIKE:
				like = "n";
				break;
			default:
				like = "";
		}
		commentMapper.updateSonCommentLike(like,commentDTO.getCommentId());
	}

	@Override
	public void updateSonCommentLike(CommentDTO commentDTO) {
		String like;
		switch (commentDTO.getLike()){
			case LIKE:
				like = "y";
				break;
			case DISLIKE:
				like = "n";
				break;
			default:
				like = "";
		}
		commentMapper.updateCommentLike(like,commentDTO.getCommentId());
	}

	//po转dto
	public static List<CommentDTO> poToDto(List<CommentPo> commentPos){
		List<CommentDTO> comments = new ArrayList<>(commentPos.size());
		for(CommentPo commentPo : commentPos){
			CommentDTO commentDTO = new CommentDTO(commentPo.getCommentId(),commentPo.getUserId(),
												commentPo.getComment(),commentPo.getLikes(),
												commentPo.getDislikes(),commentPo.getCommentTime());
			comments.add(commentDTO);
		}
		return comments;
	}
}
