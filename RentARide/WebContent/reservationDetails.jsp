<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reservation Details</title>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css">
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
	<script
		src="//netdna.bootstrapcdn.com/bootstrap/3.0.2/js/bootstrap.min.js"></script>
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
								<legend>Reservation Details</legend>
								<div class="form-group">
									<label class="col-lg-4 control-label">Customer Name :</label>
									<div class="col-lg-8">
										<label class="col-lg-6 control-label" id="custName">${custName}</label>
									</div>
								</div>

								<div class="form-group">
									<label class="col-lg-4 control-label">Pick Up/ Drop
										Location :</label>
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
									<label class="col-lg-4 control-label">Vehicle Reg No :</label>
									<div class="col-lg-8">
										<label class="col-lg-6 control-label" id="vehicleType">${vehRegNo}</label>
									</div>
								</div>

								<div class="form-group">
									<label class="col-lg-4 control-label">Rental Charges
										($) :</label>
									<div class="col-lg-8">
										<label class="col-lg-6 control-label" id="rentalFees">${rentalFees}</label>
									</div>
								</div>

								<div class="form-group">
									<label class="col-lg-4 control-label">Comments :</label>

									<div class="col-lg-8">
										<input type="text" name="comments" id="comments" class="form-control"
											placeholder="Enter Your Comments Here"
											style="height: 83px" />

									</div>
								</div>
							
									<div class="col-lg-9 col-lg-offset-8">
									<button class="btn btn-primary" name="action" value="CANCEL">Cancel Reservation</button>
									<input type="hidden" name="username" value="${custName}"/>
									<input type="hidden" name="vehRegNo" value="${vehRegNo}"/>
									<input type="hidden" name="pickUpTime" value="${pickUpTime}"/>
									<input type="hidden" name="pickUpDate" value="${pickUpDate}"/>
									
									<button class="btn btn-primary" name="action" value="END">End Trip</button>
									<input type="hidden" name=username value="${custName}"/>
									<input type="hidden" name="vehRegNo" value="${vehRegNo}"/>
									<input type="hidden" name="rentalFees" value="${rentalFees}"/>
									<input type="hidden" name="dropOffTime" value="${dropOffTime}"/>
									<input type="hidden" name="dropOffDate" value="${dropOffDate}"/>
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
