package lib.exception;

public class UndefinedException extends Exception{
    String reason;
    public UndefinedException(String reason) {
        this.reason = reason;
    }
}
