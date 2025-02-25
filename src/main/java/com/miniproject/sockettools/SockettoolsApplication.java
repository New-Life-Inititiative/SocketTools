package com.miniproject.sockettools;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.miniproject.sockettools.service.PortCheckerService;
import com.miniproject.sockettools.service.SocketServerService;

@SpringBootApplication
public class SockettoolsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SockettoolsApplication.class, args);
	}

    @Bean
    CommandLineRunner startServers(PortCheckerService portCheckerService, SocketServerService socketServerService) {
        return args -> {
            List<Integer> availablePorts = portCheckerService.getAvailablePorts();
            if (!availablePorts.isEmpty()) {
                System.out.println("Menjalankan socket server di port: " + availablePorts);
                socketServerService.startServers(availablePorts);
            } else {
                System.out.println("Tidak ada port yang tersedia.");
            }
        };
    }

}
