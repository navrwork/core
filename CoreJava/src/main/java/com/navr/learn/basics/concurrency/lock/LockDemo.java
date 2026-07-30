package com.navr.learn.basics.concurrency.lock;

import com.navr.learn.basics.concurrency.ticketdemo.Ticket;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

@Slf4j
public class LockDemo {
    private static final ReentrantLock SHARED_LOCK = new ReentrantLock();

    public static void main(String[] args) {
        log.info("=== Reentrant Lock Demo ===");
        
        // Demo 1: Basic lock
        log.info("--- Demo 1: Basic Lock ---");
        reentrantLock(1);
        reentrantLock(2);
        
        // Demo 2: Lock with timeout
        log.info("--- Demo 2: Lock with Timeout ---");
        tryLockWithTimeout(3, 2, TimeUnit.SECONDS);
        tryLockWithTimeout(4, 1, TimeUnit.SECONDS);
        
        // Demo 3: Concurrent threads
        log.info("--- Demo 3: Multiple Threads ---");
        Thread t1 = new Thread(() -> reentrantLockWithWait(5), "Thread-1");
        Thread t2 = new Thread(() -> reentrantLockWithWait(6), "Thread-2");
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            log.error("Thread interrupted", e);
        }
    }

    /**
     * Basic lock acquisition - blocks if lock is held by another thread
     */
    private static void reentrantLock(int ticketId) {
        SHARED_LOCK.lock();
        try {
            log.info("Thread {} acquired lock", Thread.currentThread().getName());
            
            // book the ticket, if available
            if (ticketId > 0) {
                simulateBooking(ticketId);
            }
        } finally {
            SHARED_LOCK.unlock();
            log.info("Thread {} released lock", Thread.currentThread().getName());
        }
    }

    /**
     * Try to acquire lock with timeout - doesn't block indefinitely
     */
    private static void tryLockWithTimeout(int ticketId, long timeout, TimeUnit unit) {
        try {
            log.info("Thread {} attempting to acquire lock (timeout: {} {})", 
                Thread.currentThread().getName(), timeout, unit);
            
            if (SHARED_LOCK.tryLock(timeout, unit)) {
                try {
                    log.info("Thread {} acquired lock successfully", Thread.currentThread().getName());
                    if (ticketId > 0) {
                        simulateBooking(ticketId);
                    }
                } finally {
                    SHARED_LOCK.unlock();
                    log.info("Thread {} released lock", Thread.currentThread().getName());
                }
            } else {
                log.warn("Thread {} failed to acquire lock within {} {}", 
                    Thread.currentThread().getName(), timeout, unit);
            }
        } catch (InterruptedException e) {
            log.error("Thread {} interrupted while waiting for lock", Thread.currentThread().getName(), e);
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Lock with wait - demonstrates waiting for lock to be released
     */
    private static void reentrantLockWithWait(int ticketId) {
        log.info("Thread {} waiting for lock...", Thread.currentThread().getName());
        SHARED_LOCK.lock();
        try {
            log.info("Thread {} acquired lock after waiting", Thread.currentThread().getName());
            
            if (ticketId > 0) {
                simulateBooking(ticketId);
            }
            
            // Simulate some work
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("Thread {} interrupted", Thread.currentThread().getName(), e);
            Thread.currentThread().interrupt();
        } finally {
            SHARED_LOCK.unlock();
            log.info("Thread {} released lock", Thread.currentThread().getName());
        }
    }

    private static void simulateBooking(int ticketId) {
        Ticket ticket = new Ticket();
        ticket.setId(ticketId);
        ticket.setStatus(Ticket.Status.BOOKED);
        ticket.setBookedAt(Instant.now());
        log.info("Ticket booked successfully: {}", ticket);
    }
}
