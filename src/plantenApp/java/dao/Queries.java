package plantenApp.java.dao;

public interface Queries {
    //region GETBYID
    String GETPLANTBYID =
            "SELECT * FROM plant WHERE plant_id = ?";
    String GETPLANTSBYIDS =
            "SELECT * FROM plant WHERE plant_id IN ?";

    String GETFENOTYPEBYPLANTID =
            "SELECT * FROM fenotype WHERE plant_id = ?";
    String GETFENOTYPEMULTIBYPLANTID =
            "SELECT * FROM fenotype_multi WHERE plant_id = ?";

    String GETABIOTISCHBYPLANTID =
            "SELECT * FROM abiotische_factoren WHERE plant_id = ?";
    String GETABIOTISCHBMULTIYPLANTID =
            "SELECT * FROM abiotisch_multi WHERE plant_id = ?";

    String GETCOMMENSALISMEBYPLANTID =
            "SELECT * FROM commensalisme WHERE plant_id = ?";
    String GETCOMMENSALISMEMULTIBYPLANTID =
            "SELECT * FROM commensalisme_multi WHERE plant_id = ?";

    String GETBEHEERBYPLANTID =
            "SELECT * FROM beheer_multi WHERE plant_id = ?";

    String GETEXTRABYPLANTID =
            "SELECT * FROM extra WHERE plant_id = ?";

    String GETFOTOBYPLANTID =
            "SELECT * FROM foto WHERE plant_id = ?";
    //endregion

    //region NAAKTETABELLEN

    String NTTYPE =
            "SELECT DISTINCT planttype_naam FROM planttype";

    String NTFAMILIE =
            "SELECT DISTINCT familie_naam FROM familie";

    String NTGESLACHT =
            "SELECT DISTINCT geslacht_naam FROM geslacht";

    String NTSOORT =
            "SELECT DISTINCT soort_naam FROM soort";

    String NTVARIATIE =
            "SELECT DISTINCT variatie_naam FROM variatie";

    String NTLEVENSDUURCONCURRENTIEKRACHT =
            "SELECT waarde FROM levensduur_concurrentiekracht";

    String NTSTRATEGIE =
            "SELECT waarde FROM strategie";

    String NTONTWIKKELINGSSNELHEID =
            "SELECT waarde FROM ontwikkelingssnelheid";

    String NTVOEDINGSBEHOEFTE =
            "SELECT waarde FROM voedingsbehoefte";

    String NTVOCHTBEHOEFTE =
            "SELECT waarde FROM vochtbehoefte";

    String NTHABITAT =
            "SELECT waarde FROM habitat";

    String NTREACTIEOMGEVING =
            "SELECT waarde FROM reactieomgeving";

    String NTGRONDSOORT =
            "SELECT waarde FROM grondsoort";

    String NTBEZONNING =
            "SELECT waarde FROM bezonning";

    String NTHABITUS =
            "SELECT waarde FROM habitus";

    String NTKLEUREN =
            "SELECT kleur FROM kleuren";

    String NTBLADVORM =
            "SELECT waarde FROM bladvorm";

    String NTRATIOBLOEIBLAD =
            "SELECT waarde FROM ratio_bloeiblad";

    String NTSPRUITFENOLOGIE =
            "SELECT waarde FROM spruitfenologie";

    String NTBLOEIWIJZE =
            "SELECT waarde FROM bloeiwijze";

    String NTSOCIABILITEIT =
            "SELECT waarde FROM sociabiliteit";

    String NTBEHEERDAAD =
            "SELECT waarde FROM beheerdaad";

    String NTNECTARWAARDE =
            "SELECT waarde FROM nectarwaarde";

    String NTPOLLENWAARDE =
            "SELECT waarde FROM pollenwaarde";

    String NTBLADGROOTTE =
            "SELECT waarde FROM maxbladgrootte";

    String NTFOTOHABITUS =
            "SELECT afbeelding FROM habitus";

    String NTFOTOBLOEIWIJZE =
            "SELECT afbeelding FROM bloeiwijze";
    //endregion

    String INSERTFOTO =
            "UPDATE bloeiwijze SET afbeelding = ? WHERE waarde = ?";
}

