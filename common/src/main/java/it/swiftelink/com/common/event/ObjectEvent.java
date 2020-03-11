package it.swiftelink.com.common.event;

/**
 * Created by Administrator on 2018/11/28.
 */

public class ObjectEvent {

    private int type;
    private Object obj;
    private int cardType;


    public ObjectEvent(int type, Object obj,int cardType) {
        this.type = type;
        this.obj = obj;
        this.cardType=cardType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }
}
