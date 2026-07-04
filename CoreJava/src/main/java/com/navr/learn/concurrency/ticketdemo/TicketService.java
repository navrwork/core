package com.navr.learn.concurrency.ticketdemo;

import java.time.Instant;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Service responsible for booking/releasing/cancelling tickets.
 * Keeps concurrency management and business rules outside the Ticket POJO.
 */
public class TicketService {

    private final ReentrantLock lock = new ReentrantLock();

    /**
     * Attempts to book the ticket for the given owner. Thread-safe.
     * Returns true when booking succeeds, false otherwise (already booked/cancelled).
     */
    public boolean book(Ticket ticket, String owner) {
        Objects.requireNonNull(ticket, "ticket must not be null");
        Objects.requireNonNull(owner, "owner must not be null");
        // Use per-ticket lock for fine-grained concurrency. Service-level lock remains available for study.
        java.util.concurrent.locks.ReentrantLock ticketLock = ticket.getLock();
        ticketLock.lock();
        try {
            if (ticket.getStatus() != Ticket.Status.AVAILABLE) return false;
            ticket.setOwner(owner);
            ticket.setStatus(Ticket.Status.BOOKED);
            ticket.setBookedAt(Instant.now());
            return true;
        } finally {
            ticketLock.unlock();
        }
    }

    /**
     * Releases a booked ticket, making it AVAILABLE again. Thread-safe.
     */
    public boolean release(Ticket ticket) {
        Objects.requireNonNull(ticket, "ticket must not be null");
        java.util.concurrent.locks.ReentrantLock ticketLock = ticket.getLock();
        ticketLock.lock();
        try {
            if (ticket.getStatus() != Ticket.Status.BOOKED) return false;
            ticket.setOwner(null);
            ticket.setStatus(Ticket.Status.AVAILABLE);
            ticket.setBookedAt(null);
            return true;
        } finally {
            ticketLock.unlock();
        }
    }

    /**
     * Cancels a booked ticket. Only the current owner may cancel. Thread-safe.
     */
    public boolean cancel(Ticket ticket, String owner) {
        Objects.requireNonNull(ticket, "ticket must not be null");
        Objects.requireNonNull(owner, "owner must not be null");
        java.util.concurrent.locks.ReentrantLock ticketLock = ticket.getLock();
        ticketLock.lock();
        try {
            if (ticket.getStatus() != Ticket.Status.BOOKED) return false;
            if (!Objects.equals(ticket.getOwner(), owner)) return false;
            ticket.setOwner(null);
            ticket.setStatus(Ticket.Status.CANCELLED);
            ticket.setBookedAt(null);
            return true;
        } finally {
            ticketLock.unlock();
        }
    }
}