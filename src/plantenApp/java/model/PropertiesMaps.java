package plantenApp.java.model;

import javafx.beans.property.StringProperty;

import java.util.EnumMap;
import java.util.Map;

public class PropertiesMaps  {
    enum EintBindings {
        VOEDINGSBEHOEFTE,
        SOCIABILITEIT,
        NECTARWAARDE,
        POLLENWAARDE,


    }

    enum EstringBindings {
        REACTIEANTAGONISTISCHEOMGEVING,
        ONTWIKKELINGSSNELHEID,
        LEVENSDUUR,
        STRATEGIE,
        BIJVRIENDELIJK,
        VLINDERVRIENDELIJK,
        EETBAAR,

    }

    enum EintArrayBindings {

    }

    enum EstringArrayBindings {

    }

    EnumMap<EintBindings, IntProperty> intProperties;
    EnumMap<EintBindings, StringProperty> stringProperties;

    public PropertiesMaps() {
        intProperties = new EnumMap<EintBindings, IntProperty>(EintBindings.class);

        intProperties.put(EintBindings.VOEDINGSBEHOEFTE, new IntProperty());
    }

}
