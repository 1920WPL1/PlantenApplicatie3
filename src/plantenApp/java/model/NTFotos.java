package plantenApp.java.model;

import javafx.scene.image.Image;


/**
 * @author bradley
 */
public class NTFotos
{
    String waarde;
    Image Foto;

    public String getWaarde() {
        return waarde;
    }

    public void setWaarde(String waarde) {
        this.waarde = waarde;
    }

    public Image getFoto() {
        return Foto;
    }

    public void setFoto(Image foto) {
        Foto = foto;
    }

    public NTFotos(String waarde, Image foto) {
        this.waarde = waarde;
        Foto = foto;
    }
}
