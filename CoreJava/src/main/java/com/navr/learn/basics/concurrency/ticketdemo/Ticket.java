package com.navr.learn.basics.concurrency.ticketdemo;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Ticket domain model (data only). Booking operations moved to TicketService.
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Ticket {
    @EqualsAndHashCode.Include
    private int id;

    private String description;

    private BigDecimal price = BigDecimal.ZERO;

    /** current owner; null when available */
    private String owner;

    public enum Status { AVAILABLE, BOOKED, CANCELLED }

    private Status status = Status.AVAILABLE;

    private Instant createdAt = Instant.now();

    private Instant bookedAt;

        /**
         * Per-ticket lock for fine-grained synchronization.
         * transient: not serialized; final: initialized once.
         */
        private final transient ReentrantLock lock = new ReentrantLock();

        @Builder
    private Ticket(int id, String description, java.math.BigDecimal price, String owner, Status status, java.time.Instant createdAt, java.time.Instant bookedAt) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.owner = owner;
        this.status = status;
        this.createdAt = createdAt;
        this.bookedAt = bookedAt;
    }

    // Read-only helpers
    public boolean isAvailable() { return status == Status.AVAILABLE; }
    public boolean isBooked() { return status == Status.BOOKED; }
    public boolean isCancelled() { return status == Status.CANCELLED; }
}
