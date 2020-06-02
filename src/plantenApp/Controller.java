package plantenApp;
import plantenApp.java.dao.Database;
import plantenApp.java.dao.InfoTablesDAO;
import plantenApp.java.model.InfoTables;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
public class Controller {
    public TextField txtSearch;
    public Spinner nudMinBladhoogte;
    public Spinner nudMaxBladhoogte;
    public Spinner nudMinBloeihoogte;
    public Spinner nudBloeiHoogte;
    public Slider sldBezonning;
    public Slider sldVochtbehoefte;
    public CheckBox chkGrondsoort_Z;
    public CheckBox chkGrondsoort_L;
    public CheckBox chkGrondsoort_K;
    public CheckBox chkVoedingsbehoefte;
    public CheckBox chkReactieAntagonistischeOmg;
    public CheckBox chkOntwikkelingssnelheid;
    public CheckBox chkLevensduur_concurrentiekracht;
    public CheckBox chkSociabiliteit;
    public CheckBox chkStrategie;
    public CheckBox chkNectarwaarde;
    public CheckBox chkPollenwaarde;
    public CheckBox chkBijvriendelijk;
    public CheckBox chkVlindervriendelijk;
    public CheckBox chkEetbaar;
    public CheckBox chkKruidgebruik;
    public CheckBox chkGeurend;
    public CheckBox chkVorstgevoelig;
    public CheckBox chkBladvorm;
    public CheckBox chkRatio_bloei_blad;
    public CheckBox chkSpruitfenologie;
    public CheckBox chkBehandeling;
    public CheckBox chkMaand;
    public CheckBox chkPerXJaar;
    public Slider sldVoedingsbehoefte;
    public CheckBox chkSociabiliteit_1;
    public CheckBox chkSociabiliteit_2;
    public CheckBox chkSociabiliteit_3;
    public CheckBox chkSociabiliteit_4;
    public CheckBox chkSociabiliteit_5;
    public RadioButton rdbStrategie_Onbekend;
    public RadioButton rdbStrategie_C;
    public RadioButton rdbStrategie_CR;
    public RadioButton rdbStrategie_CRS;
    public RadioButton rdbStrategie_CS;
    public RadioButton rdbStrategie_R;
    public RadioButton rdbStrategie_RS;
    public RadioButton rdbStrategie_S;
    public Slider sldNectarwaarde;
    public Slider sldPollenwaarde;
    public RadioButton rdbBijvriendelijk_Ja;
    public RadioButton rdbBijvriendelijk_Nee;
    public RadioButton rdbBijvriendelijk_Onbekend;
    public RadioButton rdbVlindervriendelijk_Ja;
    public RadioButton rdbVlindervriendelijk_Nee;
    public RadioButton rdbVlindervriendelijk_Onbekend;
    public RadioButton rdbEetbaar_Ja;
    public RadioButton rdbEetbaar_Nee;
    public RadioButton rdbEetbaar_Onbekend;
    public RadioButton rdbKruidgebruik_Ja;
    public RadioButton rdbKruidgebruik_Nee;
    public RadioButton rdbKruidgebruik_Onbekend;
    public RadioButton rdbGeurend_Ja;
    public RadioButton rdbGeurend_Nee;
    public RadioButton rdbGeurend_Onbekend;
    public RadioButton rdbVorstgevoelig_Ja;
    public RadioButton rdbVorstgevoelig_Nee;
    public RadioButton rdbVorstgevoelig_Onbekend;
    public Spinner nudPerXJaar;
    public CheckBox chkMaxBladhoogte;
    public Spinner nudMaxBladhoogte_Jan;
    public Spinner nudMaxBladhoogte_Feb;
    public Spinner nudMaxBladhoogte_Maa;
    public Spinner nudMaxBladhoogte_Apr;
    public Spinner nudMaxBladhoogte_Mei;
    public Spinner nudMaxBladhoogte_Jun;
    public Spinner nudMaxBladhoogte_Jul;
    public Spinner nudMaxBladhoogte_Aug;
    public Spinner nudMaxBladhoogte_Sept;
    public Spinner nudMaxBladhoogte_Okt;
    public Spinner nudMaxBladhoogte_Nov;
    public Spinner nudMaxBladhoogte_Dec;
    public CheckBox chkBladkleur;
    public CheckBox chkMinBloeihoogte;
    public Spinner nudMinBloeihoogte_Jan;
    public Spinner nudMinBloeihoogte_Feb;
    public Spinner nudMinBloeihoogte_Maa;
    public Spinner nudMinBloeihoogte_Apr;
    public Spinner nudMinBloeihoogte_Mei;
    public Spinner nudMinBloeihoogte_Jun;
    public Spinner nudMinBloeihoogte_Jul;
    public Spinner nudMinBloeihoogte_Aug;
    public Spinner nudMinBloeihoogte_Sept;
    public Spinner nudMinBloeihoogte_Okt;
    public Spinner nudMinBloeihoogte_Nov;
    public Spinner nudMinBloeihoogte_Dec;
    public CheckBox chkMaxBloeihoogte;
    public Spinner nudMaxBloeihoogte_Jan;
    public Spinner nudMaxBloeihoogte_Feb;
    public Spinner nudMaxBloeihoogte_Maa;
    public Spinner nudMaxBloeihoogte_Apr;
    public Spinner nudMaxBloeihoogte_Mei;
    public Spinner nudMaxBloeihoogte_Jun;
    public Spinner nudMaxBloeihoogte_Jul;
    public Spinner nudMaxBloeihoogte_Aug;
    public Spinner nudMaxBloeihoogte_Sept;
    public Spinner nudMaxBloeihoogte_Okt;
    public Spinner nudMaxBloeihoogte_Nov;
    public Spinner nudMaxBloeihoogte_Dec;
    public CheckBox chkBloeikleur;
    public CheckBox chkHabitus;
    public RadioButton rdbHabitus_1;
    public RadioButton rdbHabitus_2;
    public RadioButton rdbHabitus_3;
    public RadioButton rdbHabitus_4;
    public RadioButton rdbHabitus_5;
    public RadioButton rdbHabitus_6;
    public RadioButton rdbHabitus_7;
    public RadioButton rdbHabitus_8;
    public RadioButton rdbHabitus_9;
    public RadioButton rdbHabitus_10;
    public RadioButton rdbHabitus_11;
    public RadioButton rdbHabitus_12;
    public RadioButton rdbHabitus_13;
    public RadioButton rdbHabitus_14;
    public RadioButton rdbHabitus_15;
    public CheckBox chkBloeiwijze;
    public RadioButton rdbBloeiwijze_Aar;
    public RadioButton rdbBloeiwijze_BredePluim;
    public RadioButton rdbBloeiwijze_Etage;
    public RadioButton rdbBloeiwijze_BolofKnop;
    public RadioButton rdbBloeiwijze_Margrietachtig;
    public RadioButton rdbBloeiwijze_Scherm;
    public RadioButton rdbBloeiwijze_SmallePluim;
    public CheckBox chkLevensvormVolgensRaunkhiaer;
    public RadioButton rdbLevensvorm_1;
    public RadioButton rdbLevensvorm_2;
    public RadioButton rdbLevensvorm_3;
    public RadioButton rdbLevensvorm_4;
    public RadioButton rdbLevensvorm_5;
    public RadioButton rdbLevensvorm_6;
    public RadioButton rdbLevensvorm_7;
    public RadioButton rdbLevensvorm_8;
    public RadioButton rdbLevensvorm_9;
    public ComboBox cboOntwikkelingssnelheid;
    private InfoTables infoTables;
    private Connection dbConnection;

    public void initialize() throws SQLException {
        dbConnection = Database.getInstance().getConnection();
        /*infotabel object aanmaken*/
        InfoTablesDAO infotablesDAO = new InfoTablesDAO(dbConnection);
       infoTables = infotablesDAO.getInfoTables();


    }


    public void Clicked_Search(MouseEvent mouseEvent) {

    }

    /**
     * @author Bradley Velghe
     * @param box
     * @param slider
     * @return true > slider is beweegbaar || false > slider is niet beweegbaar
     */
   public boolean Togledisable(CheckBox box, Slider slider){
        if(box.isSelected()){
            slider.setDisable(false);
            return true;
        } else {
            slider.setDisable(true);
            return false;
        }
    }

}


