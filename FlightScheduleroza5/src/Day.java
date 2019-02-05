
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Osman
 */
public class Day {
    private Date date;
    private static Connection connection;
    private static DBConnection dbConnection;
    private static PreparedStatement selectAllDays;
    private static PreparedStatement insertDay;
    public Day(){
    }
    public Day(Date date){
        this.date = date;
    }
    public String toString()
    {
        return date.toString();
    }
   
     public void setDate(Date date){
        this.date = date;
    }   
    public Date getDate(){
        return date;
    }
    public Date toDate()
    {
        return date;
    }
    public int addDay(Date date){
    int Result = 0;
        try{
            connection = dbConnection.getConnection();
            insertDay = connection.prepareStatement("INSERT INTO DAY (DATE) VALUES (?)");
            insertDay.setDate(1,date);
            Result = insertDay.executeUpdate();
        }
        catch(SQLException eq){
            Result = 99;
        }
        
        return Result;
    }
    public static List< String > getAllDays()
    {
        connection = dbConnection.getConnection();
        
        List<String> results = null;
        ResultSet resultSet = null;
        try
        {
            selectAllDays = connection.prepareStatement("SELECT * FROM DAY");
            resultSet = selectAllDays.executeQuery();
            results = new ArrayList< String >();
            while(resultSet.next())
            {
                results.add(resultSet.getDate(1).toString());
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return results;        
    }
}
