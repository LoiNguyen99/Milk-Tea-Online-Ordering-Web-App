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
import milk_tea.dtos.ToppingDTO;
import mlik_tea.connections.MyConnection;

/**
 *
 * @author Loi Nguyen
 */
public class ToppingDAO {

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

    public HashMap<Integer, String> getTopping() throws Exception {
        HashMap<Integer, String> data = new HashMap();
        try {
            conn = MyConnection.getConnection();
            String sql = "select ToppingID, ToppingName from tbl_topping";
            psmt = conn.prepareCall(sql);
            rs = psmt.executeQuery();
            while (rs.next()) {
                int toppingID = rs.getInt("ToppingID");
                String ToppingName = rs.getString("ToppingName");

                data.put(toppingID, ToppingName);

            }
        } finally {
            closeConnection();
        }

        return data;
    }

    public List<ToppingDTO> getToppingFromBeginToEnd(int begin, int end, String status) throws Exception {

        List<ToppingDTO> list;
        ToppingDTO dto;
        String sql = "WITH ToppingList AS "
                + "("
                + "SELECT ToppingID, ToppingName, Price, "
                + "ROW_NUMBER() OVER (ORDER BY ToppingID DESC) AS 'RowNumber' "
                + "FROM tbl_topping where Status = ?"
                + ")"
                + "SELECT ToppingID, ToppingName, Price "
                + "FROM ToppingList "
                + "WHERE RowNumber BETWEEN ? AND ?;";

        try {
            conn = MyConnection.getConnection();
            psmt = conn.prepareCall(sql);
            psmt.setString(1, status);
            psmt.setInt(2, begin);
            psmt.setInt(3, end);
            rs = psmt.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                int toppingID = rs.getInt("ToppingID");
                String toppingName = rs.getString("ToppingName");
                float price = rs.getFloat("Price");
                dto = new ToppingDTO(toppingID, toppingName, price);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public int getAmountOfTopping() throws Exception {
        int count = 0;
        String sql = "select COUNT(ToppingID) as CountTopping from tbl_topping";
        conn = MyConnection.getConnection();
        psmt = conn.prepareCall(sql);
        rs = psmt.executeQuery();
        if (rs.next()) {

            count = rs.getInt("CountTopping");

        }

        return count;
    }

    public ToppingDTO getToppingByID(int toppingID) throws Exception {
        ToppingDTO dto = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "select ToppingName,Price, Status from tbl_topping where ToppingID = ?";
            psmt = conn.prepareCall(sql);
            psmt.setInt(1, toppingID);
            rs = psmt.executeQuery();
            while (rs.next()) {

                String ToppingName = rs.getString("ToppingName");
                String status = rs.getString("Status");
                float price = rs.getFloat("Price");
                if (status.equals("0")) {
                    status = "False";
                } else {
                    status = "True";
                }
                dto = new ToppingDTO(toppingID, ToppingName, price);
                dto.setStatus(status);

            }
        } finally {
            closeConnection();
        }

        return dto;
    }

    public boolean insert(ToppingDTO dto) throws Exception {
        boolean result = false;

        try {

            conn = MyConnection.getConnection();
            String sql = "INSERT INTO tbl_topping(ToppingName,Price) values(?,?)";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, dto.getToppingName());
            psmt.setFloat(1, dto.getPrice());
            result = psmt.executeUpdate() > 0;

        } finally {
            closeConnection();
        }

        return result;
    }

    public boolean updateTopping(ToppingDTO dto) throws Exception {
        boolean result = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "update tbl_topping set ToppingName = ?, Price = ?, Status = ? where ToppingID = ?";
            psmt = conn.prepareCall(sql);
            psmt.setString(1, dto.getToppingName());
            psmt.setFloat(2, dto.getPrice());
            psmt.setString(3, dto.getStatus());
            psmt.setInt(4, dto.getToppingID());
            result = psmt.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;

    }

    public boolean deleteTopping(int ToppingID) throws Exception {
        boolean result = false;
        try {
            String sql = "Update tbl_topping set Status = 'false' where ToppingID = ?";
            conn = MyConnection.getConnection();
            psmt = conn.prepareCall(sql);
            psmt.setInt(1, ToppingID);

            result = psmt.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
}
