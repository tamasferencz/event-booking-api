package com.tamasferencz.booking_api.service;

import com.tamasferencz.booking_api.BadRequestException;
import com.tamasferencz.booking_api.ResourceConflictException;
import com.tamasferencz.booking_api.entity.Speaker;
import com.tamasferencz.booking_api.repository.SpeakerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class SpeakerService {

    private final SpeakerRepository speakerRepository;

    public SpeakerService(SpeakerRepository speakerRepository) {this.speakerRepository = speakerRepository;}

    public Speaker createSpeaker(Speaker speaker){
        validateSpeakerData(speaker);

        if (speakerRepository.existsByEmail(speaker.getEmail())) {
            throw new ResourceConflictException("This email is taken: " + speaker.getEmail());
        }

        return speakerRepository.save(speaker);
    }

    public List<Speaker> getAllSpeakers(){
        return speakerRepository.findAll();
    }

    public Optional<Speaker> getSpeakerById(Long id) {
        return Optional.of(speakerRepository.findById(id).orElseThrow(() -> new SpeakerNotFound("Speaker with id %d not found.".formatted(id))));
    }

    @Transactional
    public Optional<Speaker> updateSpeaker(Long id, Speaker updatedSpeaker) {

        var speaker = speakerRepository.findById(id);
        if (speaker.isEmpty()) {
            throw new SpeakerNotFound("Speaker with id %d not found!".formatted(id));
        }

        validateSpeakerData(updatedSpeaker);

        return speaker.map(existingSpeaker -> {

            if (!existingSpeaker.getEmail().equals(updatedSpeaker.getEmail()) &&
                    speakerRepository.existsByEmail(updatedSpeaker.getEmail())) {
                throw new ResourceConflictException("This email is taken: " + updatedSpeaker.getEmail());
            }

            existingSpeaker.setName(updatedSpeaker.getName());
            existingSpeaker.setEmail(updatedSpeaker.getEmail());
            existingSpeaker.setBio(updatedSpeaker.getBio());
            existingSpeaker.setRating(updatedSpeaker.getRating());
            existingSpeaker.setFee(updatedSpeaker.getFee());

            return speakerRepository.save(existingSpeaker);
        });
    }

    public void deleteSpeaker(Long id) {
        var speaker = speakerRepository.findById(id);
        if (speaker.isEmpty()) {
            throw new SpeakerNotFound("Speaker with id %d not found!".formatted(id));
        }
        speakerRepository.deleteById(id);
    }

    private void validateSpeakerData(Speaker speaker) {
        if (speaker.getFee() < 0) {
            throw new BadRequestException("The fee cannot be less than zero.");
        }

        if (speaker.getRating() < 0 || speaker.getRating() > 10) {
            throw new BadRequestException("The rating should be between 0 and 10.");
        }
    }
}
