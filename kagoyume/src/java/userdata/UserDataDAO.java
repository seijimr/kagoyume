package userdata;

import base.DBManager;
import base.YahooApiManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import ky.ItemDataBeans;

public class UserDataDAO {
    
        public void insert(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("INSERT INTO user_t(name,password,mail,address,total,newDate) VALUES(?,?,?,?,?,?)");
            st.setString(1, ud.getName());
            st.setString(2, ud.getPass());
            st.setString(3, ud.getMail());
            st.setString(4, ud.getAddress());
            st.setInt(5, 0);
            st.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }

    }

    //メールアドレスとパスワードから
    public UserDataDTO LoginCheck(String mail, String pass) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();

            String sql = "SELECT * FROM user_t WHERE mail = ? AND password = ?";

            st = con.prepareStatement(sql);
            st.setString(1, mail);
            st.setString(2, pass);

            ResultSet rs = st.executeQuery();
            UserDataDTO resultUd = new UserDataDTO();

            if (rs.next()) {
                resultUd.setUserID(rs.getInt(1));
                resultUd.setName(rs.getString(2));
                resultUd.setPass(rs.getString(3));
                resultUd.setMail(rs.getString(4));
                resultUd.setAddress(rs.getString(5));
                resultUd.setTotal(rs.getInt(6));
                resultUd.setNewDate(rs.getTimestamp(7));
                resultUd.setDeleteflg(rs.getInt(8));
            } else {
                return null;
            }

            return resultUd;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }

    }

        
    public boolean RegisteredConfirm(String mail) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();

            String sql = "SELECT * FROM user_t WHERE mail = ?";

            st = con.prepareStatement(sql);
            st.setString(1, mail);

            ResultSet rs = st.executeQuery();

            //既に存在する名前ならtrue　存在しなければfalseを返す
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }

    }
    
    //もっと良いやり方はある気がするけど時間がないのでとりあえずこれで
    //ユーザーID、商品コード、値段、発送方法を引数にとり購入処理を行う
    //商品分このメソッドを回す
    public void Buy(int id,String code,int price,String type) throws SQLException{
        Connection con = null;
        PreparedStatement stin = null;
        PreparedStatement stse = null;
        PreparedStatement stup = null;
        
        try{
            con = DBManager.getConnection();
            stin =  con.prepareStatement("INSERT INTO buy_t(userID,itemCode,type,buyDate) VALUES(?,?,?,?)");
            stin.setInt(1, id);
            stin.setString(2, code);
            stin.setInt(3, new Integer(type));
            stin.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            stin.executeUpdate();
            
            stse = con.prepareStatement("SELECT * FROM user_t WHERE userID = ?");
            stse.setInt(1, id);
            ResultSet rs = stse.executeQuery();
            rs.next();
            int total = rs.getInt(6)+price;
            
            stup =  con.prepareStatement("UPDATE user_t SET total = ?");
            stup.setInt(1, total);
            stup.executeUpdate();
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }

    }
    
         public ArrayList<ItemDataBeans> BuyHistory(int userID) throws SQLException, IOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();

            String sql = "SELECT * FROM buy_t WHERE userID = ?";
            ArrayList<ItemDataBeans> list = new ArrayList<ItemDataBeans>();
            ItemDataBeans idb;

            st = con.prepareStatement(sql);
            st.setInt(1, userID);

            ResultSet rs = st.executeQuery();

            while(rs.next()){
                idb =new YahooApiManager().codeSearch(rs.getString(3));
                idb.setBuyDate(rs.getTimestamp(5));
                list.add(idb);
            }
            return list;
                    
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }

    }


         public void delete(int id) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st = con.prepareStatement("UPDATE user_t SET deleteFlg = ? WHERE userID = ?");
            st.setInt(1,1);
            st.setInt(2, id);
            st.executeUpdate();
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }

    }
         
         
                  public void update(UserDataBeans udb,int id) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st = con.prepareStatement("UPDATE user_t SET name = ? , password = ? , mail = ? , address = ? WHERE userID = ?");
            st.setString(1,udb.getName());
            st.setString(2,udb.getPass());
            st.setString(3,udb.getMail());
            st.setString(4,udb.getAddress());
            st.setInt(5, id);
            st.executeUpdate();
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }

    }
}
