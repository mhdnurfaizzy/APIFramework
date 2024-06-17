package packages;

import BDD.POJO.addPlace;
import BDD.POJO.location;

import java.util.ArrayList;
import java.util.List;

public class TestDatabuild {

    public addPlace addPlacePayload() {

        addPlace aps = new addPlace();

        aps.setAccuracy(50);
        aps.setName("Arindam Dalal");
        aps.setPhone_number("(+91) 983 893 3937");
        aps.setAddress("B-408,Arcadia,Sinhagad Road,Pune,Maharashtra,India");
        aps.setWebsite("http://google.com");
        aps.setLanguage("French-IN");
        List<String> typelist = new ArrayList<String>();
        typelist.add("shoe park");
        typelist.add("shop");
        aps.setTypes(typelist);
        location loc = new location();
        loc.setLat(-38.383494);
        loc.setLng(33.427362);

        aps.setLocation(loc);
        return aps;
    }
}
