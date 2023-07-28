package ba.unsa.etf.rpr.exceptions;

public class HotelExceptions extends Exception{

    public HotelExceptions(String msg, Exception exp){
        super(msg,exp);
    }

    public HotelExceptions(String msg){
        super(msg);
    }
}
