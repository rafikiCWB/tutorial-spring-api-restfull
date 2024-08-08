package org.brisa.nlw.journey.trip;

import org.brisa.nlw.journey.participant.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trip")
public class TripController {

    @Autowired
    private TripRepository repository;

    @Autowired
    private ParticipantService participantService;

    //Trip
    @GetMapping
    public ResponseEntity<TripCreateResponse> createTrip(@RequestBody TripRequestPayload payload) {
       Trip newTrip = new Trip(payload);

       this.repository.save(newTrip);
       this.participantService.registerParticipantsToEvent(payload.emails_to_invite(), newTrip);

       return ResponseEntity.ok(new TripCreateResponse(newTrip.getId()));

    }
}
