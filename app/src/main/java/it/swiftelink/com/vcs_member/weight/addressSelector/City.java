package it.swiftelink.com.vcs_member.weight.addressSelector;

/**
 * Author: Blincheng.
 * Date: 2017/5/9.
 * Description:
 */

public class City implements CityInterface{
    private String Name;
    private int pos;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    @Override
    public String getCityName() {
        return Name;
    }
}
