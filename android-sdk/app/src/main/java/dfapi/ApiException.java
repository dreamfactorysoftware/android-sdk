package dfapi;

/**
 * Custom exception class used by the DreamFactory Android api
 */
public class ApiException extends Exception {
    private int code = 0;
    private String message = null;

    public ApiException() {}

    public ApiException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
