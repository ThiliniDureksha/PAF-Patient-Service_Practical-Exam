<%@page import="model.PatientRepository"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Patient Details</title>



<link  rel="stylesheet"  href="Styles/formStyle.css"/>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.4.1.min.js"></script>
<script src="Components/patient.js"></script>


</head>



<body>



<nav class="navbar navbar-expand-sm bg-primary navbar-dark">
  <ul class="navbar-nav">
    <li class="nav-item active">
      <a class="nav-link" href="#">Health Care System</a>
    </li>
  </ul>
</nav>




<div class="container py-5">
    <div class="row">
        <div class="col-md-10 mx-auto">
            <form id="formPatient" name="formPatient">
                <div class="form-group row">
                    <div class="col-sm-6">
                        <label for="inputFirstname">First name</label>
                        <input type="text" class="form-control" id="Firstname"   name="Firstname" placeholder="First name">
                    </div>
                    <div class="col-sm-6">
                        <label for="inputLastname">Last name</label>
                        <input type="text" class="form-control" id="Lastname"  name="Lastname" placeholder="Last name">
                    </div>
                </div>
                
                	<!-- GENDER -->
               
                <div class="form-group row">
                    <div class="col-sm-6">
                        <label >Gender</label>
                        	<div class="input-group input-group-sm mb-3">
					      	<select class="form-control form-control-sm" id='Gender'name="Gender">
						<option selected value=''> Choose Gender </option>
						<option value='Male'>Male</option>
						<option value='Female'>Female</option>
					</select> 
					
				</div>

                    </div>
                    <div class="col-sm-6">
                        <label >BirthDay</label>
                        <input type="date" class="form-control" id="Birthday"  name="Birthday" placeholder="Birthday">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-sm-6">
                        <label >Email</label>
                        <input type="text" class="form-control" id="Email"  name="Email" placeholder="Email">
                    </div>
                    <div class="col-sm-3">
                        <label for="inputState">NIC</label>
                        <input type="text" class="form-control" id="Nic"  name="Nic" placeholder="NIC">
                    </div>
                    <div class="col-sm-3">
                        <label for="inputPostalCode">Phone No</label>
                        <input type="text" class="form-control" id="Phone"  name="Phone" placeholder="Phone No">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-sm-6">
                        <label>User name</label>
                        <input type="text" class="form-control" id="username"  name="username" placeholder="username">
                    </div>
                    <div class="col-sm-6">
                        <label >Password</label>
                        <input type="text" class="form-control" id="password"  name="password" placeholder="Password">
                    </div>
                </div>
                
                	<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary" style="width:923px">
 					<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
            </form>
            <br><br>
            		<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>
					<br>
					
					     
					
			</div>
				
			
        </div>

    			
    </div>
    
    		
    						<h4 align="center">Patient Details Table</h4>
							<br>
							<div id="divItemsGrid1" class="table table-striped" align="center">
							 <%
							 PatientRepository patientRepository = new PatientRepository();
							 //out.print(patientRepository.getPatients());
							 %>
							
    						</div>
	
</body>
</html>