/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlik_tea.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import milk_tea.dtos.CategoryDTO;
import milk_tea.dtos.ProductDTO;
import mlik_tea.connections.MyConnection;

/**
 *
 * @author Loi Nguyen
 */
public class CategoryDAO {
    Connection conn;
    PreparedStatement psmt;
    ResultSet rs;

    private void closeConnection() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (psmt != null) {
                psmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
        }
    }


    public HashMap<Integer, String> getCategory() throws Exception {
        HashMap<Integer, String> data =new HashMap();
        try {
            conn = MyConnection.getConnection();
            String sql = "select CategoryID, CategoryName from tbl_category";
            psmt = conn.prepareCall(sql);
            rs = psmt.executeQuery();
            while (rs.next()) {
                int categoryID = rs.getInt("CategoryID");
                String CategoryName = rs.getString("CategoryName");
                
                data.put(categoryID, CategoryName);
                
            }
        } finally {
            closeConnection();
        }

        return data;
    }
    
    public List<CategoryDTO> getCategoryFromBeginToEnd(int begin, int end,String status) throws Exception {
        
        List<CategoryDTO> list ;
        CategoryDTO dto;
        String sql = "WITH CategoryList AS "
                + "("
                + "SELECT CategoryID, CategoryName, "
                + "ROW_NUMBER() OVER (ORDER BY CategoryID DESC) AS 'RowNumber' "
                + "FROM tbl_category where Status = ?"
                + ")"
                + "SELECT CategoryID, CategoryName "
                + "FROM CategoryList "
                + "WHERE RowNumber BETWEEN ? AND ?;";
        
        try {
            conn = MyConnection.getConnection();
            psmt = conn.prepareCall(sql);
            psmt.setString(1, status);
            psmt.setInt(2, begin);
            psmt.setInt(3, end);
            rs = psmt.executeQuery();
            list = new ArrayList<>();
            while(rs.next())
            {
                int categoryID = rs.getInt("CategoryID");
                String categoryName = rs.getString("CategoryName");
                dto = new CategoryDTO(categoryID, categoryName);
                list.add(dto);
            }
        } finally{
            closeConnection();
        }
        return list;
    }
    
    
    public int getAmountOfCategory() throws Exception {
        int count = 0;
        String sql = "select COUNT(CategoryID) as CountCategory from tbl_category";
        conn = MyConnection.getConnection();
        psmt = conn.prepareCall(sql);
        rs = psmt.executeQuery();
        if (rs.next()) {

            count = rs.getInt("CountCategory");

        }

        return count;
    }
    
    public CategoryDTO getCategoryByID(int categoryID) throws Exception {
        CategoryDTO dto = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "select CategoryName, Status from tbl_category where CategoryID = ?";
            psmt = conn.prepareCall(sql);
            psmt.setInt(1, categoryID);
            rs = psmt.executeQuery();
            while (rs.next()) {
                
                String CategoryName = rs.getString("CategoryName");
                String status = rs.getString("Status");
                if(status.equals("0"))
                {
                    status = "False";
                }
                else {
                     status = "True";
                }
                dto = new CategoryDTO(CategoryName, status);
                dto.setCategoryID(categoryID);
                
            }
        } finally {
            closeConnection();
        }

        return dto;
    }
    
     public boolean insert(CategoryDTO dto) throws Exception {
        boolean result = false;

        try {

            conn = MyConnection.getConnection();
            String sql = "INSERT INTO tbl_category(CategoryName) values(?)";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, dto.getCategoryName());

            result = psmt.executeUpdate() > 0;

        } finally {
            closeConnection();
        }

        return result;
    }
     
     public boolean updateCategory(CategoryDTO dto) throws Exception {
        boolean result = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "update tbl_category set CategoryName = ?, Status = ? where CategoryID = ?";
            psmt = conn.prepareCall(sql);
            psmt.setString(1, dto.getCategoryName());
            psmt.setString(2, dto.getStatus());
            psmt.setInt(3, dto.getCategoryID());
            result = psmt.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;

    }
     
    public boolean deleteCategory(int CategoryID) throws Exception
    {
        boolean result = false;
        try {
            String sql = "Update tbl_category set Status = 'false' where CategoryID = ?";
            conn = MyConnection.getConnection();
            psmt = conn.prepareCall(sql);
            psmt.setInt(1, CategoryID);

            result = psmt.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
}
