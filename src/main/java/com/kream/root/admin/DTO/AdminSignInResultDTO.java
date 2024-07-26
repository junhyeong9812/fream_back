package com.kream.root.admin.DTO;

public class AdminSignInResultDTO {
    private boolean success;
    private int code;
    private String msg;
    private String token;

    public AdminSignInResultDTO() {
    }

    public AdminSignInResultDTO(boolean success, int code, String msg, String token) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.token = token;
    }

    // Getters and setters

    @Override
    public String toString() {
        return "AdminSignInResultDTO [success=" + success + ", code=" + code + ", msg=" + msg + ", token=" + token + "]";
    }
}
