

# Main Features:<br /> 
As a Manager of Rent-A-Ride, the user can:<br /> 
->Add new Vehicle Types<br /> 
->Add new Rental Locations<br /> 
->Add new Vehicles<br /> 
->Update registration and late fees<br /> 
->Activate/Terminate Customers<br /> 
->Add new Admins<br /> 
<br /> 
As an admin the user can perfoem the below functions:<br /> 
->Add new Vehicle Types<br /> 
->Add new Rental Locations<br /> 
->Add new Vehicles<br /> 
->Activate/Terminate Customers<br /> 

A customer can sign up by filling out a form with personal details, license and payment informatioon, and also paying the registration fee.<br /> 
On logging in, a cusotmer can view the current bookings and edit them.<br/>
On logging in, a customer can search for vehicles, based on location and vehicle type and also make a reservation.<br /> 
If a vehicle is not available in a particular location, same vehicle suggestions at different locations are listed.<br /> 
If the customer cancels his trip reservation one hour after the pick up time, a cancellation fee of one hour rental is applied.<br/> 
If the customer ends his trip after the drop off time, a late fee will be added to the total rental amount.<br /> 


# Techical Specifications:<br /> 
Used MYSQL database and built a J2EE app using JSP and Servlets.<br /> 
The app runs on Apache Tomcat 9 Server.<br /> 
We have deployed the MYSQL Database to an AWS RDS Instance and we are deploying the application to an EC2 instance and from the code we are connecting to the RDS instance. This makes the app globally accessible. EC2 instance has been load balanced and auto scaled for scalability and elasticity.<br />








