package plantenApp.java.model;

import plantenApp.java.dao.*;
import plantenApp.java.utils.Bindings;

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

    public ArrayList<Integer> Search(BindingData bindingData, Connection dbConnection) throws SQLException {
        ArrayList<Integer> ids = new ArrayList<Integer>();

        ids = plantDAO.FilterOn(bindingData);
        ids = fenotypeDAO.FilterOn(ids, bindingData);
        ids = commensalismeDAO.FilterOn(ids, bindingData);
        ids = abiotischeFactorenDAO.FilterOn(ids, bindingData);
        ids = extraDAO.FilterOn(ids, bindingData);
        ids = beheerDAO.FilterOn(ids, bindingData);

        return ids;
    }


}
