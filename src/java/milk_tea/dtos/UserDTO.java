/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milk_tea.dtos;

/**
 *
 * @author Loi Nguyen
 */
public class UserDTO {
    private String username;
    private String password;
    private String fullname;
    private String role;
    private int roleID;
    private String address;
    private String phoneNo;
    private String Status;

    public UserDTO() {
    }

    public UserDTO(String username, String password, String fullname, String address, String phoneNo) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.address = address;
        this.phoneNo = phoneNo;
    }

    public UserDTO(String username, String fullname, String role) {
        this.username = username;
        this.fullname = fullname;
        this.role = role;
    }

    public UserDTO(String username, String fullname, String address, String phoneNo) {
        this.username = username;
        this.fullname = fullname;
        this.address = address;
        this.phoneNo = phoneNo;
    }

   

    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passoword) {
        this.password = passoword;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }
    
    
}
