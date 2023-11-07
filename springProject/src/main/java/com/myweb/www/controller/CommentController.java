package com.myweb.www.controller;

import java.security.Principal;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/comment/*")
public class CommentController {
	
	
	//ResponseEntity 객체를 사용
	//@RequestBody : body값 추출
	//value = "mapping name", consumes : 가져오는 데이터의 형태
	//produces : 보내는 데이터의 형식 나가는 데이터 타입 : MediaType.
	// json : application/json text : text_plain
	
	@Inject
	private CommentService csv;
	
	
	@PostMapping(value = "post", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> post(@RequestBody CommentVO cvo){
		//@RequestBody CommentVO cvo js에서 바디에 댓글을 담아서 여기로 받고 
		//mapper에 보내서 정보값 입력
		
		int isOk = csv.addComment(cvo);
		return isOk > 0 ? new ResponseEntity<String> ("1", HttpStatus.OK) :new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	@GetMapping(value = "{bno}/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PagingHandler> spreadCommentList(@PathVariable("bno")long bno, @PathVariable("page")int page){
		
		log.info("bno >> " + bno + " page >> " + page);
		PagingVO pgvo = new PagingVO(page, 5);
		
		return  new ResponseEntity<PagingHandler>(csv.getList(bno, pgvo), HttpStatus.OK);
	}
	@DeleteMapping(value="/{cno}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> remove(@PathVariable("cno")int cno) {
		int isOK = csv.remove(cno);
		log.info("isOK>>> {}", isOK);
		return isOK > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping(value="/{cno}", 
			consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> edit(@RequestBody CommentVO cvo, HttpServletRequest request, Principal principal) {
		log.info("댓글 수정 cvo {}", cvo);
		if (!(cvo.getWriter().equals(principal.getName()))) {
			return new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		int isOK = csv.edit(cvo);
		return isOK > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(value="/{bno}", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CommentVO>> spread(
			@PathVariable("bno") int bno) {
		//@PathVariable("bno") int bno js에서 bno값을 헤더에 + bno로 들고 감
		log.info(">>>>> comment List bno" + bno);
		//DB 요청
		List<CommentVO> cmtList = csv.getList(bno);
		log.info(">>>>>2222" + cmtList);
		return new ResponseEntity<List<CommentVO>>(cmtList, HttpStatus.OK);
		
	}
	
}
