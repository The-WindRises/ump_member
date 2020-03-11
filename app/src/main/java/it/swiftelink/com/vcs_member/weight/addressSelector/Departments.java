package it.swiftelink.com.vcs_member.weight.addressSelector;

public class Departments implements CityInterface {


    private String Name;
    private String id;
    private int pos;


    public void setName(String name) {
        Name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getCityName() {
        return Name;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    @Override
    public int getPos() {
        return pos;
    }
}
