package org.example;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StringExample {
    public static void main(String[] args)
    {
        Person p1 = new Person("Ryan", 23, 67);
        Person p2 = new Person("Kiril", 21, 69);
        Person p3 = new Person("Octavia", 22, 72);
        Person p4 = new Person("Reid", 22, 70);


        Scanner keys = new Scanner(System.in);
        System.out.println("Enter a name:");
        String input = keys.nextLine();

        List<Person> People = new ArrayList<Person>();
        People.add(p1);
        People.add(p2);
        People.add(p3);
        People.add(p4);

        List<Person> strm = People.stream()
                .filter(person -> person.getName().contains(input))
                .collect(Collectors.toList());
        System.out.println(strm);
    }
}
