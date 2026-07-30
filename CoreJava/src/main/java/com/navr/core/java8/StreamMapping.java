package com.navr.core.java8;

import com.navr.core.java8.common.Person;
import com.navr.core.java8.common.PersonHelper;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamMapping {
    public static void main(String[] args) {
        collectorsMapping();
    }

    /**
     * mapping():
     * Adapts a Collector accepting elements of type U to one accepting elements of
     * type T by applying a mapping function to each input element before accumulation.
     *
     * <pre>
     * When mapping collector is used to collect elements of a Stream&lt;T>, it first
     * maps/transforms/converts the stream elements of type T to type U using Function&lt;T,U>,
     * and then collects them using Collector&lt;U,A,R>, in effect allowing your collector
     * which accepts Stream&lt;U> to now work with (or adapt to) Stream&lt;T>.
     * </pre>
     *
     */
    private static void collectorsMapping() {

        List<Person> personList = PersonHelper.getPersonList();

        //
        // Note: Both citySet1 and citySet2, in below code snippet, represent same data
        //

        Set<String> citySet1 = personList.stream()
                .collect(Collectors.mapping(Person::getCity, Collectors.toSet()));

        Set<String> citySet2 = personList.stream().map(Person::getCity).collect(Collectors.toSet());

        System.out.printf(" citySet1=%s%n citySet2=%s%n", citySet1, citySet2);

        // find the max age among all persons
        List<String> firstNameList = personList.stream().map(Person::getFirstName).collect(Collectors.toList());
        System.out.printf("firstNameList=%s%n", firstNameList);

        // groupingBy and mapping
        Map<String, Set<String>> lastNamesByCity = personList.stream()
                .collect(Collectors.groupingBy(
                        Person::getCity, Collectors.mapping(Person::getLastName, Collectors.toSet())
                ));
        System.out.printf("lastNamesByCity=%s%n", lastNamesByCity);

    }
}
