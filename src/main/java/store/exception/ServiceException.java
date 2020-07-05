package store.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceException extends RuntimeException {
    private String logId;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, String logId) {
        super(message);
        this.logId = logId;
    }
}