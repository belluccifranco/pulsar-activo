package com.pulsaractivo;

import com.pulsaractivo.controller.SecurityConfiguration;
import com.pulsaractivo.model.Customer;
import com.pulsaractivo.model.Device;
import com.pulsaractivo.repository.CustomerRepository;
import com.pulsaractivo.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.Filter;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DeviceRepository deviceRepository;

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
        // save a couple of customers
        customerRepository.save(new Customer("Jack", "Bauer"));
        customerRepository.save(new Customer("Chloe", "O'Brian"));
        customerRepository.save(new Customer("Kim", "Bauer"));
        customerRepository.save(new Customer("David", "Palmer"));
        customerRepository.save(new Customer("Michelle", "Dessler"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : customerRepository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

        // fetch an individual customer by ID
        Customer customer = customerRepository.findOne(1L);
        System.out.println("Customer found with findOne(1L):");
        System.out.println("--------------------------------");
        System.out.println(customer);
        System.out.println();

        // fetch customers by last name
        System.out.println("Customer found with findByLastName('Bauer'):");
        System.out.println("--------------------------------------------");
        for (Customer bauer : customerRepository.findByLastName("Bauer")) {
            System.out.println(bauer);
        }

        // save a couple of devices

        deviceRepository.save(new Device("490154203237518", "Device1"));
        deviceRepository.save(new Device("375235354514525", "Device2"));
        deviceRepository.save(new Device("943202934893509", "Device3"));

        // fetch all customers
        System.out.println();
        System.out.println("Devices found with findAll():");
        System.out.println("-------------------------------");
        for (Device device: deviceRepository.findAll()) {
            System.out.println(device);
        }
        System.out.println();
    }
}
