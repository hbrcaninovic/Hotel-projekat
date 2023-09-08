package ba.unsa.etf.rpr.exceptions;

/** Class that represents user defined Exception */
public class HotelExceptions extends Exception {

    /** HotelsException with two parameters
     * @param msg message
     * @param exp exception from Exception class
     */
    public HotelExceptions(String msg, Exception exp){
        super(msg,exp);
    }

    /** HotelsException with one parameter
     * @param msg message
     */
    public HotelExceptions(String msg){
        super(msg);
    }
}
