package lib.exception;

public class ParserFailedException extends Exception{
    int reason;
    final static int NOT_NUMBER = 0;
    ParserFailedException(int reason) {
        this.reason = reason;
    }
}
