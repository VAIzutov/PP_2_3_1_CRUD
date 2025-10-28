package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String getUsers(ModelMap model) {
        List<User> users = userService.listUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/addUser")
    public String showAddForm() {
        return "addUser";
    }

    @PostMapping("/users")
    public String addUser(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        User user = new User();
        user.setName(firstName);
        user.setSurname(lastName);
        userService.add(user);
        return "redirect:/users";
    }

//    @GetMapping("/editUser")
//    public String showEditForm(int id, ModelMap model) {
//        User user = userService.getUserById(id);
//        model.addAttribute("user", user);
//        return "editUser";
//    }
//
//    @PostMapping("/users")
//    public String updateUser( User user) {
//        userService.update(user.getId(), user);
//        return "redirect:/users";
//    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") int userId) {
        userService.delete(userId);
        return "redirect:/users"; // Перенаправление на страницу с списком пользователей
    }

}