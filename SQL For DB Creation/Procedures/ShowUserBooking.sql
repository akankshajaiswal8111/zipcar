DROP PROCEDURE IF EXISTS ShowUserBooking;
DELIMITER //

CREATE PROCEDURE ShowUserBooking 
(
in	username varchar(255) )
BEGIN
	
    select reservationid,vh.make,vh.modelno,cast(rs.pickupdatetime as date) as pickupdate,DATE_FORMAT(pickupdatetime, '%H:%i') as pickuptime,cast(rs.dropoffdatetime as date) as dropoffdate,DATE_FORMAT(dropoffdatetime, '%H:%i') as dropofftime,rl.name
    from reservation RS
    left join vehicletype VT on vt.vehicletypeid=RS.vehicletype
    left join vehicles VH on vh.Regno=RS.Vehicle
    left join rentlocations RL on RL.Rentlocationid=vh.location
    where username like RS.Customer and booked=1;

END //
DELIMITER ;