$(document).ready(function()
{
	$("#alertSuccess").hide();
	$("#alertError").hide();
});

//SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validatePatientForm();
	if (status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	// If valid------------------------
	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";

	$.ajax(
	{
		url : "PatientAPI",
		type : type,
		data : $("#formPatient").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			 onPatientSaveComplete(response.responseText, status);
			 //console.log(onPatientSaveComplete(response.responseText, status));
		}
	});
});


function onPatientSaveComplete(response, status)
{
	
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
 
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divItemsGrid1").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error")
	{
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else
	{
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidItemIDSave").val("");
	$("#formPatient")[0].reset();
}

//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidItemIDSave").val($(this).closest("tr").find('#hidItemIDUpdate').val());
	$("#Firstname").val($(this).closest("tr").find('td:eq(0)').text());
	$("#Lastname").val($(this).closest("tr").find('td:eq(1)').text());
	$("#Gender").val($(this).closest("tr").find('td:eq(2)').text());
	$("#Birthday").val($(this).closest("tr").find('td:eq(3)').text());
	$("#Email").val($(this).closest("tr").find('td:eq(4)').text());
	$("#Nic").val($(this).closest("tr").find('td:eq(5)').text());
	$("#Phone").val($(this).closest("tr").find('td:eq(6)').text());
	$("#username").val($(this).closest("tr").find('td:eq(7)').text());
	$("#password").val($(this).closest("tr").find('td:eq(8)').text());
	
}); 

//REMOVE==========================================
$(document).on("click", ".btnRemove", function(event)
		{
		 $.ajax(
		 {
		 url : "PatientAPI",
		 type : "DELETE",
		 data : "Patient_id =" + $(this).data("itemid"),
		 dataType : "text",
		 complete : function(response, status)
		 {
		   onPatientDeleteComplete(response.responseText, status);
		 }
		 
		 });
		});

function onPatientDeleteComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divItemsGrid1").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error")
	{
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else
	{
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

//CLIENT-MODEL================================================================
function validatePatientForm()
{
	//Firstname
	if ($("#Firstname").val().trim() == "")
	{
		return " Please enter Valid firstname";
	}
	
	// Check numeric value
	var fname = $("#Firstname").val().trim();
	if ($.isNumeric(fname)) {
		return "First name can't be Numeric";
	}
	

	
//Lastname-----------------------------------------------
	
	if ($("#Lastname").val().trim() == "")
	{
		return " Please enter Valid Lastname";
	}
	// Check numeric value
	var lname = $("#Lastname").val().trim();
	if ($.isNumeric(lname)) {
		return "Last name can't be Numeric";
	}
	
	
	
//Gender-------------------------------------------------
	if ($("#Gender").val().trim() == "")
	{
		return " you can't Skip Gender";
	}
	
	//Birthday
	if ($("#Birthday").val().trim() == "")
	{
		return " Please enter your Birthday";
	}
	
//Email-----------------------------------
	
	if ($("#Email").val().trim() == "")
	{
		return " Please enter Email";
	}
	
    //email pattern
	var e = /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/;	
	var email = $("#Email").val().trim();
	if (e.test(email) == false) 
	{
	return "Please enter valid email address";
	}
	
	
//Nic-----------------------------------------
	
	
	if ($("#Nic").val().trim() == "")
	{
		return "Please enter NIC.";
	}
	
	var valNIC = /^[0-9]{10}[vV]$/
		var vnic = $("#Nic").val().trim()

			if(valNIC.test(vnic)== false)
			{
			 return "Please enter valid NIC"	
			}
	

//Phone--------------------------------	
	if ($("#Phone").val().trim() == "")
	{
		return "Please enter Phone No.";
	}
	
	// Check numeric value
	var phone = $("#Phone").val().trim();
	if (!$.isNumeric(phone)) {
		return "Insert a correct Phone No";
	}
	
	// check length
	var pattern = /^\d{10}$/;
	if (!pattern.test(phone)) {
		return "Phone No should have 10 numbers";
	}
	
//------------------------------------

	if ($("#username").val().trim() == "")
	{
		return "Please enter username.";
	}
	
	if ($("#password").val().trim() == "")
	{
		return "Please enter password.";
	}
	
	
	return true;
}















