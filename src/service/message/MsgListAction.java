package service.message;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.message.MessageDAO;
import model.message.MessageVO;
import service.Action;

public class MsgListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String receive_id = request.getParameter("id");
		
		MessageDAO dao = MessageDAO.getInstance();
		List<MessageVO> mlist = null;
		
		mlist = dao.myReceiveMsgList(receive_id);
		
		
		
		
		request.setAttribute("mlist", mlist);
		
		RequestDispatcher rd = request.getRequestDispatcher("/Contents/Message/Message_main.jsp");
		rd.forward(request, response);
	
	}

}
