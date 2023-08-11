package ra.productcrud.service;

import ra.productcrud.model.User;
import ra.productcrud.util.ConnectDB;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserService implements IGenericService<User,Long> {
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void save(User user)  {
        Connection conn = ConnectDB.getConnection();
        try{
            if(user.getId() == null){

                // register
                CallableStatement callSt = conn.prepareCall("{call REGISTER(?,?)}");
                callSt.setString(1,user.getEmail());
                callSt.setString(2,user.getPassword());
                callSt.executeUpdate();
            }

        }catch (SQLException e){
            throw  new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }

    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public User findById(Long aLong) {
        return null;
    }
     public  User login(String email, String password){
         Connection conn = ConnectDB.getConnection();
         User userLogin =null;
         try{
             CallableStatement callSt = conn.prepareCall("{call LOGIN(?,?)}");
             callSt.setString(1,email);
             callSt.setString(2,password);
             ResultSet rs = callSt.executeQuery();

             while (rs.next()){
                 userLogin = new User();
                 userLogin.setId(rs.getLong("id"));
                 userLogin.setEmail(rs.getString("email"));
                 userLogin.setFullName(rs.getString("fullname"));
                 userLogin.setPhone(rs.getString("phone"));
                 userLogin.setAvatar(rs.getString("avatar"));
                 userLogin.setStatus(rs.getBoolean("status"));
             }
         }catch (SQLException e){
             throw  new RuntimeException(e);
         }finally {
             ConnectDB.closeConnection(conn);
         }
         return userLogin;
     }
}
