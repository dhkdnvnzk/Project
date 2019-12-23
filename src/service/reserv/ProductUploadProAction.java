package service.reserv;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.reserv.ProductDAO;
import model.reserv.ProductVO;
import service.Action;

public class ProductUploadProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String b_id = request.getParameter("b_id");
		
		request.setAttribute("b_id", b_id);
		

		ServletContext context = request.getServletContext();
		String path = context.getRealPath("/upload");
		String encType = "utf-8";
		
		int sizeLimit = 4*1024*1024; //�ִ� 4MB
		
		MultipartRequest multi = new MultipartRequest(request, path, sizeLimit, 
				encType, new DefaultFileRenamePolicy()); // ÷������ ���ε�
		
		String p_title = multi.getParameter("p_title"); // ��ǰ �̸�
		String p_contents = multi.getParameter("p_contents"); // ��ǰ ����
		String p_indate = multi.getParameter("p_indate"); // �����
		String p_outdate = multi.getParameter("p_outdate"); // ������
		String p_in = multi.getParameter("p_in"); //�����
		String p_out = multi.getParameter("p_out"); //������
		int p_maxpeople = Integer.parseInt(multi.getParameter("p_maxpeople")); //�ִ� �ο�
		int p_price = Integer.parseInt(multi.getParameter("p_price")); //����
		String[] tema = multi.getParameterValues("thema"); //�׸�
		String p_filename = multi.getParameter("filename"); //���� �̸�
		
		System.out.println(p_in);
		
		String theme="";
		
		for(int i=0; i<tema.length; i++) {
			if(i==tema.length-1) {
				theme=theme+tema[i];
			}else theme=theme+tema[i]+",";
		}
		
		ProductVO pvo = new ProductVO();
		
		pvo.setB_id(b_id); pvo.setP_title(p_title); pvo.setP_contents(p_contents);
		pvo.setP_indate(p_indate); pvo.setP_outdate(p_outdate); pvo.setP_in(p_in);
		pvo.setP_out(p_out); pvo.setP_maxpeople(p_maxpeople); pvo.setP_price(p_price);
		pvo.setTheme(theme); pvo.setP_filename(p_filename);
		
		ProductDAO dao = ProductDAO.getInstance();
		
		int row = 0;
		
		row = dao.productUpload(pvo);
		
		System.out.println(row);
		
		request.setAttribute("row", row);
		
				
		RequestDispatcher rd = request.getRequestDispatcher("/Contents/Reservation/R_Business/upload_ok.jsp");
		rd.forward(request, response);
	}

}
