package com.myboot03.board.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myboot03.board.dao.BoardDAO;
import com.myboot03.board.vo.ArticleVO;
import com.myboot03.board.vo.ImageVO;

@Service("boardService")
@Transactional(propagation = Propagation.REQUIRED)
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	BoardDAO boardDAO;

	@Override
	public List<ArticleVO> listArticles() throws Exception {
		return boardDAO.selectAllArticlesList();
	}

//	다중 이미지 추가하기
	@Override
	public int addNewArticle(Map articleMap) throws Exception {
		int articleNO = boardDAO.selectNewArticleNO();
		articleMap.put("articleNO", articleNO);
		boardDAO.insertNewArticle(articleMap);
		
		List<ImageVO> imageFileList = (ArrayList)articleMap.get("imageFileList");
		int imageFileNO = boardDAO.selectNewImageFileNO();
		for(ImageVO imageVO:imageFileList) {
			imageVO.setImageFileNO(++imageFileNO);
			imageVO.setArticleNO(articleNO);
		}
		
		boardDAO.insertNewImage(imageFileList);
		return articleNO;
	}

//	다중 파일 보이기
	@Override
	public Map viewArticle(int articleNO) throws Exception {
		Map articleMap = new HashMap();
		ArticleVO articleVO = boardDAO.selectArticle(articleNO);
		List<ImageVO> imageFileList = boardDAO.selectImageFileList(articleNO);
		articleMap.put("article", articleVO);
		articleMap.put("imageFileList", imageFileList);
		return articleMap;
	}
	
	
//	단일 이미지 보이기
//	@Override
//	public ArticleVO viewArticle(int articleNO) throws Exception {
//		return boardDAO.selectArticle(articleNO);
//	}

	@Override
	public void modArticle(Map articleMap) throws Exception {
		boardDAO.updateArticle(articleMap);
	}

	@Override
	public void removeArticle(int articleNO) throws Exception {
		boardDAO.deleteArticle(articleNO);
	}

}
