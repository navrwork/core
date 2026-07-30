package com.navr.core.java8;

import java.util.List;

/**
 *
 * <ul>
 * <li> Local variables referenced from a lambda expression must be final or effectively final. </li>
 * <li>Final Variable: a variable which is declared with the final keyword cannot be modified and
 * will have the same value throughout the application.</li>
 * <li>Effective Final Variable: a variable which is not explicitly declared with a final keyword but
 * never gets modified throughout the application, although it's not declared as final since the value
 * has not been modified hence compiler treats them just like final and can be termed as Effective Final.</li>
 * <li>The restriction to effectively final variables prohibits access to dynamically-changing
 * local variables, whose capture would likely introduce concurrency problems.</li>
 *
 *
 * </ul>
 */
public class LambdaAndEffectivelyFinal {

    public static void main(String[] args) {
        final int finalVar = 10; // final variable
        int effFinalVar = 20; // effectively final variable. value assigned only once.
        int anotherVar = 30; // not effectively final variable as value assigned more than once.
        anotherVar++;

        int counter = 0;
        List<Integer> intList = List.of(1,2,3);
        intList.forEach(x -> System.out.printf(x+" ")); // Ok

        intList.forEach(x -> System.out.printf(counter+" ")); // Ok. Effectively final.

        // NOT Ok. Compilation error.
        // Variable not effectively final as value is assigned more than once.
//        intList.forEach(x -> System.out.printf(counter++));

    }


}
