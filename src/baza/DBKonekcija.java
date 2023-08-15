/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author PC
 */
public class DBKonekcija {

    public Connection kon = null;

    public DBKonekcija() {
    }

    public Connection zapocniKonekciju() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        kon = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/it350projekat", "root", "");
        return kon;
    }

    public void prekiniKonekciju() throws SQLException {
        kon.close();
    }
}
