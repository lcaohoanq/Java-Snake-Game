package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import enums.Hover;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import lombok.extern.slf4j.Slf4j;
import styles.UIHovers;
import utils.ApiUtils;
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
//        email = loginView.getDataWhenLogin().getEmail();
//        password = loginView.getDataWhenLogin().getPassword();
//
//        if (loginView.isAdmin()) {
//            loginView.handleSuccess();
//            log.info("Admin login successful");
//            return;
//        }
//        //prevent empty field when click submit button, but not when click on the menu
//        if (loginView.isEmpty() && e.getSource() instanceof JButton) {
//            UIPrompts.IS_EMPTY_FIELD();
//            log.error("Empty field when login, please try again");
//        } else {
//            login(email, password);
//        }

    }

    private void login(String email_phone, String password) {
        // Create a new thread to avoid blocking the Swing event dispatch thread
        new Thread(() -> {
            try {
                // Replace with your API URL
                String apiUrl = "http://localhost:8081/users/login";
                // Create the payload as a map and convert it to JSON
                Map<String, String> payload = Map.of(
                    "email_phone", email_phone, // Replace with actual value
                    "password", password // Replace with actual value
                );

                // Send the request and get the response
                HttpResponse<String> response = ApiUtils.postRequest(apiUrl, payload);

                // Handle the response
                switch (response.statusCode()) {
                    case 200:
                        loginView.handleSuccess();
                        break;
                    case 400:
                        JOptionPane.showMessageDialog(null,
                            "Username or password is incorrect, please try again!");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null,
                            "Internal server error, please try again later!");
                        break;
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
                uiHovers.setHoverEmail(Hover.ENABLE.isStatus(), "light");
            } else {
                uiHovers.setHoverEmail(Hover.ENABLE.isStatus(), "dark");
            }
        }
        if (e.getSource() == loginView.getJPasswordField_Right_Middle_Password()) {
            if (!loginView.getStatusToggle()) {
                uiHovers.setHoverEmail(Hover.ENABLE.isStatus(), "light");

            } else {
                uiHovers.setHoverEmail(Hover.ENABLE.isStatus(), "dark");
            }
        }
        if (e.getSource() == loginView.getJButton_Right_Bottom_Submit()) {
            if (!loginView.getStatusToggle()) {
                uiHovers.setHoverEmail(Hover.ENABLE.isStatus(), "light");
            } else {
                uiHovers.setHoverEmail(Hover.ENABLE.isStatus(), "dark");
            }
        }
        if (e.getSource() == loginView.getJButton_Right_Bottom_Others()) {
            uiHovers.setHoverOther(Hover.ENABLE.isStatus());
        }
        if (e.getSource() == loginView.getJButton_Right_Bottom_Forgot_Password()) {
            uiHovers.setHoverForgotPassword(Hover.ENABLE.isStatus());
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == loginView.getJTextField_Right_Middle_Email()) {
            if (!loginView.getStatusToggle()) {
                uiHovers.setHoverEmail(Hover.DISABLE.isStatus(), "light");
            } else {
                uiHovers.setHoverEmail(Hover.DISABLE.isStatus(), "dark");
            }
        }
        if (e.getSource() == loginView.getJTextField_Right_Middle_FirstName()) {
            if (!loginView.getStatusToggle()) {
                uiHovers.setHoverFirstName(Hover.DISABLE.isStatus(), "light");
            } else {
                uiHovers.setHoverFirstName(Hover.DISABLE.isStatus(), "dark");
            }
        }
        if (e.getSource() == loginView.getJTextField_Right_Middle_LastName()) {
            if (!loginView.getStatusToggle()) {
                uiHovers.setHoverLastName(Hover.DISABLE.isStatus(), "light");
            } else {
                uiHovers.setHoverLastName(Hover.DISABLE.isStatus(), "dark");
            }
        }
        if (e.getSource() == loginView.getJPasswordField_Right_Middle_Password()) {
            if (!loginView.getStatusToggle()) {
                uiHovers.setHoverPassword(Hover.DISABLE.isStatus(), "light");
            } else {
                uiHovers.setHoverPassword(Hover.DISABLE.isStatus(), "dark");
            }
        }
        if (e.getSource() == loginView.getJButton_Right_Bottom_Submit()) {
            if (!loginView.getStatusToggle()) {
                uiHovers.setHoverButton(Hover.DISABLE.isStatus(), "light");
            } else {
                uiHovers.setHoverButton(Hover.DISABLE.isStatus(), "dark");
            }
        }
        if (e.getSource() == loginView.getJButton_Right_Bottom_Others()) {
            uiHovers.setHoverOther(Hover.DISABLE.isStatus());
        }
        if (e.getSource() == loginView.getJButton_Right_Bottom_Forgot_Password()) {
            uiHovers.setHoverForgotPassword(Hover.DISABLE.isStatus());
        }
    }
}
