package lib.exception;

public class ParserFailedException extends Exception{
    String reason;
    public ParserFailedException(String reason) {
        this.reason = reason;
    }
}
