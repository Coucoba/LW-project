package com.univ.kandan.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import com.univ.kandan.dto.UserDto;
import com.univ.kandan.exceptions.UserAlreadyExistException;
import com.univ.kandan.model.User;
import com.univ.kandan.service.UsersService;

@Controller
@RequestMapping("/users")
public class UsersController {

  private final UsersService usersService;

  @Autowired
  public UsersController(UsersService usersService) {
    this.usersService = usersService;
  }

  @GetMapping("/register")
  public String register(WebRequest request, Model model) {
    UserDto userDto = new UserDto();
    model.addAttribute("userDto", userDto);
    return "register";
  }

  @PostMapping("/save")
  public String register(
      @ModelAttribute("userDto") @Valid UserDto userDto,
      HttpServletRequest request,
      Errors errors) {

    try {
      User registered = usersService.registerUser(userDto);
      System.out.println(registered.getId());
    } catch (UserAlreadyExistException e) {
      return "redirect:/users/register?error=userAlreadyExist";
    }

    return "redirect:/users/register?success";
  }

}