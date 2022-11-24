package work.employees;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manager extends Employee {
    private int orgSize = 0;
    private int dr = 0;

    private final String managRegex = "\\w+\\=(?<orgSize>\\w+)\\,\\w+\\=(?<dr>\\w+)";
    private final Pattern managerPat = Pattern.compile(managRegex);



    public Manager(String personText) {
        super(personText);
            Matcher managMat = managerPat.matcher(peopleMat.group("details"));
            if(managMat.find()) {
            this.orgSize = Integer.parseInt(managMat.group("orgSize"));
            this.dr = Integer.parseInt(managMat.group("dr"));
        }

    }



    public int getSalary() {
        return 3500 + orgSize + dr;
    }

}
