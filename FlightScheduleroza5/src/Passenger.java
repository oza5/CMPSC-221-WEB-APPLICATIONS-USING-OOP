
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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
public class Passenger {
    private  String Name;
    private static Connection connection;
    private static DBConnection dbConnection;
    private  PreparedStatement insertPassenger;
    private static PreparedStatement selectAll;
    public Passenger(){
        
    }
    public Passenger(String Name){
        this.Name = Name;
    }
    
    public void setPassenger(String Name){
        this.Name = Name;
    }
    public String toString()
    {
        return Name;
    }
    
    public String getPassenger(){
        return Name;
    }

    public int addPassenger(String Name){
        int Result = 0;
        try{
            connection = dbConnection.getConnection();
            insertPassenger = connection.prepareStatement("INSERT INTO PASSENGER (Name) VALUES (?)");
            insertPassenger.setString(1,Name);
            Result = insertPassenger.executeUpdate();
            
        }
        catch(SQLException eq){
            Result = 99;
        }
        
        return Result;
        
    }
    
    public static List < String > getAllPassengers(){
        connection = dbConnection.getConnection();
        List<String> results = null;
        ResultSet resultSet = null;
        try
        {
            selectAll = connection.prepareStatement("SELECT * FROM PASSENGER");
            resultSet = selectAll.executeQuery();
            results = new ArrayList< String >();
            while(resultSet.next())
            {
                results.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            try
            {
                resultSet.close();
            }
            catch (SQLException eq)
            {
                eq.printStackTrace();
                dbConnection.close();
            }
        }
        return results;        
    }
        
        
    }
            



