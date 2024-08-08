package org.brisa.nlw.journey.participant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/participants")
public class ParticipantController {

    private ParticipantRepository repository;

    @PostMapping("{id}/confirm")
    ResponseEntity<Participant> confirmParticipant(@PathVariable UUID id, ParticipantRequestPayload payload) {
        Optional<Participant> participant = this.repository.findById(id);
        if (participant.isPresent()) {
            Participant rawParticipant = new Participant();
            rawParticipant.setIsConfirmed(true);
            rawParticipant.setName(payload.name());

            this.repository.save(rawParticipant);
            return ResponseEntity.ok(rawParticipant);

        }
        return ResponseEntity.notFound().build();
    }
}
