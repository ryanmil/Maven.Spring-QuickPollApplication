package io.zipcoder.tc_spring_poll_application.exception;

public class ValidationError {

    private String code;
    private String message;



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
