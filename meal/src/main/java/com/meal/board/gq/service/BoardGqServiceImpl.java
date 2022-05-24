package com.meal.board.gq.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meal.board.gq.dao.BoardGqDAO;
import com.meal.board.gq.vo.BoardGqVO;
import com.meal.board.gq.vo.Img_gqVO;

@Service("boardGqService")
@Transactional(propagation = Propagation.REQUIRED)
public class BoardGqServiceImpl implements BoardGqService {

	@Autowired
	private BoardGqDAO boardGqDAO;

	/* 페이징 */
	public List<BoardGqVO> selectBoardGqList(HashMap<String, Object> map) throws Exception {
		List<BoardGqVO> listInfo = (List<BoardGqVO>) boardGqDAO.selectBoardGqList(map);
		return listInfo;
	}

	public List<BoardGqVO> selectBoardGqallList() throws Exception {
		List<BoardGqVO> listInfo = (List<BoardGqVO>) boardGqDAO.selectBoardGqallList();
		return listInfo;
	}

	public Map<String, Object> boardGqView(int b_gq_id) throws Exception {
		Map<String, Object> boardGqMap=new HashMap<String, Object>();
		BoardGqVO boardGqVO = boardGqDAO.selectBoardGqDetail(b_gq_id);
		boardGqMap.put("boardGqVO", boardGqVO);
		List<Img_gqVO> imageList =boardGqDAO.selectBoardGqDetailImage(b_gq_id);
		boardGqMap.put("imageList", imageList);
		return boardGqMap;

	}

	public List<BoardGqVO> boardGqViewReview(int b_gq_id) throws Exception {
		return (List<BoardGqVO>) boardGqDAO.boardGqViewReview(b_gq_id);
	}

	public void boardGqUpdate(BoardGqVO boardGqVO) throws Exception {
		boardGqDAO.boardGqUpdate(boardGqVO);
	}

	public void boardGqDelete(int b_gq_id) throws Exception {
		boardGqDAO.boardGqDelete1(b_gq_id);
		boardGqDAO.boardGqDelete2(b_gq_id);
	}

	@Override
	public void boardGqWrite(HashMap<String, Object> newboardGqMap) throws Exception {
		boardGqDAO.boardGqWrite(newboardGqMap);

	}

	@Override
	public void addImg(HashMap<String, Object> item) throws Exception {
		boardGqDAO.addImg(item);

	}


	
	@Override
	public List<BoardGqVO> selectMyBoardGqallList(String u_id) throws Exception {
		List<BoardGqVO> listInfo = (List<BoardGqVO>) boardGqDAO.selectMyBoardGqallList(u_id);
		return listInfo;
	}

	@Override
	public List<BoardGqVO> selectMyBoardGqList(HashMap<String, Object> Map) throws Exception {
		List<BoardGqVO> listInfo = (List<BoardGqVO>) boardGqDAO.selectMyBoardGqList(Map);
		return listInfo;
	}

	@Override
	public List<BoardGqVO> selectSellerBoardGqallList(String s_id) {
		List<BoardGqVO> listInfo = (List<BoardGqVO>) boardGqDAO.selectSellerBoardGqallList(s_id);
		return listInfo;
	}

	@Override
	public List<BoardGqVO> selectSellerBoardGqList(HashMap<String, Object> Map) {
		List<BoardGqVO> listInfo = (List<BoardGqVO>) boardGqDAO.selectSellerBoardGqList(Map);
		return listInfo;
	}
	
	@Override
	public BoardGqVO findb_gq_id() throws Exception {
		return boardGqDAO.findb_gq_id();
	}
	
	@Override
	public BoardGqVO gqdownload(int b_gq_id) {
		return boardGqDAO.gqdownload(b_gq_id);
	}

}
