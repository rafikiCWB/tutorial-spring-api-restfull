package org.brisa.nlw.journey.trip;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TripRepository extends CrudRepository<Trip, UUID> {
}
