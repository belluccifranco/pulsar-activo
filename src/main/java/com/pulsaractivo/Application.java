package com.pulsaractivo;

import com.pulsaractivo.controller.JwtFilter;
import com.pulsaractivo.model.*;
import com.pulsaractivo.repository.DeviceRepository;
import com.pulsaractivo.repository.DispatcherRepository;
import com.pulsaractivo.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
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

    /*@Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.addUrlPatterns("/api/*");
        return registrationBean;
    }*/

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        // save a couple of persons
        addSomeDispatchers();

        // save a couple of devices
        addSomeDevices();

        //save a new userAccount and a new Role
        addSomeUserAccounts();

        addSomeEventWithDevice();
    }

    private void addSomeDevices() {
        Device device1 = new Device("490154203237518", "Device1", DeviceType.PANICBUTTON);
        device1.addEvent(new Event(device1, LocalDate.now(), EventType.POSITIONUPDATE, -27.471847, -58.840307));

        Device device2 = new Device("375235354514525", "Device2", DeviceType.AVL);
        device2.addEvent(new Event(device2, LocalDate.now(), EventType.POSITIONUPDATE, -27.463613, -58.830598));

        Device device3 = new Device("943202934893509", "Device3", DeviceType.PANICBUTTON);
        device3.addEvent(new Event(device3, LocalDate.now(), EventType.POSITIONUPDATE, -27.476826, -58.855360));

        deviceRepository.save(device1);
        deviceRepository.save(device2);
        deviceRepository.save(device3);

        System.out.println();
        System.out.println("Devices found with findAll():");
        System.out.println("-------------------------------");
        for (Device device : deviceRepository.findAll()) {
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
        for (Dispatcher dispatcher : dispatcherRepository.findAll()) {
            System.out.println(dispatcher);
        }
        System.out.println();
    }

    private void addSomeUserAccounts() {
        UserAccount userAccount = new UserAccount("admin@admin.com", "admin");
        userAccountRepository.save(userAccount);
        System.out.println(userAccount);
    }

    private void addSomeEventWithDevice() {

    }
}
