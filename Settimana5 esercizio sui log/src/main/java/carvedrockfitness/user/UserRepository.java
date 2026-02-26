package carvedrockfitness.user;

import carvedrockfitness.order.Order;
import carvedrockfitness.order.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserRepository {
    private static List<User> userList = getDummyDataList();

    public List<User> getUserList() {
        return userList;
    }

    public List<User> findAll() {
        return userList;
    }

    public List<User> findByUserStatus(UserStatus userStatus) {
        List<User> users = new ArrayList<>();
        for (User u : userList) {
            if (u.getUserStatus().equals(userStatus)) {
                users.add(u);
            }
        }
        return users;
    }

    public boolean save(User user) {
        user.setId(userList.get(userList.size() - 1).getId() + 1);
        return userList.add(user);
    }

    public boolean remove(User user) {
        if(userList.contains(user)){
            user.setUserStatus(UserStatus.DELETED);
            return true;
        } else {
            return false;
        }
    }

    public static List<User> getDummyDataList() {
        User user1 = new User(1, "maaike", "maaike@dummyemail.com", LocalDateTime.now(), UserStatus.ACTIVE);
        User user2 = new User(2, "lina", "lina@dummyemail.com", LocalDateTime.now(), UserStatus.ACTIVE);
        User user3 = new User(3, "john", "john@dummyemail.com", LocalDateTime.now(), UserStatus.ACTIVE);

        User[] users = {user1, user2, user3};
        return new ArrayList<>(Arrays.asList(users));
    }
}
