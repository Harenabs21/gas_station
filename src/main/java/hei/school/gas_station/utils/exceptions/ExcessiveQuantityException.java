package hei.school.gas_station.utils.exceptions;

public class ExcessiveQuantityException extends IllegalArgumentException{
    public ExcessiveQuantityException(String error) {
        super(error);
    }
}
