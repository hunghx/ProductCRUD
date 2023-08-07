package ra.productcrud.service;

import ra.productcrud.model.Product;
import ra.productcrud.util.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IGenericService<Product, Long> {
    @Override
    public List<Product> findAll() {
        Connection conn = ConnectDB.getConnection();
        List<Product> products = new ArrayList<>();
        try {
            CallableStatement callSt = conn.prepareCall("{call findAllProduct}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getLong("id"));
                p.setName(rs.getString("name"));
                p.setDescriptions(rs.getString("descriptions"));
                p.setPrice(rs.getDouble("price"));
                p.setStock(rs.getInt("stock"));
                p.setImageUrl(rs.getString("image_url"));
                p.setStatus(rs.getBoolean("status"));
                products.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return products;
    }

    @Override
    public void save(Product product) {
        Connection conn = ConnectDB.getConnection();

        try {
            if (product.getId() == null) {
                // thêm moi
                CallableStatement callSt = conn.prepareCall("{call insertProduct(?,?,?,?,?,?)}");
                callSt.setString(1, product.getName());
                callSt.setString(2, product.getDescriptions());
                callSt.setDouble(3, product.getPrice());
                callSt.setInt(4, product.getStock());
                callSt.setString(5, product.getImageUrl());
                callSt.setBoolean(6, product.isStatus());
                callSt.executeUpdate();
            } else {
                // cap nhat
                CallableStatement callSt = conn.prepareCall("{call updateProduct(?,?,?,?,?,?,?)}");
                callSt.setLong(1, product.getId());
                callSt.setString(2, product.getName());
                callSt.setString(3, product.getDescriptions());
                callSt.setDouble(4, product.getPrice());
                callSt.setInt(5, product.getStock());
                callSt.setString(6, product.getImageUrl());
                callSt.setBoolean(7, product.isStatus());
                callSt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }

    }

    @Override
    public void delete(Long id) {
        Connection conn = ConnectDB.getConnection();
        try {
            CallableStatement callSt = conn.prepareCall("{call deleteProduct(?)}");
            callSt.setLong(1,id);
            callSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public Product findById(Long id) {
       Connection conn = ConnectDB.getConnection();
       Product p = null;
        try {
            CallableStatement callSt = conn.prepareCall("{call findById(?)}");
            callSt.setLong(1,id);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                p = new Product();
                p.setId(rs.getLong("id"));
                p.setName(rs.getString("name"));
                p.setDescriptions(rs.getString("descriptions"));
                p.setPrice(rs.getDouble("price"));
                p.setStock(rs.getInt("stock"));
                p.setImageUrl(rs.getString("image_url"));
                p.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return p;
    }
}