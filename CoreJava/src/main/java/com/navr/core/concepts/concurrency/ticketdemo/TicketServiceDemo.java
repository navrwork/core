package com.navr.core.concepts.concurrency.ticketdemo;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Simple runnable demo to exercise TicketService methods.
 * Shows single-threaded and multi-threaded booking behavior
 * to validate per-ticket ReentrantLock semantics.
 */
public class TicketServiceDemo {

    public static void main(String[] args) throws Exception {
        TicketService service = new TicketService();

        // Prepare a ticket
        Ticket ticket = new Ticket();
        ticket.setId(1);
        ticket.setDescription("Concert - VIP");
        ticket.setPrice(new BigDecimal("150.00"));

        System.out.println("=== Single-threaded tests ===");
        System.out.println("Initial status: " + ticket.getStatus());

        boolean bookedByAlice = service.book(ticket, "alice");
        System.out.println("Booked by alice? " + bookedByAlice + " | owner=" + ticket.getOwner());

        boolean bookedByBob = service.book(ticket, "bob");
        System.out.println("Attempt to book again by bob (should fail): " + bookedByBob + " | owner=" + ticket.getOwner());

        // Try cancel by wrong owner
        boolean cancelledByBob = service.cancel(ticket, "bob");
        System.out.println("Cancel by bob (not owner) - expected false: " + cancelledByBob + " | status=" + ticket.getStatus());

        // Correct cancel
        boolean cancelledByAlice = service.cancel(ticket, "alice");
        System.out.println("Cancel by alice (owner) - expected true: " + cancelledByAlice + " | status=" + ticket.getStatus());

        // Release when available should fail
        boolean releasedWhenAvailable = service.release(ticket);
        System.out.println("Release when available (should be false): " + releasedWhenAvailable + " | status=" + ticket.getStatus());

        System.out.println("\n=== Concurrent booking test ===");

        // Ensure ticket is available
        ticket.setStatus(Ticket.Status.AVAILABLE);
        ticket.setOwner(null);
        ticket.setBookedAt(null);

        ExecutorService ex = Executors.newFixedThreadPool(2);

        Callable<Boolean> user1 = () -> {
            boolean r = service.book(ticket, "thread-user-1");
            System.out.println(Thread.currentThread().getName() + " booked=" + r + " | owner=" + ticket.getOwner());
            return r;
        };

        Callable<Boolean> user2 = () -> {
            // small delay to increase contention
            try { TimeUnit.MILLISECONDS.sleep(10); } catch (InterruptedException ignored) { Thread.currentThread().interrupt(); }
            boolean r = service.book(ticket, "thread-user-2");
            System.out.println(Thread.currentThread().getName() + " booked=" + r + " | owner=" + ticket.getOwner());
            return r;
        };

        List<Future<Boolean>> results = ex.invokeAll(Arrays.asList(user1, user2));
        ex.shutdown();
        ex.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("Concurrent booking results:");
        for (Future<Boolean> f : results) {
            System.out.println(" - result: " + f.get());
        }
        System.out.println("Final owner after concurrent attempts: " + ticket.getOwner() + " | status=" + ticket.getStatus());

        // Cleanup: make ticket available and show a final booking
        ticket.setOwner(null);
        ticket.setStatus(Ticket.Status.AVAILABLE);
        boolean finalBook = service.book(ticket, "final-user");
        System.out.println("Final booking by final-user: " + finalBook + " | owner=" + ticket.getOwner());
    }
}
