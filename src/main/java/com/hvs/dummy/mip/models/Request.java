package com.hvs.dummy.mip.models;

import lombok.Data;

import java.util.Map;

@Data
public class Request {
    private Map<String, String> message;
}
