package models;

import constants.Messages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utils.DataHandler;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginModel {
    private String username;
    private String password;
}
