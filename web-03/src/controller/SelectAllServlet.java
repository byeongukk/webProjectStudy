package controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Service.ProductService;
import model.vo.Product;

@WebServlet("/selectAll")
public class SelectAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SelectAllServlet() {
        super();
    }
	public void init(ServletConfig config) throws ServletException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		
		PrintWriter out = response.getWriter();
		
		ProductService ps = new ProductService();
		ArrayList<Product> list = ps.selectAll();
		
		if(list.size() == 0) {
			out.write("검색 결과가 없습니다");
		} else {
			int row = list.size();
			int col = 4;
			out.write("<table border='1px'>");
			//테이블 머리부분
			
			out.write("<tr height='50px'>");

			out.write("<td width='50px'>");			
			out.write("물품ID");
			out.write("</td>");
			out.write("<td width='50px'>");
			out.write("물품명");
			out.write("</td>");
			out.write("<td width='50px'>");
			out.write("가격");
			out.write("</td>");
			out.write("<td width='50px'>");
			out.write("설명");

			out.write("</td>");
			
			out.write("</tr>");
			
			for(int i = 0; i < row; i++){
				out.write("<tr height='50px'>");

				out.write("<td width='50px'>");
				
				out.write(list.get(i).getpId());
				out.write("</td>");
				out.write("<td width='50px'>");
				out.write(list.get(i).getpName());
				out.write("</td>");
				out.write("<td width='50px'>");
				out.write(list.get(i).getPrice());
				out.write("</td>");
				out.write("<td width='50px'>");
				out.write(list.get(i).getDescription());
				
				
				out.write("</td>");
				
				out.write("</tr>");
			}
			out.write("</table>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
