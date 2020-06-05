package plantenApp.java.model;

import plantenApp.java.dao.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Bradley
 **/
public class SearchHandler {
    AbiotischeFactorenDAO abiotischeFactorenDAO;
    BeheerDAO beheerDAO;
    CommensalismeDAO commensalismeDAO;
    ExtraDAO extraDAO;
    FenotypeDAO fenotypeDAO;
    PlantDAO plantDAO;
    ArrayList<Integer> ids;
    ArrayList<Plant> planten;

    public SearchHandler(Connection dbConnection) throws SQLException {
        this.abiotischeFactorenDAO = new AbiotischeFactorenDAO(dbConnection);
        this.beheerDAO = beheerDAO = new BeheerDAO(dbConnection);
        this.commensalismeDAO = new CommensalismeDAO(dbConnection);
        this.extraDAO = new ExtraDAO(dbConnection);
        this.fenotypeDAO = new FenotypeDAO(dbConnection);
        this.plantDAO = new PlantDAO(dbConnection);

        ids = new ArrayList<Integer>();
        planten = new ArrayList<Plant>();
    }

    public ArrayList<Plant> Search(BindingData bindingData, Connection dbConnection) throws SQLException {


        ids = plantDAO.FilterOn(bindingData);
        if (ids.size() != 0) {
            ids = fenotypeDAO.FilterOn(ids, bindingData);
        }
        if (ids.size() != 0) {
            ids = commensalismeDAO.FilterOn(ids, bindingData);
        }
        if (ids.size() != 0) {
            ids = abiotischeFactorenDAO.FilterOn(ids, bindingData);
        }
        if (ids.size() != 0) {
            ids = extraDAO.FilterOn(ids, bindingData);
        }
        if (ids.size() != 0) {
            ids = beheerDAO.FilterOn(ids, bindingData);
        }

        for (int id:ids
             ) {
            planten.add(plantDAO.getPlantById(id));
        }

        return planten;
    }


}
