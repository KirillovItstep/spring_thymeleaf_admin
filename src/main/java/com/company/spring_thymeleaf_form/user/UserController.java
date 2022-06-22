package com.company.spring_thymeleaf_form.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
    public class UserController {

        @Autowired
        private UserService osService;

        @GetMapping(value ="/add_os")
        public String indexOs(Model model) {
            model.addAttribute("os",new User()); //Если не добавить, то не будет выполняться парсинг шаблона исходной страницы
            return "add_os";
        }

        @GetMapping(value ="/list_os" )
        public String listOs(Model model) {
            model.addAttribute("os", osService.findAll());
            return "list_os";
        }

        @PostMapping(value="/add_os")
        public String saveOs(User os, Model model, HttpServletResponse response) {
            //Передать id в заголовке ответа
            User newOs = osService.save(os);
            //long id = newOs.getId();
            //response.addHeader("id", String.valueOf(id));
            //model.addAttribute("os", osService.findAll());
            return "redirect:/list_os";
        }

    @DeleteMapping(value = "/delete_os")
        public String deleteOs(@RequestParam(name="id")Long id) { osService.deleteById(id);
        return "redirect:/list_os";
        }

    @GetMapping(value ="/edit_os")
    public String editOs(Model model, @RequestParam(name="id")Long id) {
            User os = osService.findById(id);
        model.addAttribute("os",os);
        return "edit_os";
    }

    @PutMapping(value="/update_os")
    public String updateOs(User os, Model model) {
         //   User osDb = osService.findById(os.getId());
       // osDb.setName(os.getName());
        //osDb.setDeveloper(os.getDeveloper());
        //osService.deleteById(os.getId());
        //osService.save(osDb);
        model.addAttribute("os", osService.findAll());
        return "list_os";
    }
    }

