package work.employees;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Employee implements IEmployee {
    private final static String peopleRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{2,4}),\\s(?<position>\\w+)(?:,\\s*\\{(?<details>.*)\\})?";
    public final static Pattern peoplePat = Pattern.compile(peopleRegex);
    protected final NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance();
    protected final Matcher peopleMat;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    protected String lastName;
    protected String firstName;
    protected LocalDate dob;
    protected String position;
    DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");

    protected Employee() {
        peopleMat = null;
        lastName = "N/A";
        firstName = "N/A";
        dob = null;
    }

    //CONSTRUCTOR FOR EMPLOYEE UTILIZING PATTERN AND MATCHER TO SET FIELDS
    protected Employee(String personText) {
        peopleMat = Employee.peoplePat.matcher(personText);
        if (peopleMat.find()) {
            this.lastName = peopleMat.group("lastName");
            this.firstName = peopleMat.group("firstName");
            this.dob = LocalDate.from(dtFormatter.parse(peopleMat.group("dob")));
            this.position = peopleMat.group("position");
        }
    }

    //CREATED FACTORY METHOD
    public static final IEmployee createEmployee(String employeeText) {
        Matcher peopleMat = Employee.peoplePat.matcher(employeeText);
        //MORE CONCISE SWITCH STATEMENT
        if(peopleMat.find()) {
            return switch (peopleMat.group("position")) {
                case "Programmer" -> new Programmer(employeeText);
                case "Manager" -> new Manager(employeeText);
                case "Analyst" -> new Analyst(employeeText);
                case "Ceo" -> new Ceo(employeeText);
                default -> new DummyEmployee();
            };
        } else {
            return new DummyEmployee();
        }
    }
    //CREATING AN ABSTRACT METHOD DOESNT NEED CODE BLOCK TELLS SUBCLASSES THEY NEED T ODEFINE METHOD
    public abstract int getSalary();

    public double getBonus() {
        return getSalary() * 1.10;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //OVERRIDE ANNNOTATION TO OVERRIDE TO STRING METHOD
    @Override
    public String toString() {
        return String.format("%s, %s: %s - %s", lastName, firstName, moneyFormatter.format(getSalary()), moneyFormatter.format(getBonus()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return lastName.equals(employee.lastName)
                && firstName.equals(employee.firstName)
                && dob.equals(employee.dob);
//adding it to equals method because it was added to hashcode method
        //        && getClass().equals(o.getClass());
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, dob);
        //if added to hascode parameters must add to equals method check
        //getClass());
    }

    private static final class DummyEmployee extends Employee{
        @Override
        public int getSalary() {
            return 0;
        }
    }

    @Override
    public int compareTo(IEmployee o ) {
        Employee other = (Employee) o;
        return this.lastName.compareTo(other.lastName);
    }
}
