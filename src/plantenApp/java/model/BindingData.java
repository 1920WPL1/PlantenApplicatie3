package plantenApp.java.model;

import plantenApp.java.utils.ArrayBindings;
import plantenApp.java.utils.Bindings;

import java.util.ArrayList;
import java.util.EnumMap;


/**
 * @author bradley
 * maakt alle verschillende bindings aan
 */
public class BindingData {
    public EnumMap<Bindings, PropertyClass<Value>> dataBindings;
    public EnumMap<ArrayBindings, PropertyClass<ValueWithBoolean[]>> arrayDataBindings;

    public BindingData() {
        dataBindings = new EnumMap<Bindings, PropertyClass<Value>>(Bindings.class);
        dataBindings.put(Bindings.BEHANDELING, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.BLADVORM, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.LEVENSDUUR, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.MAAND, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.ONTWIKKELINGSSNELHEID, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.RATIOBLOEIBLAD, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.REACTIEANTAGONISTISCHEOMGEVING, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.SPRUITFENOLOGIE, new PropertyClass<Value>(new Value()));
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
        dataBindings.put(Bindings.TYPE, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.FAMILIE, new PropertyClass<Value>(new Value()));
        dataBindings.put(Bindings.SEARCH, new PropertyClass<Value>(new Value()));

        arrayDataBindings = new EnumMap<ArrayBindings, PropertyClass<ValueWithBoolean[]>>(ArrayBindings.class);
        arrayDataBindings.put(ArrayBindings.BLADKLEURPERMAAND, new PropertyClass<ValueWithBoolean[]>(new ValueWithBoolean[12]));
        for (int i = 0; i < arrayDataBindings.get(ArrayBindings.BLADKLEURPERMAAND).getValue().length; i++){

            arrayDataBindings.get(ArrayBindings.BLADKLEURPERMAAND).getValue()[i] = new ValueWithBoolean();

        }
        arrayDataBindings.put(ArrayBindings.BLOEIKLEURPERMAAND, new PropertyClass<ValueWithBoolean[]>(new ValueWithBoolean[12]));
        for (int i = 0; i < arrayDataBindings.get(ArrayBindings.BLOEIKLEURPERMAAND).getValue().length; i++){

            arrayDataBindings.get(ArrayBindings.BLOEIKLEURPERMAAND).getValue()[i] = new ValueWithBoolean();

        }
        arrayDataBindings.put(ArrayBindings.SOCIABILITEIT, new PropertyClass<ValueWithBoolean[]>(new ValueWithBoolean[5]));
        for (int i = 0; i < arrayDataBindings.get(ArrayBindings.SOCIABILITEIT).getValue().length; i++){

            arrayDataBindings.get(ArrayBindings.SOCIABILITEIT).getValue()[i] = new ValueWithBoolean();

        }
        arrayDataBindings.put(ArrayBindings.GRONDSOORT, new PropertyClass<ValueWithBoolean[]>(new ValueWithBoolean[3]));
        for (int i = 0; i < arrayDataBindings.get(ArrayBindings.GRONDSOORT).getValue().length; i++){

            arrayDataBindings.get(ArrayBindings.GRONDSOORT).getValue()[i] = new ValueWithBoolean();

        }
        arrayDataBindings.put(ArrayBindings.MAXBLADHOOGTEPERMAAND, new PropertyClass<ValueWithBoolean[]>(new ValueWithBoolean[12]));
        for (int i = 0; i < arrayDataBindings.get(ArrayBindings.MAXBLADHOOGTEPERMAAND).getValue().length; i++){

            arrayDataBindings.get(ArrayBindings.MAXBLADHOOGTEPERMAAND).getValue()[i] = new ValueWithBoolean();

        }
        arrayDataBindings.put(ArrayBindings.MINBLOEIHOOGTEPERMAAND, new PropertyClass<ValueWithBoolean[]>(new ValueWithBoolean[12]));
        for (int i = 0; i < arrayDataBindings.get(ArrayBindings.MINBLOEIHOOGTEPERMAAND).getValue().length; i++){

            arrayDataBindings.get(ArrayBindings.MINBLOEIHOOGTEPERMAAND).getValue()[i] = new ValueWithBoolean();

        }
        arrayDataBindings.put(ArrayBindings.MAXBLOEIHOOGTEPERMAAND, new PropertyClass<ValueWithBoolean[]>(new ValueWithBoolean[12]));
        for (int i = 0; i < arrayDataBindings.get(ArrayBindings.MAXBLOEIHOOGTEPERMAAND).getValue().length; i++){

            arrayDataBindings.get(ArrayBindings.MAXBLOEIHOOGTEPERMAAND).getValue()[i] = new ValueWithBoolean();

        }
        arrayDataBindings.put(ArrayBindings.STRATEGIE, new PropertyClass<ValueWithBoolean[]>(new ValueWithBoolean[8]));
        for (int i = 0; i < arrayDataBindings.get(ArrayBindings.STRATEGIE).getValue().length; i++){

            arrayDataBindings.get(ArrayBindings.STRATEGIE).getValue()[i] = new ValueWithBoolean();

        }
        arrayDataBindings.put(ArrayBindings.HABITUS, new PropertyClass<ValueWithBoolean[]>(new ValueWithBoolean[15]));
        for (int i = 0; i < arrayDataBindings.get(ArrayBindings.HABITUS).getValue().length; i++){

            arrayDataBindings.get(ArrayBindings.HABITUS).getValue()[i] = new ValueWithBoolean();

        }
        arrayDataBindings.put(ArrayBindings.BLOEIWIJZE, new PropertyClass<ValueWithBoolean[]>(new ValueWithBoolean[8]));
        for (int i = 0; i < arrayDataBindings.get(ArrayBindings.BLOEIWIJZE).getValue().length; i++){

            arrayDataBindings.get(ArrayBindings.BLOEIWIJZE).getValue()[i] = new ValueWithBoolean();

        }
        arrayDataBindings.put(ArrayBindings.LEVENSVORM, new PropertyClass<ValueWithBoolean[]>(new ValueWithBoolean[9]));
        for (int i = 0; i < arrayDataBindings.get(ArrayBindings.LEVENSVORM).getValue().length; i++){

            arrayDataBindings.get(ArrayBindings.LEVENSVORM).getValue()[i] = new ValueWithBoolean();

        }
        arrayDataBindings.put(ArrayBindings.MAXBLADHOOGTE, new PropertyClass<ValueWithBoolean[]>(new ValueWithBoolean[2]));
        for (int i = 0; i < arrayDataBindings.get(ArrayBindings.MAXBLADHOOGTE).getValue().length; i++){

            arrayDataBindings.get(ArrayBindings.MAXBLADHOOGTE).getValue()[i] = new ValueWithBoolean();

        }
        arrayDataBindings.put(ArrayBindings.MINGBLOEIHOOGTE, new PropertyClass<ValueWithBoolean[]>(new ValueWithBoolean[2]));
        for (int i = 0; i < arrayDataBindings.get(ArrayBindings.MINGBLOEIHOOGTE).getValue().length; i++){

            arrayDataBindings.get(ArrayBindings.MINGBLOEIHOOGTE).getValue()[i] = new ValueWithBoolean();

        }
        arrayDataBindings.put(ArrayBindings.MAXBLOEIHOOGTE, new PropertyClass<ValueWithBoolean[]>(new ValueWithBoolean[2]));
        for (int i = 0; i < arrayDataBindings.get(ArrayBindings.MAXBLOEIHOOGTE).getValue().length; i++){

            arrayDataBindings.get(ArrayBindings.MAXBLOEIHOOGTE).getValue()[i] = new ValueWithBoolean();

        }
        arrayDataBindings.put(ArrayBindings.VLINDERVRIENDELIJK, new PropertyClass<ValueWithBoolean[]>(new ValueWithBoolean[3]));
        for (int i = 0; i < arrayDataBindings.get(ArrayBindings.VLINDERVRIENDELIJK).getValue().length; i++){

            arrayDataBindings.get(ArrayBindings.VLINDERVRIENDELIJK).getValue()[i] = new ValueWithBoolean();

        }
        arrayDataBindings.put(ArrayBindings.BIJVRIENDELIJK, new PropertyClass<ValueWithBoolean[]>(new ValueWithBoolean[3]));
        for (int i = 0; i < arrayDataBindings.get(ArrayBindings.BIJVRIENDELIJK).getValue().length; i++){

            arrayDataBindings.get(ArrayBindings.BIJVRIENDELIJK).getValue()[i] = new ValueWithBoolean();

        }
        arrayDataBindings.put(ArrayBindings.GEUREND, new PropertyClass<ValueWithBoolean[]>(new ValueWithBoolean[3]));
        for (int i = 0; i < arrayDataBindings.get(ArrayBindings.GEUREND).getValue().length; i++){

            arrayDataBindings.get(ArrayBindings.GEUREND).getValue()[i] = new ValueWithBoolean();

        }
        arrayDataBindings.put(ArrayBindings.EETBAAR, new PropertyClass<ValueWithBoolean[]>(new ValueWithBoolean[3]));
        for (int i = 0; i < arrayDataBindings.get(ArrayBindings.EETBAAR).getValue().length; i++){

            arrayDataBindings.get(ArrayBindings.EETBAAR).getValue()[i] = new ValueWithBoolean();

        }
        arrayDataBindings.put(ArrayBindings.KRUIDGEBRUIK, new PropertyClass<ValueWithBoolean[]>(new ValueWithBoolean[3]));
        for (int i = 0; i < arrayDataBindings.get(ArrayBindings.KRUIDGEBRUIK).getValue().length; i++){

            arrayDataBindings.get(ArrayBindings.KRUIDGEBRUIK).getValue()[i] = new ValueWithBoolean();

        }
        arrayDataBindings.put(ArrayBindings.VORSTGEVOELIG, new PropertyClass<ValueWithBoolean[]>(new ValueWithBoolean[3]));
        for (int i = 0; i < arrayDataBindings.get(ArrayBindings.VORSTGEVOELIG).getValue().length; i++){

            arrayDataBindings.get(ArrayBindings.VORSTGEVOELIG).getValue()[i] = new ValueWithBoolean();

        }



    }

}
