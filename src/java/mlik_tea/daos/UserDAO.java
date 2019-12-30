/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlik_tea.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import milk_tea.dtos.UserDTO;
import mlik_tea.connections.MyConnection;

/**
 *
 * @author Loi Nguyen
 */
public class UserDAO implements Serializable {

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

    public UserDTO Login(String username, String password) throws Exception {
        UserDTO dto = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "select Fullname, RoleID from tbl_user where Username = ? and Password = ?";
            psmt = conn.prepareCall(sql);
            psmt.setString(1, username);
            psmt.setString(2, password);

            rs = psmt.executeQuery();

            if (rs.next()) {
                String fullname = rs.getString("Fullname");
                int roleid = rs.getInt("RoleID");
                dto = new UserDTO();
                dto.setFullname(fullname);
                dto.setRoleID(roleid);
                dto.setUsername(username);
            }
        } finally {
            closeConnection();
        }
        return dto;

    }

    public boolean insert(UserDTO dto) throws Exception {
        boolean result = false;

        try {

            conn = MyConnection.getConnection();
            String sql = "INSERT INTO tbl_user(Username,Password,Fullname,PhoneNo,Address) values(?,?,?,?,?)";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, dto.getUsername());
            psmt.setString(2, dto.getPassword());
            psmt.setString(3, dto.getFullname());
            psmt.setString(4, dto.getPhoneNo());
            psmt.setString(5, dto.getAddress());

            result = psmt.executeUpdate() > 0;

        } finally {
            closeConnection();
        }

        return result;
    }

    public UserDTO getUserByUsername(String username) throws Exception {
        UserDTO dto = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "select Address, PhoneNo from tbl_user where Username = ?";
            psmt = conn.prepareCall(sql);
            psmt.setString(1, username);

            rs = psmt.executeQuery();

            if (rs.next()) {
                String address = rs.getString("Address");
                String phoneNo = rs.getString("PhoneNo");
                dto = new UserDTO();
                dto.setAddress(address);
                dto.setPhoneNo(phoneNo);
            }
        } finally {
            closeConnection();
        }
        return dto;

    }

    public UserDTO getAllProertyOfUserByUsername(String username) throws Exception {
        UserDTO dto = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "select Fullname, Address, PhoneNo , Status , RoleID from tbl_user where Username = ?";
            psmt = conn.prepareCall(sql);
            psmt.setString(1, username);
            rs = psmt.executeQuery();

            if (rs.next()) {
                String fullname = rs.getString("Fullname");
                String status = rs.getString("Status");
                if (status.equals("0")) {
                    status = "False";
                } else {
                    status = "True";
                }
                int roleid = rs.getInt("RoleID");
                String address = rs.getString("Address");
                String phoneNo = rs.getString("PhoneNo");
                dto = new UserDTO(username, fullname, address, phoneNo);
                dto.setRoleID(roleid);
                dto.setStatus(status);

            }
        } finally {
            closeConnection();
        }
        return dto;

    }

    public boolean updateUser(UserDTO dto) throws Exception {
        boolean result = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "update tbl_user set Fullname = ?, Address = ?, PhoneNo = ? where Username = ?";
            psmt = conn.prepareCall(sql);
            psmt.setString(1, dto.getFullname());
            psmt.setString(2, dto.getAddress());
            psmt.setString(3, dto.getPhoneNo());
            psmt.setString(4, dto.getUsername());

            result = psmt.executeUpdate() > 0;

        } finally {
            closeConnection();
        }
        return result;

    }

    public boolean updatePassword(String username, String password) throws Exception {
        boolean result = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "update tbl_user set Password = ? where Username = ?";
            psmt = conn.prepareCall(sql);
            psmt.setString(1, password);
            psmt.setString(2, username);

            result = psmt.executeUpdate() > 0;

        } finally {
            closeConnection();
        }
        return result;

    }

    public String getPassowrd(String username) throws Exception {
        String result = "fail";
        try {
            conn = MyConnection.getConnection();
            String sql = "select Password from tbl_user where Username = ?";
            psmt = conn.prepareCall(sql);
            psmt.setString(1, username);

            rs = psmt.executeQuery();

            if (rs.next()) {
                String password = rs.getString("Password");

                result = password;

            }
        } finally {
            closeConnection();
        }
        return result;

    }

    public List<UserDTO> getUsersFromBeginToEnd(int begin, int end, String status) throws Exception {
        List<UserDTO> list;
        UserDTO dto;
        String sql = "WITH UserList AS "
                + "("
                + "SELECT a.Username, a.Fullname, a.PhoneNo, a.Address, b.RoleName,"
                + "ROW_NUMBER() OVER (ORDER BY UserName DESC) AS 'RowNumber' "
                + "FROM tbl_user a "
                + "INNER JOIN tbl_role b ON b.RoleID = a.RoleID where a.Status = ?"
                + ")"
                + "SELECT Username, Fullname, PhoneNo, Address, RoleName "
                + "FROM UserList "
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
                String userName = rs.getString("Username");
                String fullnaem = rs.getString("Fullname");
                String phoneNo = rs.getString("phoneNo");
                String address = rs.getString("Address");
                String role = rs.getString("RoleName");
                dto = new UserDTO(userName, role, fullnaem, address, phoneNo);
                dto.setRole(role);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public int getAmountOfUser() throws Exception {
        int count = 0;
        String sql = "select COUNT(Username) as CountUser from tbl_user";
        conn = MyConnection.getConnection();
        psmt = conn.prepareCall(sql);
        rs = psmt.executeQuery();
        if (rs.next()) {

            count = rs.getInt("CountUser");

        }

        return count;
    }

    public boolean updateUserByAdmin(UserDTO dto) throws Exception {
        boolean result = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "update tbl_user set Fullname = ?, PhoneNo = ?, Address = ?, RoleID = ?, Status = ? where Username = ?";
            psmt = conn.prepareCall(sql);
            psmt.setString(1, dto.getFullname());
            psmt.setString(2, dto.getPhoneNo());
            psmt.setString(3, dto.getAddress());
            psmt.setInt(4, dto.getRoleID());
            psmt.setString(5, dto.getStatus());
            psmt.setString(6, dto.getUsername());
            result = psmt.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;

    }

    public boolean deleteUser(String username) throws Exception {
        boolean result = false;
        try {
            String sql = "Update tbl_user set Status = 'false' where Username = ?";
            conn = MyConnection.getConnection();
            psmt = conn.prepareCall(sql);
            psmt.setString(1, username);

            result = psmt.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public HashMap<Integer, String> getRole() throws Exception {
        HashMap<Integer, String> data = new HashMap();
        try {
            conn = MyConnection.getConnection();
            String sql = "select RoleID, RoleName from tbl_role";
            psmt = conn.prepareCall(sql);
            rs = psmt.executeQuery();
            while (rs.next()) {
                int roleID = rs.getInt("RoleID");
                String roleName = rs.getString("RoleName");

                data.put(roleID, roleName);

            }
        } finally {
            closeConnection();
        }

        return data;
    }
    
}
