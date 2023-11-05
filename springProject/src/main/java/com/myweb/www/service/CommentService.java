package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;

public interface CommentService {

	int addComment(CommentVO cvo);

	int remove(int cno);

	int edit(CommentVO cvo);

	PagingHandler getList(long bno, PagingVO pgvo);
	
	List<CommentVO> getList(int bno);

}
