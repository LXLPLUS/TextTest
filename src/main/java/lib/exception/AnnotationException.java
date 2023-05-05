package lib.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AnnotationException extends Exception {
    String reason;
    public AnnotationException(String reason) {
        this.reason = reason;
        log.warn(this.reason);
    }
}
