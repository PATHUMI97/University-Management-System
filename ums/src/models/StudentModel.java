package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

public class StudentModel {

    Connection conn;

    public StudentModel() {
        conn = db.dbConnection.getConnection();
    }

    public String insertStudent(String cource, String sem, String nic, String email, String fname, String lname, String scl, String mobile, java.util.Date dob, String gender, String age, String city, String father, String mother, String foccupation, String moccupation) {
        String msg = null;
        String query = "INSERT INTO `student` (NIC, email, cource, sem_year, first_name, last_name, school, dob, mobile, gender, age, address, father, mother, father_occupation, mother_occupation) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, nic);
            psm.setString(2, email);
            psm.setString(3, cource);
            psm.setString(4, sem);
            psm.setString(5, fname);
            psm.setString(6, lname);
            psm.setString(7, scl);
            psm.setDate(8, new java.sql.Date(dob.getTime()));
            psm.setString(9, mobile);
            psm.setString(10, gender);
            psm.setString(11, age);
            psm.setString(12, city);
            psm.setString(13, father);
            psm.setString(14, mother);
            psm.setString(15, foccupation);
            psm.setString(16, moccupation);

            psm.execute();

            msg = "Success";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Error" + e.getMessage();
        }
        return msg;
    }

    public void loadTable(String keword, DefaultTableModel dtm) {
        String loadata = "SELECT * FROM student INNER JOIN student_enrollment ON student.NIC = student_enrollment.nic WHERE student.NIC LIKE ? ";

        try {

            PreparedStatement ps = conn.prepareStatement(loadata);
            ps.setString(1, "%" + keword + "%");
            ResultSet rs = ps.executeQuery();
            Object[] dataRow;
            dtm.setRowCount(0);
            while (rs.next()) {
                String id = rs.getString("NIC");
                String rollno = rs.getString("idstudent_enrollment");
                String name = rs.getString("first_name")+" "+rs.getString("last_name");
                String cource = rs.getString("cource");
                String sm = rs.getString("sem_year");
                dataRow = new Object[]{id, rollno, name, cource, sm};
                dtm.addRow(dataRow);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String createRollNo(String nic, String code) {
        String msg = null;
        String query = "INSERT INTO `student_enrollment` ( nic, c_code) VALUES (?,?)";
        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, nic);
            psm.setString(2, code);
            psm.execute();
            msg = "Success";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Error" + e.getMessage();
        }
        return msg;
    }

    public Object[] getSelectedStudentDataByNic(String nic) {
       String query ="SELECT * FROM student WHERE NIC =?";
        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, nic);
            ResultSet rs = psm.executeQuery();
            Object[] dataRow =null;
            while (rs.next()) {                
                String id =rs.getString("NIC");//0
                String fname =rs.getString("first_name");//1
                String lname =rs.getString("last_name");//2
                String email =rs.getString("email");//3
                String crs =rs.getString("cource");//4
                String scl =rs.getString("school");//5
                String dob =rs.getString("dob");//6
                String mobile =rs.getString("mobile");//7
                String gender =rs.getString("gender");//8
                String age =rs.getString("age");//9
                String address =rs.getString("address");//10
                String father =rs.getString("father");//11
                String mother =rs.getString("mother");//12
                String fjob =rs.getString("father_occupation");//13
                String mjob =rs.getString("mother_occupation");//14
                
                
                 dataRow = new Object[]{id, fname, lname, email, crs,scl,dob,mobile,gender,age,address,father,mother,fjob,mjob};
            }
            return dataRow;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
