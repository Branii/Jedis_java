package com.pivo.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pivo.config.MySqlConnection;
import com.pivo.utility.Utility;

public class Model extends MySqlConnection {

    public static String insertData(String id, String title, String price) { // simple pojo

        try{
            String sql = "INSERT INTO products (id, title, price) VALUES (?,?,?)";
            PreparedStatement pstmt = getConnection().prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, title);
            pstmt.setString(3, price);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return "Data inserted successfully";
            } else {
                return "Failed to insert data";
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }

       return "count not insert";
       
    }

    public static String getProductList() throws SQLException{
        String sql = "SELECT * FROM products";
        PreparedStatement pstmt = getConnection().prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Product> products = new ArrayList<>();
        while (rs.next()) {
            Product product = new Product(
                rs.getString("id"),
                rs.getString("title"),
                rs.getString("price")
                );
            products.add(product);
        }

        // Convert productList to JSON using Gson
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String productListJson = gson.toJson(products);

        Utility.pushToCacheString('products', productListJson);

        return productListJson;
    }
    
}
