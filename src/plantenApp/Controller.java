package plantenApp;

import plantenApp.java.dao.Database;
import plantenApp.java.model.InfoTables;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.sql.Connection;
import java.sql.SQLException;


public class Controller {
    private InfoTables infoTables;
    private Connection dbConnection;

    public void initialize() throws SQLException {
        dbConnection = Database.getInstance().getConnection();
    }
}


