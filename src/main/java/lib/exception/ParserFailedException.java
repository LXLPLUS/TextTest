package lib.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParserFailedException extends Exception{
    String reason;
    public ParserFailedException(String reason) {
        this.reason = reason;
        log.warn(this.reason);
    }
}
