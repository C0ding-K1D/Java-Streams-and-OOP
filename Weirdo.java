package work.employees;

import java.time.LocalDate;
import java.util.Objects;

public class Weirdo {
    private String lastName;
    private String firstName;
    private LocalDate dob;

    public Weirdo() {
    }

    public Weirdo(String lastName, String firstName, LocalDate dob) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.dob = dob;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weirdo weirdo = (Weirdo) o;
        return Objects.equals(lastName, weirdo.lastName) && Objects.equals(firstName, weirdo.firstName) && Objects.equals(dob, weirdo.dob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, dob);
    }
}
