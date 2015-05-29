package com.pulsaractivo;

import com.pulsaractivo.model.*;
import com.pulsaractivo.repository.DeviceRepository;
import com.pulsaractivo.repository.DispatcherRepository;
import com.pulsaractivo.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DispatcherRepository dispatcherRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

    @Bean
    public SecurityConfiguration applicationSecurity() {
        return new SecurityConfiguration();
    }

    @Override
    public void run(String... strings) throws Exception {
        // save a couple of persons
        addSomeDispatchers();

        // save a couple of devices
        addSomeDevices();

        //save a new userAccount and a new Role
        addSomeUserAccountsAndRoles();
    }

    private void addSomeDevices() {
        deviceRepository.save(new Device("490154203237518", "Device1", DeviceType.PANICBUTTON));
        deviceRepository.save(new Device("375235354514525", "Device2", DeviceType.AVL));
        deviceRepository.save(new Device("943202934893509", "Device3", DeviceType.PANICBUTTON));

        System.out.println();
        System.out.println("Devices found with findAll():");
        System.out.println("-------------------------------");
        for (Device device: deviceRepository.findAll()) {
            System.out.println(device);
        }
        System.out.println();
    }

    private void addSomeDispatchers() {
        dispatcherRepository.save(new Dispatcher("Franco", "24879316"));
        dispatcherRepository.save(new Dispatcher("Dario", "21365479"));
        dispatcherRepository.save(new Dispatcher("Facundo", "20547891"));

        System.out.println();
        System.out.println("Dispatchers found with findAll():");
        System.out.println("-------------------------------");
        for (Dispatcher dispatcher: dispatcherRepository.findAll()) {
            System.out.println(dispatcher);
        }
        System.out.println();
    }

    private void addSomeUserAccountsAndRoles() {
        UserRole ADMIN_ROLE = new UserRole("ADMIN_ROLE");
        List<UserRole> roles = new ArrayList();
        roles.add(ADMIN_ROLE);
        UserAccount userAccount = new UserAccount("admin@admin.com", "admin", "admin", roles);
        List<UserAccount> accounts = new ArrayList();
        accounts.add(userAccount);
        ADMIN_ROLE.setUserAccounts(accounts);
        userAccountRepository.save(userAccount);

        System.out.println(userAccount);
        System.out.println(ADMIN_ROLE);
    }
}
