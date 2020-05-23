import java.util.*;

public class User {
    private List<Group> groups;


    private String name;
    private int id;
    User(String name ){
        this.name = name;
        Task1.id++;
        id = Task1.id;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public String getName() {
        return name;
    }
}
