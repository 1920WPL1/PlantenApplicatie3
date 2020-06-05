package plantenApp;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import plantenApp.java.model.Plant;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class customController extends VBox {
    ArrayList<Plant> planten;

    public customController(ArrayList<Plant> planten) {
        this.planten = planten;

        for (Plant plant: planten) {

            TitledPane panel = new TitledPane();
            panel.setText(plant.getType() + ' '  + plant.getFamilie() +' '+ plant.getGeslacht() +' '+ plant.getSoort() +' '+ plant.getVariatie());
            this.getChildren().add(panel);
        }



    }

    }



