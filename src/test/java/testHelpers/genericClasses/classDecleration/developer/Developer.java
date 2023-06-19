package testHelpers.genericClasses.classDecleration.developer;

import lombok.Data;

import java.util.List;

/**
 * Completely nullable class to cover null scenarios
 */
@Data
public class Developer {
    private String name;
    private Integer age;
    private Double salary;
    private boolean isFullTime;
    private Address address;
    private String[] programmingLanguages;
    private List<Project> projects;
    private Role role;

    public Developer(
            String name,
            Integer age,
            Double salary,
            boolean isFullTime,
            Address address,
            String[] programmingLanguages,
            List<Project> projects,
            Role role
    ) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.isFullTime = isFullTime;
        this.address = address;
        this.programmingLanguages = programmingLanguages;
        this.projects = projects;
        this.role = role;
    }

    public Developer() {
        // Second constructor that sets everything to null
    }
}


