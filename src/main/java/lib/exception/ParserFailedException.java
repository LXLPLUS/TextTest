package lib.exception;

public class ParserFailedException extends Exception{
    int reason;
    ParserFailedException(int reason) {
        this.reason = reason;
    }
}
