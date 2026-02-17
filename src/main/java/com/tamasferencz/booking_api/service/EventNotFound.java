package com.tamasferencz.booking_api.service;

import com.tamasferencz.booking_api.ResourceNotFoundException;

public class EventNotFound extends ResourceNotFoundException {
    public EventNotFound(String message) {
        super(message);
    }
}
