package plantenApp.java.model;

import plantenApp.java.utils.ArrayBindings;
import plantenApp.java.utils.Bindings;

import java.util.ArrayList;
import java.util.EnumMap;


/**
 * @author  bradley
 * maakt alle verschillende bindings aan
 */
public class BindingData {
    public EnumMap<Bindings, PropertyClass<Value>> dataBindings;
    public EnumMap<ArrayBindings, PropertyClass<ArrayList<Value>>> arrayDataBindings;

    public BindingData() {
        dataBindings = new EnumMap<Bindings, PropertyClass<Value>>(Bindings.class);
        dataBindings.put(Bindings.BEHANDELING, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.BIJVRIENDELIJK, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.BLADVORM, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.EETBAAR, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.GEUREND, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.KRUIDGEBRUIK, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.LEVENSDUUR, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.MAAND, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.ONTWIKKELINGSSNELHEID, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.RATIOBLOEIBLAD, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.REACTIEANTAGONISTISCHEOMGEVING, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.SPRUITFENOLOGIE, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.VLINDERVRIENDELIJK, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.VORSTGEVOELIG, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.VOEDINGSBEHOEFTE, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.NECTARWAARDE, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.POLLENWAARDE, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.PERXJAAR, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.BLADKLEUR, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.BLADHOOGTE, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.BLOEIKLEUR, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.BEZONNING, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.VOCHTBEHOEFTE, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.BLADGROOTTE, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.HABITAT, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.STRATEGIE, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.HABITUS, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.BLOEIWIJZE, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.LEVENSVORM, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.TYPE, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.FAMILIE, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.SEARCH, new PropertyClass<Value>(new Value()));

        arrayDataBindings = new EnumMap<ArrayBindings, PropertyClass<ArrayList<Value>>>(ArrayBindings.class);
        arrayDataBindings.put(ArrayBindings.BLADKLEURPERMAAND, new PropertyClass<ArrayList<Value>>(new ArrayList<Value>()));
        arrayDataBindings.put(ArrayBindings.BLOEIKLEURPERMAAND, new PropertyClass<ArrayList<Value>>(new ArrayList<Value>()));
        arrayDataBindings.put(ArrayBindings.SOCIABILITEIT,new PropertyClass<ArrayList<Value>>(new ArrayList<Value>()));
        arrayDataBindings.put(ArrayBindings.GRONDSOORT,new PropertyClass<ArrayList<Value>>(new ArrayList<Value>()));
        arrayDataBindings.put(ArrayBindings.MAXBLADHOOGTEPERMAAND,new PropertyClass<ArrayList<Value>>(new ArrayList<Value>()));
        arrayDataBindings.put(ArrayBindings.MINBLOEIHOOGTEPERMAAND,new PropertyClass<ArrayList<Value>>(new ArrayList<Value>()));
        arrayDataBindings.put(ArrayBindings.MAXBLOEIHOOGTEPERMAAND,new PropertyClass<ArrayList<Value>>(new ArrayList<Value>()));
    }

}
