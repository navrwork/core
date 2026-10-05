package com.navr.core.concepts.threads.classic;

public class ThreadLocalMain {
    private static final ThreadLocal<RequestContext> CURRENT_CONTEXT = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(ThreadLocalMain::handleRequest, "Thread-1");
        Thread thread2 = new Thread(ThreadLocalMain::handleRequest, "Thread-2");

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }

    /**
     * Equivalent to a run() method in a Runnable implementation.
     * It sets up the request context for the current thread, processes the request,
     * and ensures that the context is removed after processing to prevent memory leaks.
     */
    private static void handleRequest() {
        CURRENT_CONTEXT.set(new RequestContext(Thread.currentThread().getName()));
        try {
            processRequest();
        } finally {
            CURRENT_CONTEXT.remove();
        }
    }

    /**
     * A method call within the same thread that accesses the ThreadLocal variable.
     * It retrieves the current thread's request context, updates its status, and writes a log message.
     * This demonstrates how ThreadLocal allows different threads to maintain their own independent state.
     */
    private static void processRequest() {
        RequestContext context = CURRENT_CONTEXT.get();
        context.setStatus("processing");
        writeLog();
    }

    /**
     * Another method call within the same thread that accesses the ThreadLocal variable.
     */
    private static void writeLog() {
        RequestContext context = CURRENT_CONTEXT.get();
        System.out.println(Thread.currentThread().getName()
                + " sees context: requestId=" + context.getRequestId()
                + ", status=" + context.getStatus());
    }

    private static final class RequestContext {
        private final String requestId;
        private String status = "started";

        private RequestContext(String requestId) {
            this.requestId = requestId;
        }

        private String getRequestId() {
            return requestId;
        }

        private String getStatus() {
            return status;
        }

        private void setStatus(String status) {
            this.status = status;
        }
    }
}
