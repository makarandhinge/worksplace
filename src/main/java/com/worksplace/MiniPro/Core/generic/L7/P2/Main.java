package com.worksplace.MiniPro.Core.generic.L7.P2;

public class Main {
    public static void main(String[] args) {
        HttpRequest request = HttpRequest
                .builder()
                .method("POST")
                .url("http://localhost:8080")
                .header("Authorization", "Bearer token")
                .header("Content-Type", "application/json")
                .body("{ \"name\": \"John\" }")
                .timeout(8000)
                .build();
    }
}
