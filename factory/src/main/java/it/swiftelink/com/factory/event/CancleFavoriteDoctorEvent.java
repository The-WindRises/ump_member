package it.swiftelink.com.factory.event;

/**
 * @Description:
 * @Author: klk
 * @CreateDate: 2020/1/4 14:31
 */
public class CancleFavoriteDoctorEvent {
    private boolean success;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
