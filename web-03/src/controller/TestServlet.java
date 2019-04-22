package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Service.ProductService;
import model.vo.Product;

@WebServlet("/productInsert")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public TestServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String productId = request.getParameter("productId");
		String productName = request.getParameter("productName");
		String price = request.getParameter("price");
		String description = request.getParameter("description");
		
		PrintWriter out = response.getWriter();
		
		Product p = new Product(productId,productName,100,description);
		ProductService ps = new ProductService();
		int result = ps.inputProduct(p);
		
		if(result > 0) {
			
		out.println("<html><head></head>");
		out.println("<body>");
		
		out.println("물품 ID :  : " + productId + "<br><br>");
		out.println("물품명  : " + productName + "<br><br>");
		out.println("가격   : " + price + "<br><br>");
		out.println("설명  : " + description);
		
		out.println("입력 완료");
		
		out.println("</body>");
		out.print("</html>");
		} else {
			out.println("입력 실패");
		}
		//스트림 닫기
		out.close();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
