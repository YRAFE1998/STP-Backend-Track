package application.model;

import application.model.ResponseData;

import java.util.List;

public class ResponseBuilder<ResponseData>{

    private String error;
    private List<ResponseData> data;
    private String message;

    public ResponseBuilder() {
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseBuilder(String error, List<ResponseData> data) {
        this.error = error;
        this.data = data;
    }

    public ResponseBuilder(String error, String message) {
        this.error = error;
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<ResponseData> getData() {
        return data;
    }

    public void setData(List<ResponseData> data) {
        this.data = data;
    }
}
