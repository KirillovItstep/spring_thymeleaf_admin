package com.company.spring_thymeleaf_form.user;

import com.company.spring_thymeleaf_form.role.Role;
import com.company.spring_thymeleaf_form.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

@Controller
    public class UserController {

        @Autowired
        private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping(value ={"/index","/"})
    public String index() {
        return "index";
    }

    @GetMapping(value ="/users" )
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping(value ="/edit_user")
    public String editUser(Model model, @RequestParam(name="id") Long id) {
        User user = userService.findById(id);
        model.addAttribute("user",user);
        model.addAttribute("roles",roleService.findAll());
        return "edit_user";
    }

    @PutMapping(value="/update_user")
    public String updateUser(User user, Model model, @RequestParam(value="role", required = true) long[] roles) {
        Set<Role> rolesNew = new HashSet<>();
        if (roles!=null){
            for (int i=0; i<roles.length; i++)
                rolesNew.add(roleService.findById(roles[i]));
        }
        User userDb = userService.findById(user.getId());
        userDb.setEnabled(user.isEnabled());
        userDb.setRoles(rolesNew);
        userService.save(userDb);
        model.addAttribute("users", userService.findAll());
        return "redirect:/users";
    }

        @GetMapping(value ="/add_os")
        public String indexOs(Model model) {
            model.addAttribute("os",new User()); //Если не добавить, то не будет выполняться парсинг шаблона исходной страницы
            return "add_os";
        }



        @PostMapping(value="/add_os")
        public String saveOs(User os, Model model, HttpServletResponse response) {
            //Передать id в заголовке ответа
            User newOs = userService.save(os);
            //long id = newOs.getId();
            //response.addHeader("id", String.valueOf(id));
            //model.addAttribute("os", osService.findAll());
            return "redirect:/list_os";
        }

    @DeleteMapping(value = "/delete_os")
        public String deleteOs(@RequestParam(name="id")Long id) { userService.deleteById(id);
        return "redirect:/list_os";
        }




    }

