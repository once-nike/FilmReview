package com.nike.douye.api;

import com.nike.douye.Enum.Code;
import com.nike.douye.Enum.LIKE;
import com.nike.douye.dto.CommentDTO;
import com.nike.douye.dto.FilmCommentDTO;
import com.nike.douye.dto.ResponseDTO;
import com.nike.douye.dto.SonCommentDTO;
import com.nike.douye.exception.BaseException;
import com.nike.douye.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentApi {

	@Autowired
	CommentService commentService;

	@RequestMapping(value = "/insert/fatherComment",method = RequestMethod.POST)
	public ResponseDTO<String> insertFatherComment(@RequestBody FilmCommentDTO filmCommentDTO){
		if(filmCommentDTO != null){
			commentService.insertFatherComment(filmCommentDTO.getComments().get(0),filmCommentDTO.getFilmId());
		}else {
			throw new BaseException("commentDTO不可以为空", Code.PARAM_MISSING.getValue());
		}
		return new ResponseDTO(Code.SUCCESS.getValue(),"评论成功拉");
	}

	@RequestMapping(value = "/insert/sonComment",method = RequestMethod.POST)
	public ResponseDTO<String> insertSonComment(@RequestBody SonCommentDTO sonCommentDTO){
		if(sonCommentDTO != null){
			commentService.insertSonComment(sonCommentDTO.getComments().get(0),sonCommentDTO.getFatherId());
		}else {
			throw new BaseException("commentDTO不可以为空", Code.PARAM_MISSING.getValue());
		}
		return new ResponseDTO(Code.SUCCESS.getValue(),"回复评论成功拉");
	}


	@RequestMapping(value = "/showFatherComment/byFilmId",method = RequestMethod.GET)
	public ResponseDTO<FilmCommentDTO> showCommentByFilmId(@RequestParam Integer filmId){
		FilmCommentDTO filmCommentDTO;
		if(filmId != null){
			filmCommentDTO = commentService.queryCommentByFilmId(filmId);
		}else {
			throw new BaseException("filmId不可以为空", Code.PARAM_MISSING.getValue());
		}
		return new ResponseDTO(Code.SUCCESS.getValue(),filmCommentDTO);
	}

	@RequestMapping(value = "/showSonComment/byFatherId",method = RequestMethod.GET)
	public ResponseDTO<FilmCommentDTO> showCommentByFatherId(@RequestParam Integer fatherId){
		SonCommentDTO sonCommentDTO;
		if(fatherId != null){
			sonCommentDTO = commentService.queryCommentByFatherId(fatherId);
		}else {
			throw new BaseException("fatherId不可以为空", Code.PARAM_MISSING.getValue());
		}
		return new ResponseDTO(Code.SUCCESS.getValue(),sonCommentDTO);
	}

	@RequestMapping(value = "/show/byUser",method = RequestMethod.GET)
	public ResponseDTO<FilmCommentDTO> showCommentByUser(){
		FilmCommentDTO filmCommentDTO = commentService.queryCommentRecord();
		if(isEmpty(filmCommentDTO.getComments())){
			throw new BaseException("您还没有任何影评记录哦",Code.DATA_NOT_EXIT.getValue());
		}
		return new ResponseDTO(Code.SUCCESS.getValue(),filmCommentDTO);
	}

	@RequestMapping(value = "/update/fatherCommentLikes",method = RequestMethod.POST)
	public ResponseDTO<String> updatefatherCommentLike(@RequestBody CommentDTO commentDTO){
		if (commentDTO != null){
			commentService.updateCommentLike(commentDTO);
		}else {
			throw new BaseException("commentDTO不可以为空哦",Code.PARAM_MISSING.getValue());
		}
		String message;
		if ("y".equals(commentDTO.getLike().getValue())){
			message = "点赞成功";
		}else {
			message = "点踩成功";
		}
		return new ResponseDTO(Code.SUCCESS.getValue(),message);
	}

	@RequestMapping(value = "/update/sonCommentLikes",method = RequestMethod.POST)
	public ResponseDTO<String> updatesonCommentLike(@RequestBody CommentDTO commentDTO){
		if (commentDTO != null){
			commentService.updateSonCommentLike(commentDTO);
		}else {
			throw new BaseException("commentDTO不可以为空哦",Code.PARAM_MISSING.getValue());
		}
		String message;
		if ("y".equals(commentDTO.getLike().getValue())){
			message = "点赞成功";
		}else {
			message = "点踩成功";
		}
		return new ResponseDTO(Code.SUCCESS.getValue(),message);
	}

	@RequestMapping(value = "/collect/Comment",method = RequestMethod.GET)
	public ResponseDTO<String> collectComment(@RequestParam Integer commentId){
		if (commentId != null){
			commentService.collectComment(commentId);
		}else {
			throw new BaseException("commentId不可以为空哦",Code.PARAM_MISSING.getValue());
		}
		return new ResponseDTO(Code.SUCCESS.getValue(),"收藏成功");
	}

	@RequestMapping(value = "/show/collection",method = RequestMethod.GET)
	public ResponseDTO<FilmCommentDTO> showMyCollection(){
		FilmCommentDTO filmCommentDTO = commentService.queryCommentCollection();
		if(isEmpty(filmCommentDTO.getComments())){
			throw new BaseException("您还没有收藏任何影评哦",Code.DATA_NOT_EXIT.getValue());
		}
		return new ResponseDTO(Code.SUCCESS.getValue(),filmCommentDTO);
	}

	@RequestMapping(value = "/delete/collection",method = RequestMethod.GET)
	public ResponseDTO<FilmCommentDTO> deleteMyCollection(@RequestParam Integer collectionId){
		if(collectionId == null){
			throw new BaseException("collectionId不可以为空哦",Code.PARAM_MISSING.getValue());
		}
		commentService.deleteMyCollection(collectionId);
		return new ResponseDTO(Code.SUCCESS.getValue(),"删除成功");
	}

	public static boolean isEmpty(List list){
		return list == null || list.size() == 0;
	}


}
