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
import org.springframework.web.servlet.ModelAndView;

import demo.springchat.dto.AccountCreateDTO;
import demo.springchat.dto.AjaxResponse;
import demo.springchat.entity.Account;
import demo.springchat.repo.AccountRepo;
import demo.springchat.util.ControllerException;

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

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public ModelAndView registerAccountView() {
        return new ModelAndView("account.register", "account", new AccountCreateDTO());
    }
    
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public ModelAndView registerAccountView() {
        return new ModelAndView("account.register", "account", new AccountCreateDTO());
    }

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
}
