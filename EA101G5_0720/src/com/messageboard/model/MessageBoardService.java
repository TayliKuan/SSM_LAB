package com.messageboard.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public class MessageBoardService {
	
	private MessageBoardDAO_interface dao;

	public MessageBoardService() {
		dao = new MessageBoardDAO();
//		dao = new MessageBoardJDBCDAO();
	}
	//ASKNONE,ARTNO,ASKDESC,MESREP,REPDESC,MESDATE
	public MessageBoardVO addMessageBoard(String artno, String askdesc, 
			String mesrep,String repdesc,String mesdate) {

		MessageBoardVO messageBoardVO = new MessageBoardVO();

		messageBoardVO.setArtNo(artno);
		messageBoardVO.setAskDesc(askdesc);
		messageBoardVO.setMesRep(mesrep);
		messageBoardVO.setRepDesc(repdesc);
		messageBoardVO.setMesDate(mesdate);
		dao.add(messageBoardVO);

		return messageBoardVO;
	}
	
	public MessageBoardVO replyMessage(String artno, String askdesc, 
			String mesrep,String repdesc,String mesdate) {
		
		MessageBoardVO messageBoardVO = new MessageBoardVO();
		messageBoardVO.setArtNo(artno);
		messageBoardVO.setAskDesc(askdesc);
		messageBoardVO.setMesRep(mesrep);
		messageBoardVO.setRepDesc(repdesc);
		messageBoardVO.setMesDate(mesdate);
		dao.reply(messageBoardVO);
		return messageBoardVO;
	}
	
	public MessageBoardVO updateMessageBoard(String asknone, String artno, String askdesc, 
			String mesrep,String repdesc, String mesdate) {

		MessageBoardVO messageBoardVO = new MessageBoardVO();

		messageBoardVO.setAskNone(asknone);
		messageBoardVO.setArtNo(artno);
		messageBoardVO.setAskDesc(askdesc);
		messageBoardVO.setMesRep(mesrep);
		messageBoardVO.setRepDesc(repdesc);
		messageBoardVO.setMesDate(mesdate);
		dao.update(messageBoardVO);

		return messageBoardVO;
	}

	public MessageBoardVO getMsgByArtNo(String artno){
		return dao.getMsgByArtNo(artno);
	}

	public List<MessageBoardVO> getAll() {
		return dao.getAll();
	}
//	public void deleteMessageBoard(String artno) {
//		dao.delete(artno);
//	}
//
//	public MessageBoardVO getOneMessageBoard(String asknone) {
//		return dao.findByPrimaryKey(asknone);
//	}
}
