import persons.Person;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Person person1 = new Person(30, "Alex");
        Person person2 = new Person(15, "Nate");
        Person person3 = new Person(20, "Kate");
        Person person4 = new Person(10, "Bob");
        Person person5 = new Person(16, "Victorya");
        Person person6 = new Person(32, "Sally");

        List<Person> people = new ArrayList<>();

        Collections.addAll(people, person1, person2, person3, person4, person5, person6);

        Stream<Person> stream = people.stream();
        Comparator<Person> order = Comparator.comparing(Person::getAge);
        BiConsumer<Person, Person> minMaxConsumer = (x, y) -> System.out.println(x + " " + y);


        List<Integer> numbers = new ArrayList<>();
        Collections.addAll(numbers, 1,2,3,4,5,6,7,8,9,10);

        System.out.println("TASK1");
        minMax(stream, order, minMaxConsumer);
        System.out.println();
        printEvenNumbers(numbers);
    }

    public static <T> void minMax(
            Stream<? extends T> stream,
            Comparator<? super T> order,
            BiConsumer<? super T, ? super T> minMaxConsumer
            ){
        List<T> items = stream.collect(Collectors.toList());

        minMaxConsumer.accept(
                items.stream().min(order).orElse(null),
                items.stream().max(order).orElse(null));
    }

    public static void printEvenNumbers(List<Integer> numbers){
        Predicate<Integer> predicate = num -> num % 2 == 0;
        System.out.println("Количество четных: " + numbers.stream().filter(predicate).count());
        numbers.stream().filter(predicate).forEach(System.out::println);
    }
}