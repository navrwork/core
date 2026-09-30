package com.navr.core.concepts.fi;

/**
 * Demonstrates the difference between passing a normal object and passing a
 * functional-interface argument.
 * <p>
 * A normal object argument, such as {@link Customer}, represents an entity
 * whose state and class-defined behavior already exist before the method call.
 * The receiving method decides which known methods or data from that object it
 * will use. In this example, {@link #printCustomer(Customer)} receives a
 * {@code Customer} and uses its {@code name} accessor; the caller does not
 * replace what {@code printCustomer} does.
 * <p>
 * A functional-interface argument represents a unit of behavior. The receiving
 * method defines when that behavior is invoked, while the caller defines what
 * happens when it is invoked. In this example, {@link #processCustomer(Customer,
 * CustomerAction)} always performs the common processing step and then calls
 * {@link CustomerAction#execute(Customer)}. Each lambda passed by the caller
 * supplies a different action, without changing {@code processCustomer}.
 */
public class ObjectAndFunctionalInterfaceDemo {

    public static void main(String[] args) {
        Customer customer = new Customer("Alice");

        // The method receives a concrete object containing data and behavior.
        printCustomer(customer);

        // The method receives behavior selected by the caller.
        processCustomer(customer, c -> System.out.println("Sending an email to " + c.name()));
        processCustomer(customer, c -> System.out.println("Creating an account for " + c.name()));
    }

    /**
     * Uses the object supplied by the caller as data.
     * <p>
     * The behavior of this method is fixed in the method body: it prints the
     * customer's name. Passing a different {@code Customer} changes the data,
     * but does not change the operation performed.
     *
     * @param customer object containing the customer data to print
     */
    private static void printCustomer(Customer customer) {
        System.out.println("Customer object: " + customer.name());
    }

    /**
     * Runs a common workflow and delegates the variable part to the caller.
     * <p>
     * The method owns the common "processing customer" step. The
     * {@code CustomerAction} argument owns the specific behavior that follows,
     * and that behavior is selected at the call site through a lambda.
     *
     * @param customer object supplied as data to the operation
     * @param action caller-supplied behavior to execute for the customer
     */
    private static void processCustomer(Customer customer, CustomerAction action) {
        System.out.println("Processing customer: " + customer.name());
        action.execute(customer);
    }

    /**
     * Represents one piece of replaceable behavior for a customer.
     * <p>
     * The interface itself specifies only the contract—an action can be
     * executed for a customer. The concrete behavior is supplied by each
     * lambda passed to {@link #processCustomer(Customer, CustomerAction)}.
     */
    @FunctionalInterface
    private interface CustomerAction {
        void execute(Customer customer);
    }

    private record Customer(String name) {
    }
}
