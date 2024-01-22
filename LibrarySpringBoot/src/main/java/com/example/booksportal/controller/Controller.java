package com.example.booksportal.controller;

import com.example.booksportal.dao.DaoBook;
import com.example.booksportal.dao.DaoUser;
import com.example.booksportal.model.Book;
import com.example.booksportal.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@org.springframework.stereotype.Controller
@RequestMapping("/")
public class Controller {

    @Autowired
    private DaoUser userRepository;

    @Autowired
    private DaoBook bookRepository;

    @GetMapping("/")
    public String showForm(User user) { return "welcome"; }

    @GetMapping("/login")
    public String showLoginForm(User user) { return "login"; }

    @GetMapping("/register")
    public String showRegisterForm(User user) {
        return "register";
    }

    @GetMapping("/newbook")
    public String showNewBookForm(Book book, Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "newbook";
    }

    @GetMapping("/home")
    public String results(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        model.addAttribute("books", bookRepository.findAll());
        return "home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String checkPersonInfo(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
        User utente = userRepository.login(username, password);

        if(utente == null)
            return "login";
        else {
            session.setAttribute("username", username);
        }
        return "redirect:/home";
    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
    public String logoutDo(HttpSession session)
    {
        session.setAttribute("username", null);
        return "redirect:/login";
    }

    @PostMapping("/register")
    public String registerNewUser(@Valid User user, Model model,  BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        userRepository.save(user);
        return "login";
    }

    @PostMapping("/newbook")
    public String checkBookInfo(@Valid Book book, Model model, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "newbook";
        }
        bookRepository.save(book);
        return "redirect:/home";
    }

}
