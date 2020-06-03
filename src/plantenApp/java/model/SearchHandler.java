package plantenApp.java.model;

import plantenApp.java.dao.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**@author Bradley**/
public class SearchHandler {


    AbiotischeFactorenDAO abiotischeFactorenDAO;
    BeheerDAO beheerDAO;
    CommensalismeDAO commensalismeDAO;
    ExtraDAO extraDAO;
    FenotypeDAO fenotypeDAO;
    PlantDAO plantDAO;

    public SearchHandler(Connection dbConnection) throws SQLException {
        this.abiotischeFactorenDAO = new AbiotischeFactorenDAO(dbConnection);
        this.beheerDAO = beheerDAO = new BeheerDAO(dbConnection);
        this.commensalismeDAO = new CommensalismeDAO(dbConnection);
        this.extraDAO = new ExtraDAO(dbConnection);
        this.fenotypeDAO = new FenotypeDAO(dbConnection);
        this.plantDAO = new PlantDAO(dbConnection);
    }

    public List<Plant> Search(String type, String familie, String fgsv) throws SQLException {
        List<Plant> planten = new ArrayList<Plant>();


        List<Integer> listFenotypeDAO;






        return planten;
    }
}
