package packages;

public enum APIResources {

    AddPlaceAPI("/maps/api/place/add/json"),
    GetPlaceAPI("/maps/api/place/get/json"),
    DeletePlaceAPI("/maps/api/place/delete/json");
    private String resource;

    APIResources(String resources) {
        this.resource = resources;
    }

    public String getResource() {
        return resource;
    }
}
