package com.myboot03.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myboot03.board.vo.ArticleVO;
import com.myboot03.board.vo.ImageVO;

@Mapper
@Repository("boardDAO")
public interface BoardDAO {
	public List selectAllArticlesList() throws DataAccessException;
	public int insertNewArticle(Map articleMap) throws DataAccessException;
	
	public ArticleVO selectArticle(int articleVO) throws DataAccessException;
	public void updateArticle(Map articleMap) throws DataAccessException;
	public void deleteArticle(int articleNO) throws DataAccessException;
	public List selectImageFileList(int articleNO) throws DataAccessException;
	public void insertNewImage(List<ImageVO> imageFileList) throws DataAccessException;
	public int selectNewArticleNO() throws DataAccessException;
	public int selectNewImageFileNO() throws DataAccessException;
}
