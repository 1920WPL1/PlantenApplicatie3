package plantenApp.java.model;

/**@author Siebe*/
public class Plant {
    private int id;
    private String type;
    private String familie;
    private String geslacht;
    private String soort;
    private String variatie;
    private int minPlantdichtheid;
    private int maxPlantdichtheid;

    /**@author Bradley**/
    /*externe klasses eigenschappen*/
    private AbiotischeFactoren abiotischeFactoren;
    private Beheer beheer;
    private Commensalisme commensalisme;
    private Extra extra;
    private Fenotype fenotype;
    private Foto foto;

    public Plant(int id, String type, String familie, String geslacht, String soort, String variatie, int minPlantdichtheid, int maxPlantdichtheid) {
        this.id = id;
        this.type = type;
        this.familie = familie;
        this.geslacht = geslacht;
        this.soort = soort;
        this.variatie = variatie;
        this.minPlantdichtheid = minPlantdichtheid;
        this.maxPlantdichtheid = maxPlantdichtheid;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getFamilie() {
        return familie;
    }

    public String getGeslacht() {
        return geslacht;
    }

    public String getSoort() {
        return soort;
    }

    public String getVariatie() {
        return variatie;
    }

    public int getMinPlantdichtheid() {
        return minPlantdichtheid;
    }

    public int getMaxPlantdichtheid() {
        return maxPlantdichtheid;
    }

    public AbiotischeFactoren getAbiotischeFactoren() {
        return abiotischeFactoren;
    }

    public Beheer getBeheer() {
        return beheer;
    }

    public Commensalisme getCommensalisme() {
        return commensalisme;
    }

    public Extra getExtra() {
        return extra;
    }

    public Fenotype getFenotype() {
        return fenotype;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setAbiotischeFactoren(AbiotischeFactoren abiotischeFactoren) {
        this.abiotischeFactoren = abiotischeFactoren;
    }

    public void setBeheer(Beheer beheer) {
        this.beheer = beheer;
    }

    public void setCommensalisme(Commensalisme commensalisme) {
        this.commensalisme = commensalisme;
    }

    public void setExtra(Extra extra) {
        this.extra = extra;
    }

    public void setFenotype(Fenotype fenotype) {
        this.fenotype = fenotype;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }
}
