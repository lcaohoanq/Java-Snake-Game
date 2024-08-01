package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;
import javax.swing.SwingUtilities;
import modules.user.UserDAO;
import utils.ApiUtils;
import views.ScoreView;

public class ScoreController implements ActionListener {

    private final ScoreView scoreView;
    private final UserDAO executeQuery = UserDAO.getInstance();
    private ObjectMapper objectMapper;
    public ScoreController(ScoreView scoreView) {
        this.scoreView = scoreView;
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule()).enable(
            SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        scoreView.clearTextArea();
        scoreView.setVisible(true);
        fetchData();
    }

    private void fetchData() {
        // Create a new thread to avoid blocking the Swing event dispatch thread
        new Thread(() -> {
            try {
                //https://jsonplaceholder.typicode.com/posts/1
                HttpResponse<String> response = ApiUtils.getRequest("http://localhost:8081/users");

                if (response.statusCode() == 200) {
                    String responseBody = response.body();

                    //Object json = objectMapper.readValue(responseBody, Object.class);
                    List<Object> usersList = objectMapper.readValue(responseBody, new TypeReference<>() {
                    }); //deserializing JSON array to a list of User objects

                    String formattedJson = objectMapper.writeValueAsString(usersList);

                    SwingUtilities.invokeLater(() -> scoreView.appendTextArea(formattedJson));
                } else {
                    SwingUtilities.invokeLater(() -> scoreView.appendTextArea("GET request failed: " + response.statusCode()));
                }
            } catch (IOException | InterruptedException ex) {
                SwingUtilities.invokeLater(() -> scoreView.appendTextArea("Exception: " + ex.getMessage()));
            }
        }).start();
    }
}
