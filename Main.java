package work.employees;

import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Matcher;

public class Main {


    public static void main(String[] args) {
        String people = """
            Flinstone, Fred, 1/1/1900, Programmer, {locpd=2000,yoe=10,iq=140}
            Flinstone, Fred, 1/1/1900, Programmer, {locpd=2000,yoe=10,iq=140}
            Flinstone, Fred, 1/1/1900, Programmer, {locpd=2000,yoe=10,iq=140}
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


        Matcher peopleMat = Employee.peoplePat.matcher(people);
        int totalSalaries = 0;
        IEmployee employee = null;
//        Flyer flyer = new Ceo("");
//        flyer.fly();
////////////////////////////////////////////////////////////////////////////////
        //CREATING A LIST (USING LIST) TO HAVE FLEXIBILITY BETWEEN ARRAYLIST OR LINKEDLIST AT DECLARATION
        Set<IEmployee> employees = new HashSet<>();
        while(peopleMat.find()) {
            employee = Employee.createEmployee(peopleMat.group());
            employees.add(employee);

        }
//        IEmployee iEmployee = employees.get(5);
//        System.out.println(employees.contains(iEmployee));
//        IEmployee employee1 = Employee.createEmployee("Flinstone5, Fred, 1/1/1900, Programmer, {locpd=5,yoe=10,iq=100}");
//        System.out.println(employees.contains(employee1));
//        System.out.println(iEmployee.equals(employee1));
//////////////////////////////////////////////////////////////////////////////////
//        //GETTING AND REMOVING OBJECTS FROM COLLECTION STRAIGHTFORWARDLY
//        IEmployee first = employees.get(0);
////        IEmployee first = employees.remove(0);
////        employees.add(0, new Programmer(""));
//        Collections.sort(employees, (o1, o2) -> {
//            if(o1 instanceof Employee emp1 && o2 instanceof Employee emp2 ) {
//                int lNameResult = emp1.lastName.compareTo(emp2.lastName);
//                return lNameResult != 0 ? lNameResult : Integer.compare(emp1.getSalary(), emp2.getSalary());
//            }
//            return 0;
//        });
//
////        employees.sort((o1, o2) -> {
////            if(o1 instanceof Employee emp1 && o2 instanceof Employee emp2 ) {
////                int lNameResult = emp1.lastName.compareTo(emp2.lastName);
////                return lNameResult != 0 ? lNameResult : Integer.compare(emp1.getSalary(), emp2.getSalary());
////            }
////            return 0;
////        });
//////////////////////////////////////////////////////////////////////////////////
//        //MORE CONCISE VERSION LESS LINES OF CODE (REVIEW COMMENTED CODE BELOW )
//        //BUT CANT ADD OR REMOVE FROM LIST UNMUTAB;E
        List<String> removeNames = new ArrayList<>(List.of("Wilma5", "Barney5", "Fred5"));
//        removeNames.sort(Comparator.naturalOrder());
//        System.out.println(removeNames);
////        removeNames.add("Wilma5");
////        removeNames.add("Barney4");
////        removeNames.add("Fred2");
//        List<String> newStrings = new ArrayList<>();
//        newStrings.addAll(removeNames);
//        System.out.println(newStrings.size());
////////////////////////////////////////////////////////////////////////////////
        //HOW TO LOOP AND ITERATE AND REMOVE FROM ARRAYLIST OR LINKED LIST
        //OLDER METHOD TO ITERATE AND REMOVE AT THE SAME TIME
        for (Iterator<IEmployee> it = employees.iterator(); it.hasNext();) {
            IEmployee worker = it.next();
            if(worker instanceof Employee worker1) {
                if(removeNames.contains(worker1.firstName)){
                    it.remove();
                }
            }
        }
////////////////////////////////////////////////////////////////////////////////
        for(IEmployee worker : employees) {
            System.out.println(worker.toString());
            totalSalaries += worker.getSalary();
        }

        NumberFormat moneyFormatter =  NumberFormat.getCurrencyInstance();
        System.out.printf("The total payout should be %s%n", moneyFormatter.format(totalSalaries));
//        WeirdoAlt w1  = new WeirdoAlt("mikey","jones", LocalDate.of(2000, 10, 10));
//        System.out.println(w1.firstName());
        System.out.println(employees.size());
    }

    public String getSalary(String firstName) {
        return "14000";
    }
}
