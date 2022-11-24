package work.employees;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class StreamsStuff {
    public static void main(String[] args) {
        String people = """
            Flinstone, Fred, 1/1/1900, Programmer, {locpd=2000,yoe=10,iq=140}
            Flinstone, Fred, 1/1/1900, Programmer, {locpd=2000,yoe=10,iq=140}
            Flinstone, Fred15, 1/1/1900, Programmerzzz, {locpd=2000,yoe=10,iq=140}
            Flinstone, Fred, 1/1/1900, Programmer, {locpd=2000,yoe=10,iq=140}
            Flinstone, Fred, 1/1/1900, Programmer, {locpd=2000,yoe=10,iq=140}
            Flinstone, Fred, 1/1/1900, Programmer, {locpd=2000,yoe=10,iq=140}
            Flinstone2, Fred2, 1/1/1900, Programmer, {locpd=1300,yoe=14,iq=100}
            Flinstone3, Fred, 1/1/1900, Programmer, {locpd=2300,yoe=8,iq=105}
            Flinstone4, Fred, 1/1/1900, Programmer, {locpd=1630,yoe=3,iq=115}
            Flinstone5, Fred, 1/1/1900, Programmer, {locpd=5,yoe=10,iq=100}
            Rubble, Barney, 2/2/1905, Manager, {orgSize=300,dr=10}
            Rubble2, Barney, 2/2/1905, Manager, {orgSize=100,dr=4}
            Rubble3, Barney, 2/2/1905, Manager, {orgSize=200,dr=2}
            Rubble4, Barney4, 2/2/1905, Manager, {orgSize=500,dr=8}
            Rubble5, Barney, 2/2/1905, Manager, {orgSize=175,dr=20}
            Flinstone, Wilma, 3/3/1910, Analyst, {projectCount=4}
            Flinstone2, Wilma, 3/3/1910, Analyst, {projectCount=4}
            Flinstone3, Wilma, 3/3/1910, Analyst, {projectCount=5}
            Flinstone4, Wilma, 3/3/1910, Analyst, {projectCount=6}
            Flinstone5, Wilma5, 3/3/1910, Analyst, {projectCount=9}
            Rubble, Betty, 4/4/1915, Ceo, {avgStockPrice=300}
            """;

        Predicate<String> dummyEmployeeSelector = s -> s.contains("Programmerzzz");
        long result = people
                .lines()
                .filter(dummyEmployeeSelector.negate())
                .map(Employee::createEmployee)
                .map(i -> (Employee) i)
//                .filter(Predicate.not(e -> e instanceof Programmer))
//                .distinct()
                .collect(Collectors.toSet()).stream()
                .sorted(comparing(Employee::getLastName)
                        .thenComparing(Employee::getFirstName)
                        .thenComparing(Employee::getSalary))
                .skip(5)
                .mapToInt(i -> {
                    System.out.println(i);
                    return i.getSalary();
                })
                .reduce(0, Integer::sum);

        System.out.println(result);
//            .forEach(System.out::println);

//        record car(String make, String model) {
//        }
//
//        Stream.of(new car("ford", "bronco"), new car("bmw", "i8"))
//                .filter(c -> "bmw".equals(c.make))
//            .forEach(System.out::println);

//        IntStream.of(1,2,3,4,5)
//                .reduce((acc, , 0);

//        IntStream.rangeClosed(1,5)
//                .boxed()
//                .map(n -> String.valueOf(n).concat("-"))
//                .forEach(System.out::print);


//        String[] names = {"matt", "joe", "pat"};
//        Arrays.stream(names)
//                .forEach(System.out::println);
//
//        try {
//            Files.lines(Path.of("/Users/matthew/IdeaProjects/Employees/src/main/java/work/employees/employees.txt"))
//                    .forEach(System.out::println);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        List<String> nums = new ArrayList<>(List.of("1", "2","3","4"));
        OptionalInt sum = nums.stream()
                .mapToInt(Integer::valueOf)
                .reduce(Math::min);

        System.out.println(sum.orElse(0));
    }
}
