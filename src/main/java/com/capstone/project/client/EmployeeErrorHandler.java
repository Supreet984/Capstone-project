package com.capstone.project.client;

import org.springframework.web.client.ResponseErrorHandler;

public class EmployeeErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(org.springframework.http.client.ClientHttpResponse response) throws java.io.IOException {
        return false;
    }

    @Override
    public void handleError(org.springframework.http.client.ClientHttpResponse response) throws java.io.IOException {

    }
}
