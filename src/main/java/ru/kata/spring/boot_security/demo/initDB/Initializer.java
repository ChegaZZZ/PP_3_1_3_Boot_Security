package ru.kata.spring.boot_security.demo.initDB;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Initializer {
    private final UserService userService;
    private final RoleService roleService;

    public Initializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void initDB() {
        Role role1 = new Role("ROLE_ADMIN");
        Role role2 = new Role("ROLE_USER");

        roleService.saveRole(role1);
        roleService.saveRole(role2);

        User user1 = new User("Dmitry", "Chigir", 26, "admin", "admin");
        User user2 = new User("Maria", "Chigir", 28, "user", "user");

        user1.setRoles(new HashSet<>(Set.of(role1)));
        user2.setRoles(new HashSet<>(Set.of(role2)));

        userService.saveUser(user1);
        userService.saveUser(user2);
    }
}