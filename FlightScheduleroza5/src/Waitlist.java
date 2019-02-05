
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class Waitlist {
    private Timestamp time;
    private String Name;
    private Date date;
    private String FlightNumber;
    private PreparedStatement insertWaitlist;
    private PreparedStatement getPosition;
    private Timestamp pos;
    private int pos1;
    private static PreparedStatement getPerson;
    private static DBConnection dbConnection;
    private static PreparedStatement selectCustomersBooked;
    private static PreparedStatement shiftToBooking;
    private static PreparedStatement getSize;
    private static PreparedStatement deleteWaitlist;
    private static Connection connection;
    
    public void addWaitList(String Name, Date date, String FlightNumber,Timestamp time )
    {
        this.Name =Name;
        this.date = date;
        this.FlightNumber = FlightNumber;
        this.time = time;
    }
    
    public Date getDay()
    {
        return date;
    }
      
    
    public String getFlightNumber()
    {
        return FlightNumber;
    }
    public int getPos(){
        return pos1;
    }
    public Timestamp getTime(){
        return time;
    }
    public static List <String> getPersonWaitlist(String name) throws SQLException{
        
        
        connection = dbConnection.getConnection();
        getPerson = connection.prepareStatement("SELECT FLIGHTNAME,DATE FROM WAITLIST WHERE NAME=?");
        List<String> results = null;
        ResultSet resultSet = null;
        try
        {
            
            
            getPerson.setString(1, name);
            resultSet = getPerson.executeQuery();
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
    public List <String> shiftToBooking(String flightName,Date date){
        ResultSet resultSet;
        List<String> result = null;
        try{
            connection = dbConnection.getConnection();
            shiftToBooking = connection.prepareStatement("SELECT NAME FROM WAITLIST WHERE FLIGHTNAME = ? AND DATE = ?");
            shiftToBooking.setString(1,flightName);
            shiftToBooking.setDate(2,date);
            resultSet = shiftToBooking.executeQuery();
            result = new ArrayList< String >();
            while(resultSet.next())
            {
                result.add(resultSet.getString(1));
                
            }
            
        }catch(SQLException eq){
           
        }
        return result;
    }
     public int deleteWaitlist(String name){
        int result = 0;
        connection = dbConnection.getConnection();
        try{
            connection = dbConnection.getConnection();
            deleteWaitlist = connection.prepareStatement("DELETE FROM WAITLIST WHERE NAME = ?");
            deleteWaitlist.setString(1,name);
            
            result = deleteWaitlist.executeUpdate();
            
        }catch(SQLException eq){
            result = 99;
        }
        return result;
    }
    public int getWaitListSize(){
         
        int r = 0;
        ResultSet resultSet = null;
        int position = 1;
        try
        {
            connection = dbConnection.getConnection();
            getSize = connection.prepareStatement("SELECT COUNT(NAME) FROM WAITLIST");
            //r = getWaitlistPosition(aflightNumber,adate);
            
            //insertWaitlist.setInt(4, r);
            resultSet = getSize.executeQuery();
            resultSet.next(); 
            position = resultSet.getInt(1);
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
            System.exit(1);
        }
        return position;
    }
    public int getWaitlistPosition(String flightName, Date adate)
    {
        int position = 1;
        ResultSet resultSet;
        connection = dbConnection.getConnection();
        try
        {
            connection = dbConnection.getConnection();
        getPosition = connection.prepareStatement("SELECT COUNT(FLIGHTNAME) from WAITLIST where FLIGHTNAME = ? and DATE = ?");
             
            getPosition.setString(1, flightName); getPosition.setDate(2, adate); 
            resultSet = getPosition.executeQuery(); 
            resultSet.next(); 
            position = resultSet.getInt(1);
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
            System.exit(1);
        }
        return position+1;
    }
     public int addWaitlist(String aname, String aflightNumber, Date adate,Timestamp time)
    {
        int result = 0;
        int r = 0;
        pos1 = 0;
        try
        {
            connection = dbConnection.getConnection();
            insertWaitlist = connection.prepareStatement("INSERT INTO WAITLIST "+"(NAME,DATE,FLIGHTNAME,TIMESTAMP)"+"VALUES (?,?,?,?)");
            //r = getWaitlistPosition(aflightNumber,adate);
            insertWaitlist.setString(1, aname);
            insertWaitlist.setString(3, aflightNumber);
            insertWaitlist.setDate(2, adate);
            insertWaitlist.setTimestamp(4, time);
            //insertWaitlist.setInt(4, r);
            result = insertWaitlist.executeUpdate();
            pos1+=1;
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
            dbConnection.close();
        }
        return r;
    }
    
    public Waitlist(String aname, String aflight, Date adate, Timestamp aposition)
    {
        Name=aname;
        date=adate;
        FlightNumber=aflight;
        pos=aposition;
    }
    
    public String toString()
    {
        return "\n"+"PASSENGER NAME = "+Name+"  "+"  FLIGHT NO. = "+FlightNumber+"  "+"  TIME OF ATTEMPTED BOOKING = "+pos+"\n";
    }
    
    public static List< Waitlist > getCustomersBookedWaitlist(Date adate) throws SQLException 
    {
        connection = dbConnection.getConnection();
        selectCustomersBooked = connection.prepareStatement("SELECT * FROM WAITLIST WHERE DATE=?");
        List<Waitlist> results = null;
        ResultSet resultSet = null;
        try
        {
            
            
            selectCustomersBooked.setDate(1, adate);
            resultSet = selectCustomersBooked.executeQuery();
            results = new ArrayList< Waitlist >();
            while(resultSet.next())
            {
                results.add(new Waitlist(resultSet.getString(1),resultSet.getString(3),resultSet.getDate(2),resultSet.getTimestamp(4)));
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
    
    public Waitlist()
    {
        
    }
    
    public String getPassenger()
    {
        return Name;
    }
    
}
