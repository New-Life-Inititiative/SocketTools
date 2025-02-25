package com.miniproject.sockettools.service;

import java.io.IOException;
import java.net.ServerSocket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class PortCheckerService {
    public List<Integer> getAvailablePorts() {
        List<Integer> availablePorts = new ArrayList<>();

        try {
            // Baca daftar port dari file resources
            Path path = new ClassPathResource("ports.txt").getFile().toPath();
            List<String> ports = Files.readAllLines(path);

            // Cek setiap port apakah tersedia
            for (String portStr : ports) {
                int port = Integer.parseInt(portStr.trim());
                if (isPortAvailable(port)) {
                    availablePorts.add(port); // Simpan port yang tersedia
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return availablePorts;
    }

    private boolean isPortAvailable(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            return true; // Jika bisa bind, berarti port tersedia
        } catch (IOException e) {
            return false; // Port sudah digunakan
        }
    }
}
