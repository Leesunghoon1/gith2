package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardDAO {

	int PostRegister(BoardVO bvo);

	List<BoardVO> getList(PagingVO pvo);

	int postModify(BoardVO bvo);

	int remove(int bno);

	int readCount(long bno);

	BoardVO getDetail(long bno);

	int getTotalCount(PagingVO pvo);

	long selectOneBno();

}
