package work.employees;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Programmer extends Employee {

    private int linesOfCode = 0;
    private int yearsOfExp = 0;
    private int iq = 0;

    private final String progRegex = "\\w+\\=(?<locpd>\\w+)\\,\\w+\\=(?<yoe>\\w+)\\,\\w+\\=(?<iq>\\w+)";
    private final Pattern coderPat = Pattern.compile(progRegex);

    public Programmer(String personText) {
//        Matcher peopleMat = peoplePat.matcher(personText);
//    if(peopleMat.find()){
//        this.lastName = peopleMat.group("lastName");
//        this.firstName = peopleMat.group("firstName");
//        this.dob = LocalDate.from(dtFormatter.parse(peopleMat.group("dob")));
//        this.position = peopleMat.group("position");
        super(personText);
        Matcher coderMat = coderPat.matcher(peopleMat.group("details"));
        if(coderMat.find()) {
            this.linesOfCode = Integer.parseInt(coderMat.group("locpd"));
            this.yearsOfExp = Integer.parseInt(coderMat.group("yoe"));
            this.iq = Integer.parseInt(coderMat.group("iq"));
        }
    }



    public int getSalary() {
        return 3000 + this.linesOfCode * this.yearsOfExp * this.iq;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


//    public String getPeopleRegex() {
//        return peopleRegex;
//    }
//
//    public void setPeopleRegex(String peopleRegex) {
//        this.peopleRegex = peopleRegex;
//    }
//
//    public Pattern getPeoplePat() {
//        return peoplePat;
//    }
//
//    public void setPeoplePat(Pattern peoplePat) {
//        this.peoplePat = peoplePat;
//    }

}
