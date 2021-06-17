DROP PROCEDURE IF EXISTS SearchVehicle;
DELIMITER //

CREATE PROCEDURE SearchVehicle 
(
in	VehType int,
IN Loc int,
in PickupDate datetime,
in dropdate datetime)
BEGIN
	SELECT vh.regno,rl.name,Vt.type,vh.mileage,vh.make,vt.priceperhr,vh.modelno,vh.lastservicedate, DATE_sub(PickupDate,interval 1 minute) as PickupDate,DATE_add(dropdate,interval 1 minute) as dropdate
	from vehicles VH
    left join vehicletype VT on vt.vehicletypeid=vh.Type
    left join rentlocations RL on rl.rentlocationid=vh.location
	where RegNo not in
    (
    select vehicle 
    from reservation RS
    left join vehicles V on V.RegNo=RS.Vehicle
    left join rentlocations rl on rl.rentlocationid=v.location
    where VehType=rs.vehicletype and 
    loc=rl.rentlocationid and (
    PickupDate between RS.PickUpDateTime and RS.DropOffDateTime or
    dropdate between RS.PickUpDateTime and RS.DropOffDateTime) and
    booked = True
    ) and vh.location=Loc and vh.Type=vehtype
    ;

END //
DELIMITER ;