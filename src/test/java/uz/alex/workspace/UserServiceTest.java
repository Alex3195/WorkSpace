package uz.alex.workspace;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uz.alex.workspace.model.UserModel;
import uz.alex.workspace.service.UserService;

import java.util.List;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    void createUserTest() {
        UserModel user = new UserModel();
        user.setUsername("alexking95@gmail.com");
        user.setPassword("123456");
        user.setFirstName("Alex");
        user.setLastName("King");
        user.setPhone("1234567890");
        assert (userService.createUser(user) != null);
    }

    @Test
    void updateUser() {
        UserModel user = new UserModel();
        user.setId(1L);
        user.setUsername("alexking95@gmail.com");
        user.setPassword("123456");
        user.setFirstName("Alex");
        user.setLastName("King");
        user.setPhone("+998900416564");
        assert (userService.createUser(user) != null);
    }

    @Test
    void getUserById() {
        UserModel user = userService.getUserById(1L);
        assert (user != null);
        assert (user.getId() == 1L);
        assert (user.getUsername().equals("Alexking95"));
    }
    @Test
    void getAllUsers() throws AssertionError {
        List<UserModel> users = userService.getAllUsers();
        assert (users != null);
        if ((users.isEmpty())) {
            throw new AssertionError();
        }

    }
}
