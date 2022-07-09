
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;


public class LectureModel {
    Connection conn;
    
    public LectureModel(){
        conn=db.dbConnection.getConnection();
    }

    public String insertLecturer(String nic, String name, String address, String email, String mobile, String faculty, String qual, String ex, String age, String gender) {
        String msg = null;
        String  query ="INSERT INTO `lecturer` (NIC, name, address, email, mobile, faculty_id, qualification, experience, age, gender) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement psm =conn.prepareStatement(query);
            psm.setString(1, nic);
            psm.setString(2, name);
            psm.setString(3, address);
            psm.setString(4, email);
            psm.setString(5, mobile);
            psm.setString(6, faculty);
            psm.setString(7, qual);
            psm.setString(8, ex);
            psm.setString(9, age);
            psm.setString(10, gender);
            
            psm.execute();
            
            msg="Success";
        } catch (Exception e) {
            e.printStackTrace();
            msg="Error"+e.getMessage();
        }
    return  msg;
    }

    public void loadTable(String keyword, DefaultTableModel dtm) {
       String loaddata = "SELECT * FROM `lecturer` WHERE NIC LIKE ?";
       
        try {
            PreparedStatement psm = conn.prepareStatement(loaddata);
            psm.setString(1, "%" + keyword + "%");
             ResultSet rs = psm.executeQuery();
            Object[] dataRow;
            dtm.setRowCount(0);
            while (rs.next()) {
                String id = rs.getString("NIC");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String qal = rs.getString("qualification");
                String ex = rs.getString("experience");
                dataRow = new Object[]{id, name, email, qal, ex};
                dtm.addRow(dataRow);

            }
            
        } catch (Exception e) {
        }
       
    }

    public Object[] getSelectedLectureDataByNic(String nic) {
        String query ="SELECT * FROM `lecturer` WHERE NIC =?";
        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, nic);
            ResultSet rs = psm.executeQuery();
            Object[] dataRow =null;
            while (rs.next()) {                
                String id =rs.getString("NIC");//0
                String name =rs.getString("name");//1
                String address =rs.getString("address");//2
                String email =rs.getString("email");//3
                String mobile =rs.getString("mobile");//4
                String facid =rs.getString("faculty_id");//5
                String qual =rs.getString("qualification");//6
                String exp =rs.getString("experience");//7
                String age =rs.getString("age");//8
                String gender =rs.getString("gender");//9
               
                
                
                 dataRow = new Object[]{id, name, address, email, mobile,facid,qual,exp,age,gender,address};
            }
            return dataRow;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
