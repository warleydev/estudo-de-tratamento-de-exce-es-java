package model.entities;

import model.exceptions.DomainException;
import org.w3c.dom.ls.LSOutput;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private Integer roomNumber;
    private Date checkIn;
    private Date checkOut;

    public Reservation() {
    }

    public Reservation(Integer roomNumber, Date checkin, Date checkout) throws DomainException{
        if(!checkout.after(checkin)){
            throw new DomainException("Check-out date must be after check-in date");
        }
        this.roomNumber = roomNumber;
        this.checkIn = checkin;
        this.checkOut = checkout;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckin() {
        return checkIn;
    }

    public Date getCheckout() {
        return checkOut;
    }

    public long duration(){
        long diff = checkOut.getTime() - checkIn.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public void updateDates(Date checkIn, Date checkOut) throws DomainException{
        Date now = new Date();
        if (checkIn.before(now) || checkOut.before(now)){
            throw new DomainException("Check-out date must be after check-in date");
        }
        if(!checkOut.after(checkIn)){
            throw new DomainException("Reservation dates for update must be future dates");
        }
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    @Override
    public String toString(){
            return "Room "
                    +roomNumber
                    + ", check-in: "
                    + sdf.format(checkIn)
                    + ", check-out: "
                    + sdf.format(checkOut)
                    + ", "
                    + duration()
                    + " nights";
    }

}
