/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.springchat.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.springchat.dto.AccountCreateDTO;
import demo.springchat.dto.AjaxResponse;
import demo.springchat.dto.Login;
import demo.springchat.dto.User;
import demo.springchat.entity.Account;
import demo.springchat.repo.AccountRepo;
import demo.springchat.util.ControllerException;
import java.security.Principal;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author nathaniel.a.camomot
 */
@Controller
public class AccountController {

  private static final Logger log = LoggerFactory.getLogger(AccountController.class);

  @Autowired
  private MessageSource messageSource;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private AccountRepo accountRepo;

  @Autowired
  private AuthenticationManager authenticationManager;

  @RequestMapping(value = "register", method = RequestMethod.POST)
  @ResponseBody
  public AjaxResponse registerAccount(@ModelAttribute("account") @Validated AccountCreateDTO accountCreateDTO,
          BindingResult result) {

    if (result.hasErrors()) {
      return new AjaxResponse(result, messageSource);
    }

    Account account = accountRepo.findByUsername(accountCreateDTO.getUsername());

    if (account == null) {
      account = new Account();
      account.setUsername(accountCreateDTO.getUsername());
      account.setPassword(passwordEncoder.encode(accountCreateDTO.getPassword()));
      account.setRoles(new String[]{"ROLE_USER"});
      account = accountRepo.save(account);
    } else {
      throw new ControllerException(accountCreateDTO.getUsername() + " already taken");
    }

    return new AjaxResponse(account.getUsername());
  }

  @RequestMapping(value = "login", method = RequestMethod.POST)
  public User login(@RequestBody @Validated Login login) {

    String username = login.getUsername();
    Authentication auth;
    try {
      UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, login.getPassword());
      auth = authenticationManager.authenticate(token);

    } catch (Exception e) {
      log.error("Login error", e);
      throw new ControllerException("Login failed");
    } finally {
      login.clearPass();
    }

    return new User(username);
  }

  @RequestMapping(value = "me", method = RequestMethod.GET)
  public User me(Principal principal) {
    if (principal == null || StringUtils.isEmpty(principal.getName())) {
      throw new ControllerException("Not logged in");
    }

    return new User(principal.getName());
  }
}
