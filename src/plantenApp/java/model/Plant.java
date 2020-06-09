package plantenApp.java.model;

import java.util.ArrayList;

/**
 * @author Siebe
 */
public class Plant {
    private int id;
    private String type;
    private String familie;
    private String geslacht;
    private String soort;
    private String variatie;
    private int minPlantdichtheid;
    private int maxPlantdichtheid;

    private Foto foto;
    private Beheer beheer;
    private AbiotischeFactoren abiotischeFactoren;
    private Commensalisme commensalisme;
    private Fenotype fenotype;
    private Extra extra;

    public Plant() {

    }

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

    public Plant(int id, String type, String familie, String geslacht, String soort, String variatie, int minPlantdichtheid, int maxPlantdichtheid, Foto foto, Beheer beheer, AbiotischeFactoren abiotischeFactoren, Commensalisme commensalisme, Fenotype fenotype, Extra extra) {
        this.id = id;
        this.type = type;
        this.familie = familie;
        this.geslacht = geslacht;
        this.soort = soort;
        this.variatie = variatie;
        this.minPlantdichtheid = minPlantdichtheid;
        this.maxPlantdichtheid = maxPlantdichtheid;
        this.foto = foto;
        this.beheer = beheer;
        this.abiotischeFactoren = abiotischeFactoren;
        this.commensalisme = commensalisme;
        this.fenotype = fenotype;
        this.extra = extra;
    }

    public void Print() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id:").append(id).append('\n');
        sb.append("Type:").append(type).append('\n');
        sb.append("Familie:").append(familie).append('\n');
        sb.append("Geslacht:").append(geslacht).append('\n');
        sb.append("Soort:").append(soort).append('\n');
        sb.append("Variatie:").append(variatie).append('\n');
        sb.append("MinPlantdichtheid:").append(minPlantdichtheid).append('\n');
        sb.append("MaxPlantdichtheid:").append(maxPlantdichtheid).append('\n');

        sb.append("Beheerdaden:").append('\n');
        for (int i = 0; i < beheer.getMultiEigenschappen().size(); i++) {
            Beheerdaad_Eigenschap beheerdaad = beheer.getMultiEigenschappen().get(i);
            int id = beheerdaad.getId();
            String naam = beheerdaad.getNaam();
            int freq = beheerdaad.getFrequentie();
            String maand = beheerdaad.getMaand();
            String opmerking = beheerdaad.getOpmerking();
            sb.append("Id:").append(id).append(" Naam:").append(naam).append(" Frequentie:").append(freq).append(" Maand:").append(maand).append(" Opmerking:").append(opmerking).append('\n');
        }

        sb.append("Id:").append(abiotischeFactoren.getId()).append('\n');
        sb.append("Bezonning:").append(abiotischeFactoren.getBezonning()).append('\n');
        sb.append("Grondsoort:").append(abiotischeFactoren.getGrondsoort()).append('\n');
        sb.append("ReactieOmgeving:").append(abiotischeFactoren.getReactieAntagonistischeOmgeving()).append('\n');
        sb.append("Vochtbehoefte:").append(abiotischeFactoren.getVochtbehoefte()).append('\n');
        sb.append("Voedingsbehoefte:").append(abiotischeFactoren.getVoedingsbehoefte()).append('\n');

        sb.append("Habitats:").append('\n');
        for (int i = 0; i < abiotischeFactoren.getMultiEigenschappen().size(); i++) {
            AbioMulti_Eigenschap abio = abiotischeFactoren.getMultiEigenschappen().get(i);
            sb.append("Id:").append(abio.getId()).append(" Naam:").append(abio.getNaam()).append(" Value:").append(abio.getValue());
        }

        sb.append("Id:").append(commensalisme.getId()).append('\n');
        sb.append("Ontwikkelingssnelheid:").append(commensalisme.getOntwikkelingssnelheid()).append('\n');
        sb.append("Strategie:").append(commensalisme.getStrategie()).append('\n');

        sb.append("Sociabiliteit:").append('\n');
        for (int i = 0; i < commensalisme.getMultiEigenschappen().size(); i++) {
            CommMulti_Eigenschap com = commensalisme.getMultiEigenschappen().get(i);
            sb.append("Id:").append(com.getId()).append(" Naam:").append(com.getNaam()).append(" Value:").append(com.getValue());
        }

        sb.append("Id:").append(fenotype.getId()).append('\n');
        sb.append("Habitus:").append(fenotype.getHabitus()).append('\n');
        sb.append("Ratio_bloei_blad:").append(fenotype.getRatio_bloei_blad()).append('\n');
        sb.append("Bladgrootte:").append(fenotype.getBladgrootte()).append('\n');
        sb.append("Bladvorm:").append(fenotype.getBladvorm()).append('\n');
        sb.append("Bloeiwijze:").append(fenotype.getBloeiwijze()).append('\n');
        sb.append("Levensvorm:").append(fenotype.getLevensvorm()).append('\n');
        sb.append("Spruitfenologie:").append(fenotype.getSpruitfenologie()).append('\n');

        sb.append("Multi:").append('\n');
        for (int i = 0; i < fenotype.getMultiEigenschappen().size(); i++) {
            FenoMulti_Eigenschap fen = fenotype.getMultiEigenschappen().get(i);
            sb.append("Id:").append(fen.getId()).append(" Naam:").append(fen.getNaam()).append(" Value:").append(fen.getJan()).append(fen.getFeb()).append(fen.getMaa()).append(fen.getApr()).append(fen.getMei()).append(fen.getJun()).append(fen.getJul()).append(fen.getAug()).append(fen.getSep()).append(fen.getOkt()).append(fen.getNov()).append(fen.getDec());
        }

        sb.append("Geurend:").append(extra.getGeurend()).append('\n');
        sb.append("Bijvriendelijk:").append(extra.getBijvriendelijk()).append('\n');
        sb.append("Eetbaar:").append(extra.getEetbaar()).append('\n');
        sb.append("Kruidgebruik:").append(extra.getKruidgebruik()).append('\n');
        sb.append("Nectarwaarde:").append(extra.getNectarwaarde()).append('\n');
        sb.append("Pollenwaarde:").append(extra.getPollenwaarde()).append('\n');
        sb.append("Vorstgevoelig:").append(extra.getVorstgevoelig()).append('\n');

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

    public Foto getFoto() {
        return foto;
    }

    public Beheer getBeheer() {
        return beheer;
    }

    public AbiotischeFactoren getAbiotischeFactoren() {
        return abiotischeFactoren;
    }

    public Commensalisme getCommensalisme() {
        return commensalisme;
    }

    public Fenotype getFenotype() {
        return fenotype;
    }

    public Extra getExtra() {
        return extra;
    }
}
