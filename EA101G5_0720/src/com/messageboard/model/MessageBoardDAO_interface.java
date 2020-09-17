package com.messageboard.model;

import java.util.List;
import java.util.Set;

public interface MessageBoardDAO_interface {
	public void add(MessageBoardVO messageBoardVO);
	public void reply(MessageBoardVO messageBoardVO);//回覆
	public void update(MessageBoardVO messageBoardVO);
	public MessageBoardVO getMsgByArtNo(String artno);
	
	public List <MessageBoardVO> getAll();
	
    }
