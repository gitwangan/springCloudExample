package com.example.common.domain;

import lombok.Data;

@Data
public class SearchResponseData {
    private Employee[] employeeResponse;
    private String portResponse;
}
