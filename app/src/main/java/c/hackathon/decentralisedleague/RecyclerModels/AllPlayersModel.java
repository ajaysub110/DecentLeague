package c.hackathon.decentralisedleague.RecyclerModels;

public class AllPlayersModel {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAssets() {
        return assets;
    }

    public void setAssets(String assets) {
        this.assets = assets;
    }

    public AllPlayersModel(String name, String party, String age, String area, String assets,String id,String origin) {
        this.name = name;
        this.party = party;
        this.age = age;
        this.area = area;
        this.assets = assets;
        this.id = id;
        this.origin = origin;
    }

    String name;
    String party;
    String age;
    String area;
    String assets;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    String origin;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AllPlayersModel(){

    }

    String id;
}
