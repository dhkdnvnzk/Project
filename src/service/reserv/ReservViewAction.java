package service.reserv;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.reserv.ProductDAO;
import model.reserv.ProductVO;
import service.Action;

public class ReservViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int idx = Integer.parseInt(request.getParameter("p_idx"));
		
		ProductDAO dao = ProductDAO.getInstance();
		dao.plusReadcnt(idx);
		ProductVO vo = dao.ProductView(idx);
		String savePath = "/Contents/img/product_img";
		
		request.setAttribute("savePath", savePath);
		request.setAttribute("pvo", vo);
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/Contents/Reservation/Reserv_view.jsp");
		rd.forward(request, response);
	}

}
