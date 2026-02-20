package com.tamasferencz.booking_api.service;

import com.tamasferencz.booking_api.ResourceNotFoundException;

public class SpeakerNotFound extends ResourceNotFoundException {
    public SpeakerNotFound(String message) {
        super(message);
    }
}
