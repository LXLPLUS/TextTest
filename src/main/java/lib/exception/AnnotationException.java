package lib.exception;

public class AnnotationException extends Exception {
    String reason;
    public AnnotationException(String reason) {
        this.reason = reason;
    }
}
