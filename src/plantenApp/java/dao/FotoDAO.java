package plantenApp.java.dao;

import javafx.scene.image.Image;
import plantenApp.java.model.Foto;
import plantenApp.java.model.Foto_Eigenschap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Siebe
 */
public class FotoDAO implements Queries {
    private Connection dbConnection;
    private PreparedStatement stmtSelectFotoByID;

    public FotoDAO(Connection dbConnection) throws SQLException {
        this.dbConnection = dbConnection;

        stmtSelectFotoByID = dbConnection.prepareStatement(GETFOTOBYPLANTID);
    }

    //region GET

    /**
     * @param id -> plant_id
     * @return -> verzameling van de fotos van de specifieke plant
     * @author Siebe
     */
    public Foto getFotoById(int id) throws SQLException {
        //Dao

        //Items
        Foto foto = null;

        //SqlCommand
        foto = new Foto(
                id,
                getFotos(id)
        );

        //Output
        return foto;
    }

    /**
     * @param id -> plant_id
     * @return -> fotos van de specifieke plant
     * @author Siebe
     */
    private ArrayList<Foto_Eigenschap> getFotos(int id) throws SQLException {
        //Dao

        //Items
        ArrayList<Foto_Eigenschap> fotos = new ArrayList<>();

        //SqlCommand
        stmtSelectFotoByID.setInt(1, id);
        ResultSet rs = stmtSelectFotoByID.executeQuery();
        while (rs.next()) {
            Foto_Eigenschap foto = new Foto_Eigenschap(
                    rs.getInt("foto_id"),
                    rs.getString("eigenschap"),
                    rs.getString("url"),
                    rs.getBlob("figuur")
            );
            fotos.add(foto);
        }

        //Output
        return fotos;
    }


    public void InsertFoto(String waarde, byte[] afbeelding) throws SQLException {
        //Dao

        //Items

        //SqlCommand
        PreparedStatement iifoto = dbConnection.prepareStatement(INSERTFOTO);
        iifoto.setBytes(1, afbeelding);
        iifoto.setString(2, waarde);
        iifoto.execute();

        //Output

    }
    //endregion
}
