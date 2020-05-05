package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import util.DatabaseConnection;

public class PatientRepository {

	
	DatabaseConnection databaseConnection = new DatabaseConnection();
	
	
	//========================= getPatients ==========================
	

	public String getPatients() {

		String output = "";
		try {
			Connection con = databaseConnection.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
						
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>First Name</th><th>Last Name</th></th><th>Gender</th>"
					 + "<th>Birthday</th><th>Email</th><th>NIC</th><th>Phone</th><th>Username</th><th>Password</th><th>Update</th><th>Remove</th></tr>"; 

			String query = "select * from patient";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				int Patient_id = rs.getInt("Patient_id");
				String Firstname = rs.getString("Firstname");
				String Lastname = rs.getString("Lastname");
				String Gender = rs.getString("Gender");
				String Birthday = rs.getString("Birthday");
				String Email = rs.getString("Email");
				String Nic = rs.getString("Nic");
				String Phone = rs.getString("Phone");
				String username = rs.getString("username");
				String password = rs.getString("password");

				// Add into the html table
				output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" +Patient_id+ "'>" +Firstname+"</td>";
				output += "<td>" + Lastname + "</td>";
				output += "<td>" + Gender + "</td>";
				output += "<td>" + Birthday + "</td>";
				output += "<td>" + Email + "</td>";
				output += "<td>" + Nic + "</td>";
				output += "<td>" + Phone + "</td>";
				output += "<td>" + username + "</td>";
				output += "<td>" + password + "</td>";


				output += "<td><input name='btnUpdate' type='button'value='Update'class='btnUpdate btn btn-secondary'></td> "
						+ "<td><input name='btnRemove' type='button' value='Remove'class='btnRemove btn btn-danger' data-itemid='"+Patient_id+"'>" + "</td></tr>";
				
			}

			con.close();
			// Complete the html table
			output += "</tr></table>";

		} catch (Exception e) {
			output = "Error while reading the Patients Details.";
			System.err.println(e.getMessage());
		}

		return output;
	}
	
	

	//====================== AddPatients  ========================
	
		public String AddPatients(String fname, String lname, String gender, String birthday, String email, String nic,String phone,String uname,String pass) {

		
			String output = "";
			try {

				Connection con = databaseConnection.connect();

				if (con == null) {
					return "Error while connecting to the database";
				}
	
				// create a prepared statement
				String query = " INSERT INTO patient (Firstname, Lastname, Gender, Birthday, Email, Nic,Phone,username,password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);

				
				// binding values
				preparedStmt.setString(1, fname);
				preparedStmt.setString(2, lname);
				preparedStmt.setString(3, gender);
				preparedStmt.setString(4, birthday);
				preparedStmt.setString(5, email);
				preparedStmt.setString(6, nic);
				preparedStmt.setString(7, phone);
				preparedStmt.setString(8, uname);
				preparedStmt.setString(9, pass);
			
	
				preparedStmt.execute();
				con.close();
				
				String newpatient = getPatients();
				output = "{\"status\":\"success\", \"data\": \"" + newpatient + "\"}";

			} catch (Exception e) {
				output = "{\"status\":\"error\", \"data\": \"Error while inserting the patient details.\"}";
				System.err.println(e.getMessage());
			}

			System.out.print(output);
			return output;
		}
		
		
		
	
		
		
		
		//============================= updatePatients ==============================
		
		public String updatePatients(String Patient_id, String fname, String lname, String gender, String birthday, String email, String nic,String phone,String uname,String pass) {

			String output = "";

			try {
				Connection con = databaseConnection.connect();
				if (con == null) {
					return "Error while connecting to the database for updating.";
				}
				// create a prepared statement
				String query = "UPDATE patient SET Firstname =?,Lastname =?,Gender =?,Birthday =?,Email =?,Nic =?,Phone =?,username =?,password =? WHERE Patient_id = ?";
				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values
				preparedStmt.setString(1, fname);
				preparedStmt.setString(2, lname);
				preparedStmt.setString(3, gender);
				preparedStmt.setString(4, birthday);
				preparedStmt.setString(5, email);
				preparedStmt.setString(6, nic);
				preparedStmt.setString(7, phone);
				preparedStmt.setString(8, uname);
				preparedStmt.setString(9, pass);
				preparedStmt.setInt(10, Integer.parseInt(Patient_id));
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				String newpatient = getPatients();
				output = "{\"status\":\"success\", \"data\": \"" +newpatient + "\"}";
				 
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\": \"Error while updating the Patient.\"}";
				 
				System.err.println(e.getMessage());
			}
				
			return output;
				 
		} 
		
		
		
		//============================= deletePatients ==============================	
		
		public String deletePatients(String Patient_id)
		{
			String output = "";
		 
			try
			{
				Connection con = databaseConnection.connect();
				
				if (con == null)
				{
					return "Error while connecting to the database for deleting.";
				} 
		
				// create a prepared statement
				String query = "DELETE FROM patient WHERE Patient_id=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
		 
				preparedStmt.setString(1, Patient_id);
				preparedStmt.execute();
				con.close();
		
				String newpatient = getPatients();
				output = "{\"status\":\"success\", \"data\": \"" + newpatient + "\"}";
		 
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\":\"Error while deleting the Patient.\"}";
				System.err.println(e.getMessage());
		 
			}
		 
			return output;
		 
		}
	
	}
		