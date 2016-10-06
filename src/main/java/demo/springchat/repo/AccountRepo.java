/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.springchat.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import demo.springchat.entity.Account;
import org.springframework.cache.annotation.Cacheable;

/**
 *
 * @author nathaniel.a.camomot
 */
public interface AccountRepo extends PagingAndSortingRepository<Account, Long> {

    public Account findByUsername(String username);

    @Cacheable("accounts")
    public Page<Account> findByUsernameContains(String username, Pageable page);

    public Account findById(Long id);
}
