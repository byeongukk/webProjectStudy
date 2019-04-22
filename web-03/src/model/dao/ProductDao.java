package model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import common.JDBCTemplate;
import model.vo.Product;

public class ProductDao {

	public int inputProduct(Connection con, Product p) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "INSERT INTO PRODUCT VALUES (?,?,?,?)";
		
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, p.getpId());
			pstmt.setString(2, p.getpName());
			pstmt.setInt(3, p.getPrice());
			pstmt.setString(4, p.getDescription());
			
			result = pstmt.executeUpdate();
			con.setAutoCommit(false);
	        if(result > 0) {
	            con.commit();
	        } else {
	            con.rollback();
	        }		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		return result;
	}

	public ArrayList<Product> selectAll(Connection con) {
		ArrayList<Product> list = null;
		Statement stmt = null;
		ResultSet rset = null;

		Properties prop = new Properties();
		try {
			prop.load(new FileReader("C:\\5_Servlet\\workspace\\web-03\\src\\sql\\query.properties"));
			String query = prop.getProperty("selectAll");
			System.out.println(query);
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset!=null) {
				list = new ArrayList<Product>();
				
				while(rset.next()) {
					Product p = new Product();
					 p.setpId(rset.getString("PRODUCT_ID"));
                     p.setpName(rset.getString("P_NAME"));
                     p.setPrice(rset.getInt("PRICE"));
                     p.setDescription(rset.getString("DESCRIPTION"));
                     list.add(p);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(stmt);
			JDBCTemplate.close(rset);
		}
		return list;
	}

}
