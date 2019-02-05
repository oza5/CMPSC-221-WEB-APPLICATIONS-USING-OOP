                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
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
public class Booking {
    private Timestamp time; 
    private  String Name;
    private  String FlightNumber;
    private  Date date;
    private static Connection connection;
    private static DBConnection dbConnection;
    private  PreparedStatement insertBooking;
    private  PreparedStatement getFlightSeats;
    private static PreparedStatement selectCustomersBooked;
    private static PreparedStatement getPersonBooked;
    private static PreparedStatement deleteBooking;
    private static PreparedStatement getFlightName;
    public Date getDay()
    {
        return date;
    }
      
    
    public String getFlightNumber()
    {
        return FlightNumber;
    }
    
    public String getPassenger()
    {
        return Name;
    }
    public Booking() 
    {
        
        
    }
    
    public Booking(String Name, String FlightNumber, Date date) 
    {
        this.Name = Name;
        this.date = date;
        this.FlightNumber =FlightNumber;
        
    }
    public static List <String> getPersonBooked(String name) throws SQLException{
        
        
        connection = dbConnection.getConnection();
        getPersonBooked = connection.prepareStatement("SELECT FLIGHTNAME,DATE FROM BOOKING WHERE NAME=?");
        List<String> results = null;
        ResultSet resultSet = null;
        try
        {
            
            
            getPersonBooked.setString(1, name);
            resultSet = getPersonBooked.executeQuery();
            results = new ArrayList< String >();
            while(resultSet.next())
            {
                results.add(resultSet.getString(1));
                results.add(resultSet.getDate(2).toString());
            }
        }
                
           
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return results;
    }
    public List<String> GetFlight(String name,Date date){
        connection = dbConnection.getConnection();
        //getFlightName = connection.prepareStatement("SELECT FLIGHTNAME FROM BOOKING WHERE NAME=? AND DATE=?");
        List<String> results = null;
        ResultSet resultSet = null;
        try
        {
            getFlightName = connection.prepareStatement("SELECT FLIGHTNAME FROM BOOKING WHERE NAME=? AND DATE=?");
            getFlightName.setString(1, name);
            getFlightName.setDate(2, date);
            resultSet = getFlightName.executeQuery();
            results = new ArrayList< String >();
            while(resultSet.next())
            {
                results.add(resultSet.getString(1));
            }
        }
        catch(SQLException eq){
            eq.printStackTrace();
            
        }
        return results;
        
        
    }
    public int getSeatsBooked(String FlightName, Date date)
    {
        int seatsBooked = 0;
        ResultSet resultSet;
        connection = dbConnection.getConnection();
        try
        {
        connection = dbConnection.getConnection();
        getFlightSeats = connection.prepareStatement("select count(FLIGHTNAME) from BOOKING where FLIGHTNAME = ? and DATE = ?");
             
            getFlightSeats.setString(1, FlightName); getFlightSeats.setDate(2, date); 
            resultSet = getFlightSeats.executeQuery(); 
            resultSet.next(); 
            seatsBooked = resultSet.getInt(1);
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
            System.exit(1);
        }
        return seatsBooked;
    }
    public int deleteBooking(String name,Date date){
        int result = 0;
        connection = dbConnection.getConnection();
        try{
            connection = dbConnection.getConnection();
            deleteBooking = connection.prepareStatement("DELETE FROM BOOKING WHERE NAME = ? AND DATE = ?");
            deleteBooking.setString(1,name);
            deleteBooking.setDate(2,date);
            result = deleteBooking.executeUpdate();
            
        }catch(SQLException eq){
            result = 99;
        }
        return result;
    }
     public int deleteBookingDrop(String name,Date date){
        int result = 0;
        connection = dbConnection.getConnection();
        try{
            connection = dbConnection.getConnection();
            deleteBooking = connection.prepareStatement("DELETE FROM BOOKING WHERE FLIGHTNAME = ? AND DATE = ?");
            deleteBooking.setString(1,name);
            deleteBooking.setDate(2,date);
            result = deleteBooking.executeUpdate();
            
        }catch(SQLException eq){
            result = 99;
        }
        return result;}
    public String toString()
    {
        return Name+" ";
    }
    
    public static List< Booking > getCustomersBooked(String FlightName, Date date) throws SQLException 
    {
        connection = dbConnection.getConnection();
        selectCustomersBooked = connection.prepareStatement("SELECT * FROM BOOKING WHERE FLIGHTNAME=? AND DATE=?");
        List<Booking> results = null;
        ResultSet resultSet = null;
        try
        {
            
            selectCustomersBooked.setString(1, FlightName);
            selectCustomersBooked.setDate(2, date);
            resultSet = selectCustomersBooked.executeQuery();
            results = new ArrayList< Booking >();
            while(resultSet.next())
            {
                results.add(new Booking(resultSet.getString(1),resultSet.getString(3),resultSet.getDate(2)));
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
            catch (SQLException sqlException)
            {
                sqlException.printStackTrace();
                dbConnection.close();
            }
        }
        return results;        
    }
    
    public int addBooking(String aname, String aflightNumber, Date adate,Timestamp atime)
    {
        int result = 0;
        
        try
        {
            connection = dbConnection.getConnection();
            insertBooking = connection.prepareStatement("INSERT INTO BOOKING (NAME,DATE,FLIGHTNAME,TIMESTAMP) VALUES (?,?,?,?)");
            
            insertBooking.setString(1, aname);
            insertBooking.setDate(2, adate);
            insertBooking.setString(3, aflightNumber);
            insertBooking.setTimestamp(4,atime);
            result = insertBooking.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
            dbConnection.close();
        }
        return result;
    }
    
}
