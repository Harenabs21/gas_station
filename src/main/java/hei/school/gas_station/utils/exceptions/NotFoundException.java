package hei.school.gas_station.utils.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String error){
        super(error);
    }
}
