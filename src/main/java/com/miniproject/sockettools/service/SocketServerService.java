package com.miniproject.sockettools.service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;

@Service
public class SocketServerService {
    private final List<ServerSocket> serverSockets = new ArrayList<>();
    private final ExecutorService executorService = Executors.newFixedThreadPool(10); // Thread Pool untuk handle client

    public void startServers(List<Integer> ports) {
        for (int port : ports) {
            executorService.submit(() -> startSocketServer(port));
        }
    }

    private void startSocketServer(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSockets.add(serverSocket);
            System.out.println("Socket Server berjalan di port: " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client terhubung di port: " + port);
                // TODO: Handle komunikasi dengan client di sini
            }
        } catch (IOException e) {
            System.err.println("Gagal menjalankan socket server di port: " + port);
        }
    }

    public void stopServers() {
        for (ServerSocket serverSocket : serverSockets) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
