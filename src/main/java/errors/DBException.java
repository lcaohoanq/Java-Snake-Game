package errors;

import java.sql.SQLException;

public class DBException extends SQLException {
    public DBException(String message) {
        super(message);
    }
}
