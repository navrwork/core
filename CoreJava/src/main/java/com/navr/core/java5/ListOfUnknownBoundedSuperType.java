package com.navr.core.java5;

import java.util.ArrayList;
import java.util.List;

/**
 * List&lt;? super T&gt; is a lower-bounded bounded wildcard in Java generics that represents a list of some unknown type
 * that is a supertype of T. This is incredibly useful when you want to write data to the list while maintaining type safety.
 * <ul>
 *     <li>
 *         For List&lt;? super T&gt;, only instances of T or its subtypes can be added to the list.
 *         The wildcard ? super T restricts the types the list can accept to T and any superclass of T.
 *         For example, if T is Integer, the list could be of type List&lt;Integer&gt;, List&lt;Number&gt;, or List&lt;Object&gt;.
 *         It cannot be of type List&lt;String&gt; because String is not a superclass of Integer.
 *     </li>
 *     <li>
 *         When you perform a read operation on List&lt;? super T&gt;, the compiler only knows that the list can hold elements
 *         that are of type T or any of its superclasses (e.g., Number or Object for '? super Integer').
 *         Since the compiler cannot determine the exact type stored in the list, it defaults to the lowest common denominator type
 *         that is guaranteed to work: Object.
 *     </li>
 * </ul>
 */
public class ListOfUnknownBoundedSuperType {

    public static void main(String[] args) {
        listOfUnknownBoundedSuperType();

        //
        // addNumbers use cases
        //
        List<Object> objList = new ArrayList<>();
        addNumbers(objList); // Ok. argument is super type of T.
        List<Number> numList = new ArrayList<>();
        addNumbers(numList); // Ok. argument is super type of T.
        List<Integer> intList = new ArrayList<>();
        addNumbers(intList); // Ok. argument of type T.
        List<Double> doubleList = new ArrayList<>();
//        addNumbers(doubleList); // NOT Ok. argument is neither of type T nor super type of T.

        System.out.printf("After addNumbers(): numList=%s%n", numList);
    }

    private static void listOfUnknownBoundedSuperType() {
        List<? super Integer> superIntList = new ArrayList<>();
        superIntList.add(100); // Ok. Integer element allowed.
        superIntList.add(200); // Ok. Integer element allowed.
        superIntList.add(null); // Ok. null allowed.
//        superIntList.add(200.0); // NOT Ok. 'double' not allowed.
//        superIntList.add(new Object()); // Not Ok. Compilation error.

        // Read operation needs an explicit casting
        Object obj = superIntList.get(0); // Returns Object
        Integer num = (Integer) obj; // Explicit cast required
        System.out.printf("listOfUnknownBoundedSuperType: num=%d%n", num);

        List<Object> objectList = new ArrayList<>();
        superIntList = objectList; // Ok.
        List<Number> numberList = new ArrayList<>();
        superIntList = numberList; // Ok
        List<Double> doubleList = new ArrayList<>();
//        superIntList = doubleList; // Not Ok. Incompatible types. Found: 'java.util.List<java.lang.Double>', required: 'java.util.List<? super java.lang.Integer>'


        // you can add anything because all classes are subtypes of Object.
        List<? super Object> objList = new ArrayList<Object>();
        objList.add(new Object()); // Ok
        objList.add(123); // Ok
        objList.add("hello"); // Ok

        // Read operation returns Object. Needs an explicit casting.
        Object e0 = objList.get(0); // Ok. Returns Object type.
        Integer e1 = (Integer) objList.get(1); // Ok. Returns Object type. Can be cast to Integer.
        String e2 = (String) objList.get(2); // Ok. Returns Object type. Can be cast to String.
        System.out.printf("listOfUnknownBoundedSuperType: e0=%s, e1=%d, e2=%s%n", e0, e1, e2);

    }

    /**
     * In Java, &lt;? super T&gt; is a lower-bounded wildcard used in generics. It represents an unknown type that is either T or one of its superclasses.
     * <br/><br/>
     * <b>
     * When you use List&lt;? super Integer&gt;, the method signature declares: "accept a list whose type is Integer or any supertype of Integer."
     * </b>
     * <br/><br/>
     * <p>
     * The compiler determines compatibility by checking if the actual list type fits this constraint.
     * For instance, &lt;? super T&gt; represents an unknown type that is either Integer or one of its superclasses such as Number or Object.
     * <br/><br/>
     * Key Characteristics:
     * <ul>
     *    <li>Definition: It matches Integer and any of its parent types: Integer, Number, Serializable, and Object.</li>
     *
     *    <li>PECS Principle: This follows the "Consumer Super" rule. It is used when you want a collection to consume (receive) data.</li>
     *
     *    <li>
     *         Writing (Adding): <br/> You can safely add an Integer (or its subtypes) to a collection defined with this wildcard. This is because regardless of whether the actual list is a List&lt;Number&gt; or List&lt;Object&gt;, an Integer is a valid element for all of them.
     *         With &lt;? super T&gt;, you can only add elements of type T or its subtypes. You cannot add supertypes of T, even if they seem logically compatible, because the compiler must protect against runtime type errors.
     *    </li>
     *
     *    <li>Reading (Getting): When you read from such a collection, you are only guaranteed to get an Object. The compiler cannot be sure if the list contains specifically Integer or Number objects, so it defaults to the highest common ancestor.</li>
     * </ul>
     * <br/>
     **/
    private static void addNumbers(List<? super Integer> list) {
        System.out.printf("addNumbers: list class = %s %n", list.getClass().getName());
        list.add(10); // Legal: Integer can be added to any supertype of Integer

        //
        // Not Ok
        // This fails because Object is not guaranteed to be compatible with the actual list's type.
        // Even though Object is technically a supertype of Integer, the list might actually be typed as ArrayList<Integer>,
        // in which case adding an Object would violate type safety. The compiler must be conservative and reject this.
        //
//        list.add(new Object()); // Not Ok.

//        Integer i = list.get(0); // Illegal: Could be a List<Object>, only Object is guaranteed
        Integer intObj = (Integer) list.get(0); // Ok. Explicit cast is ok.
        Object obj = list.get(0); // Legal: All elements are at least Objects
        System.out.printf("addNumbers: intObj=%d, obj=%d%n", intObj, obj);
    }

}
