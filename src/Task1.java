import java.util.*;
import java.io.*;

public class Task1 {
    public static int id = 0;
    public static void main(String[] args) {
        List<User> friendList = new ArrayList<>();
        List<User> answer = new ArrayList<>();
        String OurFavouriteCategory = "IT";
        for (User friend : friendList
        ) {
            for (Group group : friend.getGroups()
            ) {
                if (group.getCategory().getName().equals(OurFavouriteCategory)) {
                    answer.add(friend);
                    break;
                }
            }
        }
        for (User friend: answer
             ) {
            System.out.println(friend.getName());
        }
    }
}
