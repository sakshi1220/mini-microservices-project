package io.com.hotelService.HotelService.exception;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String s){
        super(s);
    }
    public ResourceNotFoundException(){
        super("Not Found");
    }
}
