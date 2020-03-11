package it.swiftelink.com.vcs_member.mqtt;

public interface MqttMessageCallBack {
    void messageArrived(String topic, String message);

}
