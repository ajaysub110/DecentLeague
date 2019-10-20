package c.hackathon.decentralisedleague.RecyclerModels;

public class NewsModel {

    String title;
    String desc;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    String appId;

    public NewsModel(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public NewsModel(String title, String desc,String appId) {
        this.title = title;
        this.desc = desc;
        this.appId = appId;
    }


}
