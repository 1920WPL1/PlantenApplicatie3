package plantenApp.java.model;

import plantenApp.java.dao.*;
import plantenApp.java.model.data.GUIdata;

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

    public ArrayList<Plant> Search(GUIdata guiData, Connection dbConnection) throws SQLException {


        ids = plantDAO.FilterOn(guiData);
        if (ids.size() != 0) {
            ids = fenotypeDAO.FilterOn(ids, guiData);
            System.out.println(ids.toString());
        }
        if (ids.size() != 0) {
            ids = commensalismeDAO.FilterOn(ids, guiData);
            System.out.println(ids.toString());
        }
        if (ids.size() != 0) {
            ids = abiotischeFactorenDAO.FilterOn(ids, guiData);
            System.out.println(ids.toString());
        }
        if (ids.size() != 0) {
            ids = extraDAO.FilterOn(ids, guiData);
            System.out.println(ids.toString());
        }
        if (ids.size() != 0) {
            ids = beheerDAO.FilterOn(ids, guiData);
            System.out.println(ids.toString());
        }

        for (int id : ids
        ) {
            planten.add(plantDAO.getPlantById(id));
        }

        return planten;
    }

    public Plant SelectFullPlant(Plant plant) throws SQLException {
        return plantDAO.getFullPlantById(plant.getId());
    }


}
