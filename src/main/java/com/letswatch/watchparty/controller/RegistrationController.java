package com.letswatch.watchparty.controller;


import com.letswatch.watchparty.dto.UserDto;
import com.letswatch.watchparty.models.UserEntity;
import com.letswatch.watchparty.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    private UserServices userServices;

    public RegistrationController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/register")
    public String getRegistrationPage(Model model){
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "registration";

    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") UserDto user,
                           Model model, BindingResult result){
        UserEntity ifEmailUsed = userServices.findByEmail(user.getEmail());
        if(ifEmailUsed != null && ifEmailUsed.getEmail() != null && !ifEmailUsed.getEmail().isEmpty()){
            return "redirect:/register?fail";
        }
        UserEntity ifUserNameUsed = userServices.findByUsername(user.getUsername());
        if(ifUserNameUsed != null && ifUserNameUsed.getEmail() != null && !ifUserNameUsed.getEmail().isEmpty()){
            return "redirect:/register?fail";
        }
        if(result.hasErrors()){
            model.addAttribute("user", user);
            return "registration";
        }
        userServices.saveUser(user);
        return "redirect:/parties?success";

    }

    @GetMapping("/login")
    public String loginUser(){
        return "login";
    }

}
