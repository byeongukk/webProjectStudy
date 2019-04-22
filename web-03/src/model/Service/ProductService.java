package model.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import model.dao.ProductDao;
import model.vo.Product;

public class ProductService {

	public int inputProduct(Product p) {
		ProductDao pd = new ProductDao();
		Connection con = null;
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "student", "student");
			

			result = pd.inputProduct(con,p);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public ArrayList<Product> selectAll() {
		Connection con = JDBCTemplate.getConnection();
		ProductDao pd = new ProductDao();
		ArrayList<Product> list = pd.selectAll(con);
		
		JDBCTemplate.close(con);
		return list;
	}

	
	
}
