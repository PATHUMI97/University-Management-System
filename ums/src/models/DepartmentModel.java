
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author PATHUMI
 */
public class DepartmentModel {
    Connection conn;
//string id = 10, string name=examination department
    public DepartmentModel(){
        conn=db.dbConnection.getConnection();
    }
    
    public String insertDepartment(String id, String name, String des) {
   String msg =null;
   String query ="INSERT INTO `department` (id_department, name, description) VALUES (?,?,?)";
        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, id);
             psm.setString(2, name);
              psm.setString(3, des);
              
              psm.execute();
              
              msg="success";
        } catch (Exception e) {
            msg ="error"+e.getMessage();
        }
   return msg;
    }
    
}
