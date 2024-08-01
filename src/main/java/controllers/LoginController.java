package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import lombok.extern.slf4j.Slf4j;
import styles.UIHovers;
import views.LoginView;
import views.UIPrompts;

@Slf4j
public final class LoginController implements ActionListener, MouseListener {

    public static String email = "";
    private final LoginView loginView;
    public String password = "";
    private final UIHovers<LoginView> uiHovers;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public LoginController(LoginView loginView) {
        super();
        this.loginView = loginView;
        this.uiHovers = new UIHovers<>(loginView);
        httpClient = HttpClient.newHttpClient();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule()).enable(
            SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        email = loginView.getDataWhenLogin().getEmail();
        password = loginView.getDataWhenLogin().getPassword();

        if (loginView.isAdmin()) {
            loginView.handleSuccess();
            log.info("Admin login successful");
            return;
        }
        //prevent empty field when click submit button, but not when click on the menu
        if (loginView.isEmpty() && e.getSource() instanceof JButton) {
            UIPrompts.IS_EMPTY_FIELD();
            log.error("Empty field when login, please try again");
        } else {
            login(email, password);
        }

    }

    private void login(String email_phone, String password) {
        // Create a new thread to avoid blocking the Swing event dispatch thread
        new Thread(() -> {
            try {
                // Replace with your API URL
                String apiUrl = "http://localhost:8081/users/login";
                HttpClient httpClient = HttpClient.newHttpClient();
                ObjectMapper objectMapper = new ObjectMapper();

                // Create the payload as a map and convert it to JSON
                Map<String, String> payload = Map.of(
                    "email_phone", email_phone, // Replace with actual value
                    "password", password // Replace with actual value
                );
                String jsonPayload = objectMapper.writeValueAsString(payload);

                // Build the HTTP request
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                    .build();

                // Send the request and get the response
                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                // Handle the response
                if (response.statusCode() == 200) {
                    loginView.handleSuccess();
                } else if(response.statusCode() == 400) {
                    JOptionPane.showMessageDialog(null, "Wrong email, phone or password");
                } else {
                    JOptionPane.showMessageDialog(null, "Internal server error, please try again later!");
                }
            } catch (IOException | InterruptedException ex) {
                JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
            }
        }).start();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == loginView.getJTextField_Right_Middle_Email()) {
            if (!loginView.getStatusToggle()) {
                uiHovers.setHoverEmail(true, "light");
            } else {
                uiHovers.setHoverEmail(true, "dark");
            }
        }
        if (e.getSource() == loginView.getJPasswordField_Right_Middle_Password()) {
            if (!loginView.getStatusToggle()) {
                uiHovers.setHoverPassword(true, "light");
            } else {
                uiHovers.setHoverPassword(true, "dark");
            }
        }
        if (e.getSource() == loginView.getJButton_Right_Bottom_Submit()) {
            if (!loginView.getStatusToggle()) {
                uiHovers.setHoverButton(true, "light");
            } else {
                uiHovers.setHoverButton(true, "dark");
            }
        }
        if (e.getSource() == loginView.getJButton_Right_Bottom_Others()) {
            uiHovers.setHoverOther(true);
        }
        if(e.getSource() == loginView.getJButton_Right_Bottom_Forgot_Password()){
            uiHovers.setHoverForgotPassword(true);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == loginView.getJTextField_Right_Middle_Email()) {
            if (!loginView.getStatusToggle()) {
                uiHovers.setHoverEmail(false, "light");
            } else {
                uiHovers.setHoverEmail(false, "dark");
            }
        }
        if (e.getSource() == loginView.getJTextField_Right_Middle_FirstName()) {
            if (!loginView.getStatusToggle()) {
                uiHovers.setHoverFirstName(false, "light");
            } else {
                uiHovers.setHoverFirstName(false, "dark");
            }
        }
        if (e.getSource() == loginView.getJTextField_Right_Middle_LastName()) {
            if (!loginView.getStatusToggle()) {
                uiHovers.setHoverLastName(false, "light");
            } else {
                uiHovers.setHoverLastName(false, "dark");
            }
        }
        if (e.getSource() == loginView.getJPasswordField_Right_Middle_Password()) {
            if (!loginView.getStatusToggle()) {
                uiHovers.setHoverPassword(false, "light");
            } else {
                uiHovers.setHoverPassword(false, "dark");
            }
        }
        if (e.getSource() == loginView.getJButton_Right_Bottom_Submit()) {
            if (!loginView.getStatusToggle()) {
                uiHovers.setHoverButton(false, "light");
            } else {
                uiHovers.setHoverButton(false, "dark");
            }
        }
        if (e.getSource() == loginView.getJButton_Right_Bottom_Others()) {
            uiHovers.setHoverOther(false);
        }
        if(e.getSource() == loginView.getJButton_Right_Bottom_Forgot_Password()){
            uiHovers.setHoverForgotPassword(false);
        }
    }
}
