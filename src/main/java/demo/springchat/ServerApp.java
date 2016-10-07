package demo.springchat;

import demo.springchat.entity.Account;
import demo.springchat.repo.AccountRepo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by nathaniel.a.camomot
 */
@SpringBootApplication
public class ServerApp {

    public static void main(String[] args) {
        SpringApplication.run(ServerApp.class, args);
    }

    @Value("${app.init.eids}")
    private String[] initEids;

    @Value("${app.init.roles}")
    private String[] initRoles;

    @Value("${app.init.pics}")
    private String[] initPics;

    @Autowired
    Environment env;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner init(final AccountRepo accountRepo) {

        return (String... arg0) -> {
            for (int i = 0; i < initEids.length; i++) {
                Account account = accountRepo.findByUsername(initEids[i]);
                if (account == null) {
                    account = new Account();
                }
                account.setUsername(initEids[i]);
                account.setPassword(passwordEncoder.encode("test"));
                account.setRoles(Arrays.asList(initRoles[i]));
                account.setPic(initPics[i]);

                accountRepo.save(account);
            }
        };
    }

    @Bean
    public Mapper mapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        List<String> config = new ArrayList<>();
        config.add("dozer.xml");
        mapper.setMappingFiles(config);
        return mapper;
    }
}
