package testHelpers.genericClasses.classObjects.developer;

import testHelpers.genericClasses.classDecleration.developer.Address;
import testHelpers.genericClasses.classDecleration.developer.Developer;
import testHelpers.genericClasses.classDecleration.developer.Project;
import testHelpers.genericClasses.classDecleration.developer.Role;

import java.util.ArrayList;
import java.util.List;

public final class DeveloperObjects {
    private static final List<Developer> RAW_LIST = new ArrayList<>();

    static {
        initRawList();
    }

    public static List<Developer> getRawList() {
        return RAW_LIST;
    }

    private static void initRawList() {
        // randomly "null" every so often
        RAW_LIST.add(new Developer("Alice Smith", null, 6000.0, true, new Address("New York", "USA"), new String[]{"JavaScript"}, createProjectsList1(), Role.SENIOR_DEVELOPER));
        RAW_LIST.add(new Developer("Bob Johnson", null, 5500.0, false, new Address("London", "UK"), new String[]{"Java"}, createProjectsList2(), Role.DEVELOPER));
        RAW_LIST.add(new Developer("Charlie Brown", 28, null, true, new Address("Paris", "France"), new String[]{"Python"}, createProjectsList3(), Role.DEVELOPER));
        RAW_LIST.add(new Developer("David Brech", 35, 7000.0, false, new Address("Berlin", "Germany"), null, null, null));
        RAW_LIST.add(new Developer("Eve Anderson", 32, 3000.0, true, new Address(null, "Nauru"), new String[]{"C++", "C#"}, createProjectsList4(), Role.DEVELOPER));
        RAW_LIST.add(new Developer("Frank Williams", 40, null, true, new Address("Tokyo", "Japan"), new String[]{"Ruby"}, createProjectsList5(), Role.DEVELOPER));
        RAW_LIST.add(new Developer("Grace Davis", 45, 8000.0, false, new Address("Sydney", "Australia"), new String[]{"HTML", "CSS"}, createProjectsList6(), Role.SENIOR_DEVELOPER));
        RAW_LIST.add(new Developer("Hank Wilson", 27, 4500.0, true, new Address("Toronto", "Canada"), null, createProjectsList7(), Role.DEVELOPER));
        RAW_LIST.add(new Developer("Ivy Moore", null, 6500.0, false, new Address("Mumbai", "India"), new String[]{"PHP"}, createProjectsList8(), Role.SENIOR_DEVELOPER));
        RAW_LIST.add(new Developer("Jack Thompson", 33, null, true, new Address("São Paulo", "Brazil"), new String[]{"Swift"}, createProjectsList9(), Role.DEVELOPER));
        RAW_LIST.add(new Developer("Hamza Öner", 25, 1733.33, false, new Address("Mainz", "Germany"), new String[]{"Java"}, createProjectsList10(), Role.DEVELOPER));
        RAW_LIST.add(new Developer()); // complete null object
    }

    private static List<Project> createProjectsList1() {
        List<Project> projects = new ArrayList<>();
        projects.add(new Project("Social Media Analytics", "Develop a system to analyze social media data and extract insights."));
        projects.add(new Project("Smart Home Automation", "Build a home automation system with voice control and sensor integration."));
        return projects;
    }

    private static List<Project> createProjectsList2() {
        List<Project> projects = new ArrayList<>();
        projects.add(new Project("E-Learning Platform", "Create an online platform for interactive learning and course management."));
        projects.add(new Project("Inventory Management Solution", "Build a solution to track and manage inventory for retail businesses."));
        return projects;
    }

    private static List<Project> createProjectsList3() {
        List<Project> projects = new ArrayList<>();
        projects.add(new Project("Healthcare Management System", "Develop a system to manage patient records and medical appointments."));
        projects.add(new Project("Inventory Management Solution", "Build a solution to track and manage inventory for retail businesses."));
        return projects;
    }

    private static List<Project> createProjectsList4() {
        List<Project> projects = new ArrayList<>();
        projects.add(new Project("Ride-Sharing Application", "Create a platform for users to share rides and carpool."));
        projects.add(new Project("Restaurant Ordering System", "Develop an online ordering system for restaurants with menu customization."));
        return projects;
    }

    private static List<Project> createProjectsList5() {
        List<Project> projects = new ArrayList<>();
        projects.add(new Project("Fitness Tracking App", "Build a mobile app to track and analyze fitness activities and health metrics."));
        projects.add(new Project("Event Ticketing Platform", "Create an online platform for buying and selling event tickets."));
        return projects;
    }

    private static List<Project> createProjectsList6() {
        List<Project> projects = new ArrayList<>();
        projects.add(new Project("AI-powered Virtual Assistant", "Develop an intelligent virtual assistant for voice-based interactions."));
        projects.add(new Project("Health and Fitness App", "Build an app for tracking fitness activities and providing workout routines."));
        return projects;
    }

    private static List<Project> createProjectsList7() {
        List<Project> projects = new ArrayList<>();
        projects.add(new Project("E-Commerce Mobile App", "Create a mobile app for online shopping with intuitive user experience."));
        projects.add(new Project("Customer Feedback Analysis", "Develop a system to analyze and extract insights from customer feedback."));
        return projects;
    }

    private static List<Project> createProjectsList8() {
        List<Project> projects = new ArrayList<>();
        projects.add(new Project("Tourism Website", "Develop a website to promote tourism and showcase local attractions."));
        projects.add(new Project("Ticket Reservation System", "Develop a system for reserving tickets for movies, concerts, and events."));
        return projects;
    }

    private static List<Project> createProjectsList9() {
        List<Project> projects = new ArrayList<>();
        projects.add(new Project("Document Management System", "Create a system for managing and organizing digital documents."));
        projects.add(new Project("Weather Forecasting Application", "Build an app for real-time weather forecasts and alerts."));
        return projects;
    }

    private static List<Project> createProjectsList10() {
        List<Project> projects = new ArrayList<>();
        projects.add(new Project("QueryOnGenericList OS", "Create a Java Library for parsing queries and retrieving queried generic lists."));
        return projects;
    }
}

/*
Developer(name=Alice Smith,     age=null,   salary=6000.0,  isFullTime=true,    address=Address(city=New York, country=USA),        programmingLanguages=[JavaScript],  projects=[Project(name=Social Media Analytics, description=Develop a system to analyze social media data and extract insights.), Project(name=Smart Home Automation, description=Build a home automation system with voice control and sensor integration.)],               role=SENIOR_DEVELOPER)
Developer(name=Bob Johnson,     age=null,   salary=5500.0,  isFullTime=false,   address=Address(city=London, country=UK),           programmingLanguages=[Java],        projects=[Project(name=E-Learning Platform, description=Create an online platform for interactive learning and course management.), Project(name=Inventory Management Solution, description=Build a solution to track and manage inventory for retail businesses.)],        role=DEVELOPER)
Developer(name=Charlie Brown,   age=28,     salary=null,    isFullTime=true,    address=Address(city=Paris, country=France),        programmingLanguages=[Python],      projects=[Project(name=Healthcare Management System, description=Develop a system to manage patient records and medical appointments.), Project(name=Inventory Management Solution, description=Build a solution to track and manage inventory for retail businesses.)],    role=DEVELOPER)
Developer(name=David Brech,     age=35,     salary=7000.0,  isFullTime=false,   address=Address(city=Berlin, country=Germany),      programmingLanguages=null,          projects=null,                                                                                                                                                                                                                                                              role=null)
Developer(name=Eve Anderson,    age=32,     salary=5000.0,  isFullTime=true,    address=Address(city=null, country=Nauru),          programmingLanguages=[C++, C#],     projects=[Project(name=Ride-Sharing Application, description=Create a platform for users to share rides and carpool.), Project(name=Restaurant Ordering System, description=Develop an online ordering system for restaurants with menu customization.)],                   role=DEVELOPER)
Developer(name=Frank Williams,  age=40,     salary=null,    isFullTime=true,    address=Address(city=Tokyo, country=Japan),         programmingLanguages=[Ruby],        projects=[Project(name=Fitness Tracking App, description=Build a mobile app to track and analyze fitness activities and health metrics.), Project(name=Event Ticketing Platform, description=Create an online platform for buying and selling event tickets.)],             role=DEVELOPER)
Developer(name=Grace Davis,     age=45,     salary=8000.0,  isFullTime=false,   address=Address(city=Sydney, country=Australia),    programmingLanguages=[HTML, CSS],   projects=[Project(name=AI-powered Virtual Assistant, description=Develop an intelligent virtual assistant for voice-based interactions.), Project(name=Health and Fitness App, description=Build an app for tracking fitness activities and providing workout routines.)],  role=SENIOR_DEVELOPER)
Developer(name=Hank Wilson,     age=27,     salary=4500.0,  isFullTime=true,    address=Address(city=Toronto, country=Canada),      programmingLanguages=null,          projects=[Project(name=E-Commerce Mobile App, description=Create a mobile app for online shopping with intuitive user experience.), Project(name=Customer Feedback Analysis, description=Develop a system to analyze and extract insights from customer feedback.)],        role=DEVELOPER)
Developer(name=Ivy Moore,       age=null,   salary=6500.0,  isFullTime=false,   address=Address(city=Mumbai, country=India),        programmingLanguages=[PHP],         projects=[Project(name=Tourism Website, description=Develop a website to promote tourism and showcase local attractions.), Project(name=Ticket Reservation System, description=Develop a system for reserving tickets for movies, concerts, and events.)],                  role=SENIOR_DEVELOPER)
Developer(name=Jack Thompson,   age=33,     salary=null,    isFullTime=true,    address=Address(city=São Paulo, country=Brazil),    programmingLanguages=[Swift],       projects=[Project(name=Document Management System, description=Create a system for managing and organizing digital documents.), Project(name=Weather Forecasting Application, description=Build an app for real-time weather forecasts and alerts.)],                       role=DEVELOPER)
Developer(name=Hamza Öner,      age=25,     salary=1733.33, isFullTime=false,   address=Address(city=Mainz, country=Germany),       programmingLanguages=[Java],        projects=[Project(name=QueryOnGenericList OS, description=Create a Java Library for parsing queries and retrieving queried generic lists.)],                                                                                                                                role=DEVELOPER)
Developer(name=null,            age=null,   salary=null,    isFullTime=false,   address=null,                                       programmingLanguages=null,          projects=null,                                                                                                                                                                                                                                                              role=null)
 */