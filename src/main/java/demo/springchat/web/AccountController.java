/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.springchat.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.springchat.entity.Account;
import demo.springchat.repo.AccountRepo;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author nathaniel.a.camomot
 */
@Controller
@Transactional
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountRepo accountRepo;

    @RequestMapping(value = "find_user", method = RequestMethod.GET)
    @ResponseBody
    public Page<Account> me(@NotEmpty String term) {
        return accountRepo.findByUsernameContains(term, new PageRequest(0, 8));
    }
}
