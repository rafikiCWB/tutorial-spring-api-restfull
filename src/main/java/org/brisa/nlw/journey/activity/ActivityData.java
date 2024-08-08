package org.brisa.nlw.journey.activity;

import java.time.LocalDateTime;
import java.util.UUID;

public record ActivityData(UUID id, String tittle, LocalDateTime occurs_at) {
}
