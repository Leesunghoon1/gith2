package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.repository.BoardDAO;
import com.myweb.www.repository.FileDAO;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class BoardServiceImpl implements boardService{
	
	@Inject
	private BoardDAO bdao;
	
	@Inject
	private FileDAO fdao;


	 @Override 
	 public int PostRegister(BoardVO bvo) { // TODO Auto-generated
	return bdao.PostRegister(bvo); }
	 

	@Transactional
	@Override
	public BoardVO getDetail(long bno) {
		// TODO Auto-generated method stub
		
		int isOK = bdao.readCount(bno);
		log.info("조회수 >> " + (isOK > 0 ? "UP" : "ERROR"));
		return bdao.getDetail(bno);
	}

	@Override
	public int postModify(BoardVO bvo) {
		// TODO Auto-generated method stub
		return bdao.postModify(bvo);
	}

	@Override
	public int remove(int bno) {
		// TODO Auto-generated method stub
		return bdao.remove(bno);
	}

	@Override
	public int getTotalCount(PagingVO pvo) {
		// TODO Auto-generated method stub
		return bdao.getTotalCount(pvo);
	}

	@Override
	public List<BoardVO> getList(PagingVO pvo) {
		// TODO Auto-generated method stub
				return bdao.getList(pvo);
	}



	@Transactional
	@Override
	public int register(BoardDTO bdto) {
		// TODO Auto-generated method stub
		int isUp = bdao.PostRegister(bdto.getBvo());
		//기존 메소드 활용
		if(bdto.getFlist() == null) {
			//사진이없더라도 등록되게 마지막 * 1 해주는거
			isUp *= 1;
		}else {
			// bvo insert 후, 파일도 있다면 ...
			if(isUp > 0 && bdto.getFlist().size() > 0) {
				long bno = bdao.selectOneBno();
				//글등록 파일등록 둘다 할때
				//이건 파일등록
				//글을 등록하자마자 그 글의 bno를 갖고 file mapper에 bno 등록하려고
				//가장 마지막에 등록된 bno
				for(FileVO fvo : bdto.getFlist()) {
					fvo.setBno(bno);
					isUp *= fdao.insertFile(fvo);
				}
			}
		}
		return isUp;
	}


	
}
