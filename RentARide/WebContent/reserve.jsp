<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reservation Page</title>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css">
<style>
.logoutbt {
	font: bold 13px Arial;
	text-decoration: none;
	color: black;
	padding: 4px 8px 5px 9px;
	border-top: 1px solid #CCCCCC;
	border-right: 1px solid #333333;
	border-bottom: 1px solid #333333;
	border-left: 1px solid #CCCCCC;
}
</style>
</head>
<body>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.2/js/bootstrap.min.js"></script>

 <script type="text/javascript">
        function goToSearch(){
        	alert("Hi");
        	window.location.href = "/searchRentals.jsp";

        }

 </script>
<br/>
<a href='index.jsp' class="logoutbt" style="background-color: #dedede;float:right;margin-right:3%;">Logout</a>
<a href='bookings.jsp' class="logoutbt" style="background-color: #dedede;float:right;margin-right:1%;">Home</a>
<br/>
<br/>
<h1 align="center">Your Car Rental Reservation</h1>	

<div class="col-lg-6 col-lg-offset-3">
		<div class="well">
			<div class="container">
				<div class="row">
					<div class="col-lg-6">
						<form id="myForm" method="post" class="bs-example form-horizontal"
							action="reservationDetails">
							<fieldset>
								<legend>Review and Reserve</legend>
								
								<div class="form-group">
									<label class="col-lg-4 control-label">Pick Up/ Drop Location :</label>
									<div class="col-lg-8">
										<label class="col-lg-6 control-label" id="pickUpLocation">${pickUpLocation}</label>
									</div>
								</div>

								<div class="form-group">
									<label class="col-lg-4 control-label">Pick Up Date :</label>
									<div class="col-lg-8">
										<label class="col-lg-6 control-label" id="pickUpDate">${pickUpDate}</label>
									</div>
								</div>

								<div class="form-group">
									<label class="col-lg-4 control-label">Pick Up Time :</label>
									<div class="col-lg-8">
										<label class="col-lg-6 control-label" id="pickUpTime">${pickUpTime}</label>
									</div>
								</div>

								<div class="form-group">
									<label class="col-lg-4 control-label">Drop Off Date :</label>
									<div class="col-lg-8">
										<label class="col-lg-6 control-label" id="dropOffDate">${dropOffDate}</label>
									</div>
								</div>

								<div class="form-group">
									<label class="col-lg-4 control-label">Drop Off Time :</label>
									<div class="col-lg-8">
										<label class="col-lg-6 control-label" id="dropOffTime">${dropOffTime}</label>
									</div>
								</div>

								<div class="form-group">
									<label class="col-lg-4 control-label">Vehicle Type :</label>
									<div class="col-lg-8">
										<label class="col-lg-6 control-label" id="vehicleType">${vehicleType}</label>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-lg-4 control-label">Vehicle Make :</label>
									<div class="col-lg-8">
										<label class="col-lg-6 control-label" id="vehMake">${vehMake}</label>
									</div>
								</div>
								
								
								
								<legend>Customer Information</legend>
								
								<div class="form-group">
									<label class="col-lg-4 control-label">Customer Name :</label>
									<div class="col-lg-8">
										<label class="col-lg-6 control-label" id="name">${name}</label>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-lg-4 control-label">Phone Number :</label>
									<div class="col-lg-8">
										<label class="col-lg-6 control-label" id="phNum">${phNum}</label>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-lg-4 control-label">Email Address :</label>
									<div class="col-lg-8">
										<label class="col-lg-6 control-label" id="email">${email}</label>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-lg-4 control-label">License Number :</label>
									<div class="col-lg-8">
										<label class="col-lg-6 control-label" id="licenseNum">${licenseNum}</label>
									</div>
								</div>
								
								<div class="form-group">
									<b>You will be charged $${fees} for this rental.</b>
								</div>
								
								<div class="col-lg-9 col-lg-offset-10">
									<input type="hidden" name="vehRegNo" value="${vehRegNo}"/>
									<input type="hidden" name="vehTypeId" value="${vehTypeId}"/>
									<input type="hidden" name="pickUpDateTime" value="${pickUpDateTime}"/>
									<input type="hidden" name="dropOffDateTime" value="${dropOffDateTime}"/>
									<input type="hidden" name="fees" value="${fees}"/>
									<input type="hidden" name="username" value="${username}"/>
									<input type="hidden" name="action" value="RESERVE"/>
									<input type="submit" class="btn btn-primary" value="Book">
									
									<input type="submit" class="btn btn-primary" name="Modify" value="Modify"/>

									

								</div>

							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
