
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
public class Flight {
    private  int seats;
    private String FlightNum;
    private static Connection connection;
    private static DBConnection dbConnection;
    private static PreparedStatement getSeatsavail;
    private static PreparedStatement getAllFlights;
    private static PreparedStatement insertFlight;
    private static PreparedStatement deleteFlight;
    
public Flight(String FlightNum,int seats){
    this.FlightNum = FlightNum;
    this.seats = seats;
}
public Flight(){
}
public int getSeats(){
    return seats;
}
public String getFlightName(){
    return FlightNum;
}
public int addFlight(String FlightNum,int seats){
    int Result = 0;
    try{
        connection = dbConnection.getConnection();
        insertFlight = connection.prepareStatement("INSERT INTO FLIGHT (NAME,SEATS) VALUES (?,?)");
        insertFlight.setString(1,FlightNum);
        insertFlight.setInt(2,seats);
        Result = insertFlight.executeUpdate();
    }catch(SQLException eq){
        Result = 99;
    }
return Result;
}

public int deleteFlight(String FlightNum){
   
        int result = 0;
        connection = dbConnection.getConnection();
        try{
            connection = dbConnection.getConnection();
            deleteFlight = connection.prepareStatement("DELETE FROM FLIGHT WHERE NAME = ?");
            deleteFlight.setString(1,FlightNum);
            
            result = deleteFlight.executeUpdate();
            
        }catch(SQLException eq){
            result = 99;
        }
        return result;
    
}

public int getLeftSeats(String FlightNum){
    int seatsLeft = 0;
    ResultSet resultSet = null;
    connection = dbConnection.getConnection();
    try{
        getSeatsavail = connection.prepareStatement("SELECT SEATS FROM FLIGHT WHERE Name = ? "); 
        getSeatsavail.setString(1, FlightNum); 
        resultSet = getSeatsavail.executeQuery(); 
        resultSet.next(); 
        seatsLeft = resultSet.getInt(1);
    }
    catch(SQLException eq){
        eq.printStackTrace();
        System.exit(1);
        }
        return seatsLeft;
    }
    
public static List< String > getAllFlights() 
    {
        connection = dbConnection.getConnection();
        
        List<String> results = null;
        ResultSet resultSet = null;
        try
        {
            getAllFlights = connection.prepareStatement("SELECT * FROM FLIGHT");
            resultSet = getAllFlights.executeQuery();
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
       
        
        return results;        
    }
    
            
}

