package plantenApp.java.model;

import plantenApp.java.utils.ERequestArrayData;
import plantenApp.java.utils.ERequestData;

import java.util.EnumMap;
import java.util.Map;


/**
 * @author bradley
 * maakt alle verschillende bindings aan
 */
public class BindingData {
    public EnumMap<ERequestData, SearchRequest<RequestValue>> searchRequestData;
    public EnumMap<ERequestArrayData, SearchRequest<RequestValueWBool[]>> searchRequestArrayData;

    public BindingData() {
        searchRequestData = new EnumMap<ERequestData, SearchRequest<RequestValue>>(ERequestData.class);
        PrepSearchRequestData();

        searchRequestArrayData = new EnumMap<ERequestArrayData, SearchRequest<RequestValueWBool[]>>(ERequestArrayData.class);
        PrepSearchRequestArrayData();
    }

    private void PrepSearchRequestData(){
        searchRequestData.put(ERequestData.BEHANDELING, new SearchRequest<RequestValue>(new RequestValue()));
        searchRequestData.put(ERequestData.BLADVORM, new SearchRequest<RequestValue>(new RequestValue()));
        searchRequestData.put(ERequestData.LEVENSDUUR, new SearchRequest<RequestValue>(new RequestValue()));
        searchRequestData.put(ERequestData.MAAND, new SearchRequest<RequestValue>(new RequestValue()));
        searchRequestData.put(ERequestData.ONTWIKKELINGSSNELHEID, new SearchRequest<RequestValue>(new RequestValue()));
        searchRequestData.put(ERequestData.RATIOBLOEIBLAD, new SearchRequest<RequestValue>(new RequestValue()));
        searchRequestData.put(ERequestData.REACTIEANTAGONISTISCHEOMGEVING, new SearchRequest<RequestValue>(new RequestValue()));
        searchRequestData.put(ERequestData.SPRUITFENOLOGIE, new SearchRequest<RequestValue>(new RequestValue()));
        searchRequestData.put(ERequestData.VOEDINGSBEHOEFTE, new SearchRequest<RequestValue>(new RequestValue()));
        searchRequestData.put(ERequestData.NECTARWAARDE, new SearchRequest<RequestValue>(new RequestValue()));
        searchRequestData.put(ERequestData.POLLENWAARDE, new SearchRequest<RequestValue>(new RequestValue()));
        searchRequestData.put(ERequestData.PERXJAAR, new SearchRequest<RequestValue>(new RequestValue()));
        searchRequestData.put(ERequestData.BLADKLEUR, new SearchRequest<RequestValue>(new RequestValue()));
        searchRequestData.put(ERequestData.BLADHOOGTE, new SearchRequest<RequestValue>(new RequestValue()));
        searchRequestData.put(ERequestData.BLOEIKLEUR, new SearchRequest<RequestValue>(new RequestValue()));
        searchRequestData.put(ERequestData.BEZONNING, new SearchRequest<RequestValue>(new RequestValue()));
        searchRequestData.put(ERequestData.VOCHTBEHOEFTE, new SearchRequest<RequestValue>(new RequestValue()));
        searchRequestData.put(ERequestData.BLADGROOTTE, new SearchRequest<RequestValue>(new RequestValue()));
        searchRequestData.put(ERequestData.HABITAT, new SearchRequest<RequestValue>(new RequestValue()));
        searchRequestData.put(ERequestData.TYPE, new SearchRequest<RequestValue>(new RequestValue()));
        searchRequestData.put(ERequestData.FAMILIE, new SearchRequest<RequestValue>(new RequestValue()));
        searchRequestData.put(ERequestData.SEARCH, new SearchRequest<RequestValue>(new RequestValue()));
    }
    private void PrepSearchRequestArrayData(){
        searchRequestArrayData.put(ERequestArrayData.BLADKLEURPERMAAND, new SearchRequest<RequestValueWBool[]>(new RequestValueWBool[12]));
        searchRequestArrayData.put(ERequestArrayData.BLOEIKLEURPERMAAND, new SearchRequest<RequestValueWBool[]>(new RequestValueWBool[12]));
        searchRequestArrayData.put(ERequestArrayData.SOCIABILITEIT, new SearchRequest<RequestValueWBool[]>(new RequestValueWBool[5]));
        searchRequestArrayData.put(ERequestArrayData.GRONDSOORT, new SearchRequest<RequestValueWBool[]>(new RequestValueWBool[3]));
        searchRequestArrayData.put(ERequestArrayData.MAXBLADHOOGTEPERMAAND, new SearchRequest<RequestValueWBool[]>(new RequestValueWBool[12]));
        searchRequestArrayData.put(ERequestArrayData.MINBLOEIHOOGTEPERMAAND, new SearchRequest<RequestValueWBool[]>(new RequestValueWBool[12]));
        searchRequestArrayData.put(ERequestArrayData.MAXBLOEIHOOGTEPERMAAND, new SearchRequest<RequestValueWBool[]>(new RequestValueWBool[12]));
        searchRequestArrayData.put(ERequestArrayData.STRATEGIE, new SearchRequest<RequestValueWBool[]>(new RequestValueWBool[8]));
        searchRequestArrayData.put(ERequestArrayData.HABITUS, new SearchRequest<RequestValueWBool[]>(new RequestValueWBool[15]));
        searchRequestArrayData.put(ERequestArrayData.BLOEIWIJZE, new SearchRequest<RequestValueWBool[]>(new RequestValueWBool[8]));
        searchRequestArrayData.put(ERequestArrayData.LEVENSVORM, new SearchRequest<RequestValueWBool[]>(new RequestValueWBool[9]));
        searchRequestArrayData.put(ERequestArrayData.BLADHOOGTE, new SearchRequest<RequestValueWBool[]>(new RequestValueWBool[2]));
        searchRequestArrayData.put(ERequestArrayData.BLOEIHOOGTE, new SearchRequest<RequestValueWBool[]>(new RequestValueWBool[2]));
        searchRequestArrayData.put(ERequestArrayData.VLINDERVRIENDELIJK, new SearchRequest<RequestValueWBool[]>(new RequestValueWBool[3]));
        searchRequestArrayData.put(ERequestArrayData.BIJVRIENDELIJK, new SearchRequest<RequestValueWBool[]>(new RequestValueWBool[3]));
        searchRequestArrayData.put(ERequestArrayData.GEUREND, new SearchRequest<RequestValueWBool[]>(new RequestValueWBool[3]));
        searchRequestArrayData.put(ERequestArrayData.EETBAAR, new SearchRequest<RequestValueWBool[]>(new RequestValueWBool[3]));
        searchRequestArrayData.put(ERequestArrayData.KRUIDGEBRUIK, new SearchRequest<RequestValueWBool[]>(new RequestValueWBool[3]));
        searchRequestArrayData.put(ERequestArrayData.VORSTGEVOELIG, new SearchRequest<RequestValueWBool[]>(new RequestValueWBool[3]));

        for (Map.Entry<ERequestArrayData, SearchRequest<RequestValueWBool[]>> entry : searchRequestArrayData.entrySet()) {
            ERequestArrayData e = entry.getKey();
            SearchRequest<RequestValueWBool[]> v = entry.getValue();
            for (int i = 0; i < v.Value().length; i++) {
                v.Value()[i] = new RequestValueWBool();
            }
        }
    }

}
