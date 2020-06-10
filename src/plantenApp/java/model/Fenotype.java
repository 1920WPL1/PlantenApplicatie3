package plantenApp.java.model;

import java.util.ArrayList;

/**
 * @author Siebe
 */
public class Fenotype {
    private int id;
    private int plant_id;
    private String bladvorm;
    private String levensvorm;
    private String habitus;
    private String bloeiwijze;
    private int bladgrootte;
    private String ratio_bloei_blad;
    private String spruitfenologie;

    private ArrayList<FenoMulti_Eigenschap> multiEigenschappen;

    public Fenotype(int id, int plant_id, String bladvorm, String levensvorm, String habitus, String bloeiwijze, int bladgrootte, String ratio_bloei_blad, String spruitfenologie, ArrayList<FenoMulti_Eigenschap> multiEigenschappen) {
        this.id = id;
        this.plant_id = plant_id;
        this.bladvorm = bladvorm;
        this.levensvorm = levensvorm;
        this.habitus = habitus;
        this.bloeiwijze = bloeiwijze;
        this.bladgrootte = bladgrootte;
        this.ratio_bloei_blad = ratio_bloei_blad;
        this.spruitfenologie = spruitfenologie;
        this.multiEigenschappen = multiEigenschappen;
    }

    public int getId() {
        return id;
    }

    public int getPlant_id() {
        return plant_id;
    }

    public String getBladvorm() {
        return bladvorm;
    }

    public String getLevensvorm() {
        return levensvorm;
    }

    public String getHabitus() {
        return habitus;
    }

    public String getBloeiwijze() {
        return bloeiwijze;
    }

    public int getBladgrootte() {
        return bladgrootte;
    }

    public String getRatio_bloei_blad() {
        return ratio_bloei_blad;
    }

    public String getSpruitfenologie() {
        return spruitfenologie;
    }

    public ArrayList<FenoMulti_Eigenschap> getMultiEigenschappen() {
        return multiEigenschappen;
    }

    public FenoMulti_Eigenschap getBladhoogte() {
        for (int i = 0; i < multiEigenschappen.size(); i++) {
            if (multiEigenschappen.get(i).getNaam().equals("bladhoogte")) {
                return multiEigenschappen.get(i);
            }
        }
        return new FenoMulti_Eigenschap();
    }

    public FenoMulti_Eigenschap getMinBloeihoogte() {
        for (int i = 0; i < multiEigenschappen.size(); i++) {
            if (multiEigenschappen.get(i).getNaam().equals("minbloeihoogte")) {
                return multiEigenschappen.get(i);
            }
        }
        return new FenoMulti_Eigenschap();
    }

    public FenoMulti_Eigenschap getMaxBloeihoogte() {
        for (int i = 0; i < multiEigenschappen.size(); i++) {
            if (multiEigenschappen.get(i).getNaam().equals("maxbloeihoogte")) {
                return multiEigenschappen.get(i);
            }
        }
        return new FenoMulti_Eigenschap();
    }

    public FenoMulti_Eigenschap getBladkleur() {
        for (int i = 0; i < multiEigenschappen.size(); i++) {
            if (multiEigenschappen.get(i).getNaam().equals("bladkleur")) {
                return multiEigenschappen.get(i);
            }
        }
        return new FenoMulti_Eigenschap();
    }

    public FenoMulti_Eigenschap getBloeikleur() {
        for (int i = 0; i < multiEigenschappen.size(); i++) {
            if (multiEigenschappen.get(i).getNaam().equals("bloeikleur")) {
                return multiEigenschappen.get(i);
            }
        }
        return new FenoMulti_Eigenschap();
    }
}
