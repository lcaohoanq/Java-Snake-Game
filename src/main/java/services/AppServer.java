package services;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.InetSocketAddress;

public class AppServer extends JFrame {
    private JButton toggleButton;
    private HttpServer server;
    private boolean serverRunning;

    private int port = 7000;

    public AppServer() {
        super("HTTP Server Controller");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Change to DO_NOTHING_ON_CLOSE
        setLayout(new FlowLayout());

        toggleButton = new JButton("Start Server");
        toggleButton.addActionListener(new ToggleButtonListener());
        add(toggleButton);

        setSize(300, 100);
        setLocationRelativeTo(null);
        setVisible(true);

        // Add a WindowListener
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (server != null) {
                    server.stop(0);
                    System.out.println("Server stopped");
                }
                System.exit(0); // Exit the application
            }
        });
    }

    private class ToggleButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!serverRunning) {
                try {
                    server = HttpServer.create(new InetSocketAddress(port), 0);
                    server.createContext("/", new MyHandler());
                    server.setExecutor(null);
                    server.start();
                    serverRunning = true;
                    System.out.println("Server is running at port: " + port);
                    toggleButton.setText("Stop Server");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                if (server != null) {
                    server.stop(0);
                    serverRunning = false;
                    System.out.println("Server stopped");
                    toggleButton.setText("Start Server");
                }
            }
        }
    }

    private class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "Server is running at port: " + port + "\n";
            exchange.sendResponseHeaders(200, response.length());
            exchange.getResponseBody().write(response.getBytes());
            exchange.getResponseBody().close();
        }
    }
}
