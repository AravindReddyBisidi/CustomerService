package com.alpha.customerservice.dto;

public class MovieResponseDto {

    private int statuscode;
    private String message;
    private CreateMovieDto data;

    public MovieResponseDto() {
    }

    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CreateMovieDto getData() {
        return data;
    }

    public void setData(CreateMovieDto data) {
        this.data = data;
    }
}