/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AventronMD;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author dagom
 */
public class AutomovilMD {

    private DataSource getConnection0() throws NamingException {
        Context c = new InitialContext();
        return (DataSource) c.lookup("java:comp/env/connection0");
    }
    
    
}
