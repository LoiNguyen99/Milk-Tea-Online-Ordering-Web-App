/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlik_tea.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import milk_tea.dtos.CartItemDTO;
import milk_tea.dtos.ProductDTO;
import milk_tea.dtos.ToppingDTO;
import milk_tea.dtos.SizeDTO;
import mlik_tea.connections.MyConnection;

/**
 *
 * @author Loi Nguyen
 */
public class ProductDAO {

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

    public List<ProductDTO> getProduct() throws Exception {
        List<ProductDTO> data = new ArrayList<>();
        ProductDTO dto = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "select top(10) ProductID, ProductName, Price, ImagePath from tbl_product where Status = 1 order by Date desc";
            psmt = conn.prepareCall(sql);
            rs = psmt.executeQuery();
            while (rs.next()) {
                int productid = rs.getInt("ProductID");
                String productname = rs.getString("ProductName");
                Float price = rs.getFloat("Price");
                String imagePath = rs.getString("ImagePath");
                dto = new ProductDTO(productid, productname, price, imagePath);
                data.add(dto);
            }
        } finally {
            closeConnection();
        }

        return data;
    }

    public ProductDTO getAllPropertyOfProduct(int productID) throws Exception {
        ProductDTO dto = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "select ProductID, ProductName, Price, ImagePath, CategoryID, Status from tbl_product where ProductID = ?";
            psmt = conn.prepareCall(sql);
            psmt.setInt(1, productID);
            rs = psmt.executeQuery();
            if (rs.next()) {
                int productid = rs.getInt("ProductID");
                String productname = rs.getString("ProductName");
                int categoryID = rs.getInt("CategoryID");
                Float price = rs.getFloat("Price");
                String status = rs.getString("Status");
                if (status.equals("0")) {
                    status = "False";
                } else {
                    status = "True";
                }
                String imagePath = rs.getString("ImagePath");
                dto = new ProductDTO(productid, productname, price, imagePath);
                dto.setCategoryID(categoryID);
                dto.setStatus(status);
            }
        } finally {
            closeConnection();
        }

        return dto;
    }

    public ProductDTO getProductByID(int productID) throws Exception {
        ProductDTO data = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "select ProductName, Price, ImagePath, CategoryID from tbl_product where ProductID = ?";
            psmt = conn.prepareCall(sql);
            psmt.setInt(1, productID);
            rs = psmt.executeQuery();
            while (rs.next()) {
                String productname = rs.getString("ProductName");
                Float price = rs.getFloat("Price");
                String imagePath = rs.getString("ImagePath");
                int categoryID = rs.getInt("CategoryID");
                data = new ProductDTO(productname, price, imagePath, categoryID);
            }
        } finally {
            closeConnection();
        }

        return data;
    }

    public boolean insertProduct(ProductDTO dto) throws Exception {

        boolean result = false;

        try {

            conn = MyConnection.getConnection();
            String sql = "INSERT INTO tbl_product(ProductName,Price,ImagePath,CategoryID) values(?,?,?,?)";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, dto.getProductName());
            psmt.setFloat(2, dto.getPrice());
            psmt.setString(3, dto.getImagePath());
            psmt.setInt(4, dto.getCategoryID());

            result = psmt.executeUpdate() > 0;

        } finally {
            closeConnection();
        }

        return result;
    }

    public int getProductCount() throws Exception {

        int count = 0;
        try {
            conn = MyConnection.getConnection();
            String sql = "select ident_current('tbl_product') as ProductCount";
            psmt = conn.prepareCall(sql);
            rs = psmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("ProductCount");
            }
        } finally {
            closeConnection();
        }

        return count;
    }

    public HashMap<Integer, String> getSize() throws Exception {
        HashMap<Integer, String> data = new HashMap();
        try {
            conn = MyConnection.getConnection();
            String sql = "select SizeID, SizeName from tbl_size";
            psmt = conn.prepareCall(sql);
            rs = psmt.executeQuery();
            while (rs.next()) {
                int sizeID = rs.getInt("SizeID");
                String sizeName = rs.getString("SizeName");

                data.put(sizeID, sizeName);

            }
        } finally {
            closeConnection();
        }

        return data;
    }

    public HashMap<Integer, String> getSizeByProductID(int productID) throws Exception {
        HashMap<Integer, String> data = new HashMap();
        try {
            conn = MyConnection.getConnection();
            String sql = "select b.SizeID, b.SizeName "
                    + "from tbl_product_size a,"
                    + "(select SizeID, SizeName from tbl_size) b "
                    + "where a.SizeID = b.SizeID and a.ProductID = ? order by SizeID ASC";
            psmt = conn.prepareCall(sql);
            psmt.setInt(1, productID);
            rs = psmt.executeQuery();
            while (rs.next()) {
                int sizeID = rs.getInt("SizeID");
                String sizeName = rs.getString("SizeName");

                data.put(sizeID, sizeName);

            }
        } finally {
            closeConnection();
        }

        return data;
    }

    public boolean insertProductSize(int productID, int sizeID) throws Exception {
        boolean result = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "INSERT INTO tbl_product_size(ProductID,SizeID) values(?,?)";
            psmt = conn.prepareCall(sql);
            psmt.setInt(1, productID);
            psmt.setInt(2, sizeID);
            result = psmt.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return result;
    }

    public HashMap<Integer, ToppingDTO> getToping() throws Exception {
        HashMap<Integer, ToppingDTO> data = new HashMap<>();
        try {
            conn = MyConnection.getConnection();
            String sql = "select ToppingID, ToppingName, Price from tbl_topping where Status = 1";
            psmt = conn.prepareCall(sql);
            rs = psmt.executeQuery();
            while (rs.next()) {
                int toppingID = rs.getInt("ToppingID");
                String toppingName = rs.getString("ToppingName");
                float price = rs.getFloat("Price");
                ToppingDTO dto = new ToppingDTO(toppingID, toppingName, price);
                data.put(toppingID, dto);

            }
        } finally {
            closeConnection();
        }

        return data;

    }

    public ToppingDTO getTopingByID(int toppingID) throws Exception {
        ToppingDTO dto = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "select ToppingName, Price from tbl_topping where Status = 1 and ToppingID = ?";
            psmt = conn.prepareCall(sql);
            psmt.setInt(1, toppingID);
            rs = psmt.executeQuery();
            if (rs.next()) {
                String toppingName = rs.getString("ToppingName");
                float price = rs.getFloat("Price");
                dto = new ToppingDTO(toppingID, toppingName, price);
            }
        } finally {
            closeConnection();
        }

        return dto;

    }

    public SizeDTO getSizeByID(int sizeID) throws Exception {
        SizeDTO dto = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "select sizeName from tbl_size where Status = 1 and sizeID = ?";
            psmt = conn.prepareCall(sql);
            psmt.setInt(1, sizeID);
            rs = psmt.executeQuery();
            if (rs.next()) {
                String sizeName = rs.getString("sizeName");

                dto = new SizeDTO(sizeID, sizeName);
            }
        } finally {
            closeConnection();
        }

        return dto;

    }

    public List<ProductDTO> getAllProductFromBeginToEnd(int begin, int end) throws Exception {
        List<ProductDTO> data = new ArrayList<>();
        ProductDTO dto = null;
        String sql = "WITH ProductList AS "
                + "("
                + "SELECT ProductID, ProductName, Price, ImagePath, "
                + "ROW_NUMBER() OVER (ORDER BY Date DESC) AS 'RowNumber' "
                + "FROM tbl_product"
                + ")"
                + "SELECT ProductID, ProductName, Price, ImagePath "
                + "FROM ProductList "
                + "WHERE RowNumber BETWEEN ? AND ?;";
        try {
            conn = MyConnection.getConnection();
            psmt = conn.prepareCall(sql);
            psmt.setInt(1, begin);
            psmt.setInt(2, end);
            rs = psmt.executeQuery();
            while (rs.next()) {

                int productid = rs.getInt("ProductID");
                String productname = rs.getString("ProductName");
                Float price = rs.getFloat("Price");
                String imagePath = rs.getString("ImagePath");
                dto = new ProductDTO(productid, productname, price, imagePath);
                data.add(dto);
            }
        } finally {
            closeConnection();
        }
        return data;
    }

    public List<ProductDTO> getProductByCategoryIDFromBeginToEnd(int begin, int end, int id) throws Exception {
        List<ProductDTO> data = new ArrayList<>();
        ProductDTO dto = null;
        String sql = "WITH ProductList AS "
                + "("
                + "SELECT ProductID, ProductName, Price, ImagePath, "
                + "ROW_NUMBER() OVER (ORDER BY Date DESC) AS 'RowNumber' "
                + "FROM tbl_product where CategoryID = ?"
                + ")"
                + "SELECT ProductID, ProductName, Price, ImagePath "
                + "FROM ProductList "
                + "WHERE RowNumber BETWEEN ? AND ?;";
        try {
            conn = MyConnection.getConnection();
            psmt = conn.prepareCall(sql);
            psmt.setInt(1, id);
            psmt.setInt(2, begin);
            psmt.setInt(3, end);
            rs = psmt.executeQuery();
            while (rs.next()) {

                int productid = rs.getInt("ProductID");
                String productname = rs.getString("ProductName");
                Float price = rs.getFloat("Price");
                String imagePath = rs.getString("ImagePath");
                dto = new ProductDTO(productid, productname, price, imagePath);
                data.add(dto);
            }
        } finally {
            closeConnection();
        }
        return data;
    }

    public List<ProductDTO> getAllPropertyOfProductFromBeginToEnd(int begin, int end, String status) throws Exception {
        List<ProductDTO> data = new ArrayList<>();
        ProductDTO dto = null;
        String sql = "WITH ProductList AS "
                + "("
                + "SELECT b.ProductID, b.ProductName, b.Price, b.ImagePath, c.CategoryName, b.Date,"
                + "ROW_NUMBER() OVER (ORDER BY Date DESC) AS 'RowNumber' "
                + "FROM tbl_product b "
                + "INNER JOIN tbl_category c ON b.CategoryID = c.CategoryID where b.Status = ?"
                + ")"
                + "SELECT ProductID, ProductName, Price, ImagePath, Date, CategoryName "
                + "FROM ProductList "
                + "WHERE RowNumber BETWEEN ? AND ?;";
        try {
            conn = MyConnection.getConnection();
            psmt = conn.prepareCall(sql);
            psmt.setString(1, status);
            psmt.setInt(2, begin);
            psmt.setInt(3, end);
            rs = psmt.executeQuery();
            while (rs.next()) {

                int productid = rs.getInt("ProductID");
                String productname = rs.getString("ProductName");
                String categoryName = rs.getString("CategoryName");
                String dateString = rs.getString("Date");

                Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dateString);

                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

                String createDate = format.format(date);

                Float price = rs.getFloat("Price");
                String imagePath = rs.getString("ImagePath");
                dto = new ProductDTO(productid, productname, price, imagePath);
                dto.setCreateDate(createDate);
                dto.setCategoryName(categoryName);
                data.add(dto);
            }
        } finally {
            closeConnection();
        }
        return data;
    }

    public boolean deleteProduct(int productID) throws Exception {
        boolean result = false;
        try {
            String sql = "Update tbl_product set Status = 'false' where ProductID = ?";
            conn = MyConnection.getConnection();
            psmt = conn.prepareCall(sql);
            psmt.setInt(1, productID);

            result = psmt.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean updateProductNotImage(int productID, String productName, float price, int categoryID, String status) throws Exception {
        boolean result = false;

        String sql = "Update tbl_product set ProductName = ?, Price = ?, CategoryID = ?, Status = ? where ProductID = ?";
        try {
            conn = MyConnection.getConnection();
            psmt = conn.prepareCall(sql);
            psmt.setString(1, productName);
            psmt.setFloat(2, price);
            psmt.setInt(3, categoryID);
            psmt.setString(4, status);
            psmt.setInt(5, productID);
            result = psmt.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return result;
    }

    public boolean deleteSizeByProductID(int productID) throws Exception {
        boolean result = false;

        String sql = "delete from tbl_product_size where ProductID = ?";
        try {
            conn = MyConnection.getConnection();
            psmt = conn.prepareCall(sql);
            psmt.setInt(1, productID);
            result = psmt.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return result;
    }

    public int getAmountOfProducts() throws Exception {
        int count = 0;
        String sql = "select COUNT(ProductID) as CountProduct from tbl_Product";
        conn = MyConnection.getConnection();
        psmt = conn.prepareCall(sql);
        rs = psmt.executeQuery();
        if (rs.next()) {

            count = rs.getInt("CountProduct");

        }
        return count;
    }
    
    public List<SizeDTO> getSizeFromBeginToEnd(int begin, int end,String status) throws Exception {
        
        List<SizeDTO> list ;
        SizeDTO dto;
        String sql = "WITH SizeList AS "
                + "("
                + "SELECT SizeID, SizeName, "
                + "ROW_NUMBER() OVER (ORDER BY SizeID DESC) AS 'RowNumber' "
                + "FROM tbl_Size where Status = ?"
                + ")"
                + "SELECT SizeID, SizeName "
                + "FROM SizeList "
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
                int SizeID = rs.getInt("SizeID");
                String SizeName = rs.getString("SizeName");
                dto = new SizeDTO(SizeID, SizeName);
                list.add(dto);
            }
        } finally{
            closeConnection();
        }
        return list;
    }
    
    
    public int getAmountOfSize() throws Exception {
        int count = 0;
        String sql = "select COUNT(SizeID) as CountSize from tbl_Size";
        conn = MyConnection.getConnection();
        psmt = conn.prepareCall(sql);
        rs = psmt.executeQuery();
        if (rs.next()) {

            count = rs.getInt("CountSize");

        }

        return count;
    }
    
    public SizeDTO getAllPropertyOfSizeByID(int SizeID) throws Exception {
        SizeDTO dto = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "select SizeName, Status from tbl_Size where SizeID = ?";
            psmt = conn.prepareCall(sql);
            psmt.setInt(1, SizeID);
            rs = psmt.executeQuery();
            while (rs.next()) {
                
                String SizeName = rs.getString("SizeName");
                String status = rs.getString("Status");
                if(status.equals("0"))
                {
                    status = "False";
                }
                else {
                     status = "True";
                }
                dto = new SizeDTO(SizeID, SizeName);
                dto.setStatus(status);
                
            }
        } finally {
            closeConnection();
        }

        return dto;
    }
    
     public boolean insert(SizeDTO dto) throws Exception {
        boolean result = false;

        try {

            conn = MyConnection.getConnection();
            String sql = "INSERT INTO tbl_Size(SizeName) values(?)";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, dto.getSizeName());

            result = psmt.executeUpdate() > 0;

        } finally {
            closeConnection();
        }

        return result;
    }
     
     public boolean updateSize(SizeDTO dto) throws Exception {
        boolean result = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "update tbl_Size set SizeName = ?, Status = ? where SizeID = ?";
            psmt = conn.prepareCall(sql);
            psmt.setString(1, dto.getSizeName());
            psmt.setString(2, dto.getStatus());
            psmt.setInt(3, dto.getSizeID());
            result = psmt.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;

    }
     
    public boolean deleteSize(int SizeID) throws Exception
    {
        boolean result = false;
        try {
            String sql = "Update tbl_Size set Status = 'false' where SizeID = ?";
            conn = MyConnection.getConnection();
            psmt = conn.prepareCall(sql);
            psmt.setInt(1, SizeID);

            result = psmt.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    
    public boolean insertCartInfo(String username, String address, String phoneNo) throws Exception
    {
        boolean result = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "insert into tbl_cart_info(Username,Address,PhoneNo) values(?,?,?)";
            psmt = conn.prepareCall(sql);
            psmt.setString(1, username);
            psmt.setString(2, address);
            psmt.setString(3, phoneNo);
            result = psmt.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;

    }
    
    public int getCurrentCartID() throws Exception
    {
        int count = 0;
        try {
            conn = MyConnection.getConnection();
            String sql = "select ident_current('tbl_cart_info') as CartCount";
            psmt = conn.prepareCall(sql);
            rs = psmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("CartCount");
            }
        } finally {
            closeConnection();
        }

        return count;
    }
    
    
    public boolean insertCartDetail(CartItemDTO dto, int quantity, int CartID) throws Exception
    {
        boolean result = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "insert into tbl_cart_detail(CartID, ProductID, SizeID, Quantity) values(?,?,?,?)";
            psmt = conn.prepareCall(sql);
            psmt.setInt(1, CartID);
            psmt.setInt(2, dto.getProductID());
            psmt.setInt(3, dto.getSize().getSizeID());
            psmt.setInt(4, quantity);
            result = psmt.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;

    }
    
     public int getCurrentToppingListID() throws Exception
    {
        int count = 0;
        try {
            conn = MyConnection.getConnection();
            String sql = "select ident_current('tbl_cart_detail') as ToppingCount";
            psmt = conn.prepareCall(sql);
            rs = psmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("ToppingCount");
            }
        } finally {
            closeConnection();
        }

        return count;
    }
     
     public boolean insertCartTopping(int toppingID,int toppingListID) throws Exception
    {
        boolean result = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "insert into tbl_topping_cart_detail(ToppingID, ToppingListID) values(?,?)";
            psmt = conn.prepareCall(sql);
            psmt.setInt(1, toppingID);
            psmt.setInt(2, toppingListID);;
            result = psmt.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;

    }
     
     public List<ProductDTO> search(float from, float to) throws Exception{
        List<ProductDTO> data = null;
        ProductDTO dto = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "select ProductID, ProductName, Price, ImagePath from tbl_product "
                    + "where Status = 1 "
                    + "and Price between ? and ?";
            psmt = conn.prepareCall(sql);
            psmt.setFloat(1, from);
            psmt.setFloat(2, to);
            rs = psmt.executeQuery();
            data = new ArrayList<>();
            while (rs.next()) {
                int productid = rs.getInt("ProductID");
                String productname = rs.getString("ProductName");
                Float price = rs.getFloat("Price");
                String imagePath = rs.getString("ImagePath");
                dto = new ProductDTO(productid, productname, price, imagePath);
                data.add(dto);
            }
        } finally {
            closeConnection();
        }

        return data;
     }
}
