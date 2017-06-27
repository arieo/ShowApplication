package show.example.com.showapplication.Entities;

/**
 * Created by Yakov Shechter on 30/05/2017.
 */

public class Action {

    private double actPrice;
    private String actStart;
    private String actEnd;
    private String actState;
    private String actType;
    private String actDescription;
    //private ActDescription actDescription;
    private String businessID;
    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    private int thumbnail;

    public String  getActDescription() {
        return actDescription;
    }

    public void setActDescription(String actDescription) {
        this.actDescription = actDescription;
    }

    public String getBusinessID() {
        return businessID;
    }

    public void setBusinessID(String businessID) {
        this.businessID = businessID;
    }



    public void setActPrice(double actPrice) {
        this.actPrice = actPrice;
    }

    public double getActPrice() {
        return actPrice;
    }

    public String getActStart() {
        return actStart;
    }

    public void setActStart(String actStart) {
        this.actStart = actStart;
    }

    public String getActEnd() {
        return actEnd;
    }

    public void setActEnd(String actEnd) {
        this.actEnd = actEnd;
    }

    public String getActState() {
        return actState;
    }

    public void setActState(String actState) {
        this.actState = actState;
    }

    public String getActType() {
        return actType;
    }

    public void setActType(String actType) {
        this.actType = actType;
    }

}
