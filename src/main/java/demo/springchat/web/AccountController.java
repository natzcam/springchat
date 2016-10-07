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
import demo.springchat.res.FormResponse;
import demo.springchat.res.User;
import demo.springchat.util.MapperUtils;
import org.dozer.Mapper;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author nathaniel.a.camomot
 */
@Controller
@Transactional
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private Mapper mapper;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private AccountRepo accountRepo;

    @RequestMapping(value = "find_user", method = RequestMethod.GET)
    @ResponseBody
    public Page<User> me(@RequestParam("term") @NotEmpty String term) {

        Pageable pageable = new PageRequest(0, 5);
        Page<Account> page = accountRepo.findByUsernameContains(term, pageable);
        Page<User> userPage = MapperUtils.mapPage(page, pageable,
                mapper,
                User.class);

        return userPage;
    }
}
