
package model;

import java.sql.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Ebill
{ private PreparedStatement preparedStmt;//A common method to connect to the DB
private Connection connect()
{
Connection con = null;
try
{
Class.forName("com.mysql.jdbc.Driver");
//Provide the correct details: DBServer/DBName, username, password
con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebill", "root", "");
}
catch (Exception e)
{e.printStackTrace();}
return con;
}//method to insert ebill
public String insertEbill(String ebillid, String accno, String amount, String startdate, String enddate, String refno) {
Connection conn = connect();
String Output = "";
try {
if (conn == null) {
return "Error occuring to the database for inserting";
}
//SQL query
String query = " insert into ebill_structure values (?, ?, ?, ?, ?,?)";
PreparedStatement preparedStatement = conn.prepareStatement(query);
//binding data to SQL query
preparedStmt.setInt(1, 0);
preparedStmt.setString(2, accno);
preparedStmt.setString(3, amount);
preparedStmt.setString(4, startdate);
preparedStmt.setString(5, enddate);
preparedStmt.setString(6, refno);
//execute the SQL statement
preparedStatement.execute();
conn.close(); String newEbills = readEbills();
Output = "{\"status\":\"success\", \"data\": \"" + newEbills + "\"}";
} catch(Exception e) {
Output = "{\"status\":\"error\", \"data\": \"Failed to insert the ebill\"}";
System.err.println(e.getMessage());
}
return Output;
}//method to read bill details
public String readEbills()
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{
return "Error while connecting to the database for reading.";
}
// Prepare the html table to be displayed
output = "<table border='1'><tr><th>Bill ID</th>"
+ "<th>Bill ACC no</th><th>Bill Amount</th>"
+ "<th>Start Date</th>"
+ "<th>End Date</th>"+ "<th>Reference No</th>"
+ "<th>Update</th><th>Remove</th></tr>";

String query = "select * from ebill_structure";
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
while (rs.next())
{
int ebillid = rs.getInt("ebillid");
String accno = rs.getString("accno");
String amount = rs.getString("amount");
String startdate = rs.getString("startdate");
String enddate = rs.getString("enddate");
String refno = rs.getString("refno");
// Add into the html table
output += "<tr style=\"border: 1px solid #ddd; padding: 8px;\"><td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: Blue;\">" + ebillid + "</td>";
output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + accno + "</td>";
output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + amount + "</td>";
output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + startdate + "</td>";
output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + enddate + "</td>";
output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + refno + "</td>";
// buttons
output += "<td><input name='btnUpdate' type='button' value='Update' "
+ "class='btnUpdate btn btn-secondary' data-ebillid='" + ebillid + "'></td>"
+ "<td><input name='btnRemove' type='button' value='Remove' "
+ "class='btnRemove btn btn-danger' data-ebillid='" + ebillid + "'></td></tr>";
}
con.close();
// Complete the html table
output += "</table>";
}
catch (Exception e)
{
output = "Error while reading the ebills.";
System.err.println(e.getMessage());
}
return output;
}//method to update ebill details
public String updateEbill(String ebillid, String accno, String amount, String startdate, String enddate, String refno ) {
Connection conn = connect();
String Output = "";
try {
if (conn == null) {
return "Database connection error Occured";
}
//SQL query
String query = "UPDATE ebill_structure SET ebillid=?, accno=?, amount=?, startdate=?, enddate=?, refno=? WHERE ebillid=?";
//binding data to SQL query
PreparedStatement preparedStatement = conn.prepareStatement(query);
preparedStmt.setString(1, ebillid);
preparedStmt.setString(2, accno);
preparedStmt.setString(3, amount);
preparedStmt.setString(4, startdate);
preparedStmt.setString(5, enddate);
preparedStmt.setString(6, refno);
//execute the SQL statement
preparedStatement.executeUpdate();
conn.close();
String newEbills = readEbills;
Output = "{\"status\":\"success\", \"data\": \"" + newEbills + "\"}";
} catch(Exception e) {
Output = "{\"status\":\"error\", \"data\":\"Failed to update ebill.\"}";
System.err.println(e.getMessage());
}
return Output;
}//method to delete data
public String deleteEbill(String ebillid) {
String Output = "";
Connection conn = connect();
try {
if (conn == null) {
return "Database connection error";
}
//SQL query
String query = "DELETE FROM ebill_structure WHERE ebillid = ?";
//binding data to the SQL query
PreparedStatement preparedStatement = conn.prepareStatement(query);
preparedStatement.setInt(1, Integer.parseInt(ebillid));
//executing the SQL statement
preparedStatement.execute();
conn.close();
String newEbills = readEbills();
Output = "{\"status\":\"success\", \"data\": \"" + newEbills + "\"}";
} catch(Exception e) {
Output = "{\"status\":\"error\", \"data\":\"Failed to delete ebill.\"}";
System.err.println(e.getMessage());
}
return Output;
}
}

