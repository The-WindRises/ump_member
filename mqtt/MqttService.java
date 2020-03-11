package it.swiftelink.com.vcs_member.mqtt;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.IBinder;
import androidx.annotation.Nullable;
import android.util.Log;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.utils.Constants;
import it.swiftelink.com.common.utils.Utils;
import it.swiftelink.com.vcs_member.App;

public class MqttService extends Service implements NetReceiver.NetStatusMonitor {
    private static final String TAG = "MqttService";

    private String host = "tcp://mqtt.swiftelink.com:1883";

    private String userName = "admin";

    private String passWord = "public";

//    private String topic = "productionvcs_" + App.getSPUtils().getString(Common.SPApi.LOGINID);

//    private String topic = "uatvcs_" + App.getSPUtils().getString(Common.SPApi.LOGINID);


    private static MqttAndroidClient client;
    private MqttConnectOptions mqttConnectOptions;
    private MqttMessageCallBack mqttMessageCallBack;
    private NetReceiver netBroadcastReceiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        Log.i(TAG, "onBind: ....");

        return new CustomBinder();

    }

    @Override
    public void onNetChange(boolean netStatus) {
        if (netStatus) {
            doClientConnection();
        }
    }

    public class CustomBinder extends Binder {
        public MqttService getService() {
            return MqttService.this;
        }
    }


    public void setMqttMessageCallBack(MqttMessageCallBack mqttMessageCallBack) {
        this.mqttMessageCallBack = mqttMessageCallBack;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i(TAG, "onCreate: ....");

        registerBroadcastReceiver();
        //客户端
        client = new MqttAndroidClient(App.getInstance(), host, Utils.getClientId());
        //配置连接信息
        mqttConnectOptions = new MqttConnectOptions();
        //是否清除缓存
        mqttConnectOptions.setCleanSession(false);
        //是否重连
        mqttConnectOptions.setAutomaticReconnect(true);
        //设置心跳,60s
        mqttConnectOptions.setKeepAliveInterval(60);
        //超时时间
        mqttConnectOptions.setConnectionTimeout(30);
        init();
    }

    /**
     * 注册网络状态广播
     */
    private void registerBroadcastReceiver() {
        //注册广播
        if (netBroadcastReceiver == null) {
            netBroadcastReceiver = new NetReceiver();
            IntentFilter filter = new IntentFilter();
            filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            registerReceiver(netBroadcastReceiver, filter);
            //设置监听
            netBroadcastReceiver.setStatusMonitor(this);
        }
    }

    private void init() {
        if (mqttConnectOptions != null) {
            mqttConnectOptions.setUserName(userName);
            mqttConnectOptions.setPassword(passWord.toCharArray());
            try {
                if (client != null) {
                    client.setCallback(new MqttCallback() {
                        @Override
                        public void connectionLost(Throwable cause) {
                            Log.i(TAG, "连接丢失重新连接");
                        }

                        @Override
                        public void messageArrived(String topic, MqttMessage message) throws Exception {
                            String str1 = new String(message.getPayload());
                            if (mqttMessageCallBack != null) {
                                mqttMessageCallBack.messageArrived(topic, str1);
                            }
                            Log.i(TAG, "mqtt推送内容Topic=" + topic);
                            Log.i(TAG, "mqtt推送内容:" + str1);
                        }

                        @Override
                        public void deliveryComplete(IMqttDeliveryToken token) {
                        }

                    });
                }

                doClientConnection();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 取消订阅主题
     *
     * @param topic
     */
    public void uSubriceTopic(String topic) {
        try {
            if (client != null) {
                client.unsubscribe(topic, App.getInstance(), new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        Log.i(TAG, "取消订阅成功!");
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                        Log.i(TAG, "取消订阅失败!");
                    }
                });
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /**
     * 订阅主题
     *
     * @param topic
     * @param qos
     */
    public void subriceTopic(String topic, int qos) {

        Log.i(TAG, "subriceTopic: 订阅主题");
        try {
            if (client != null) {
                client.subscribe(topic, qos, App.getInstance(), new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        Log.i(TAG, "订阅成功>>主题:" + asyncActionToken.getTopics()[0]);


                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                        Log.i(TAG, "订阅失败>>主题:" + asyncActionToken.getTopics()[0]);
                    }
                });
            }

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    /**
     * 发送消息
     *
     * @param topic
     * @param mesg
     */
    public void sendMes(String topic, String mesg) {
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setPayload(mesg.getBytes());
        try {

            if (client != null) {
                client.publish(topic, mqttMessage, null, new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        Log.i(TAG, "消息发送成功!");
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                        Log.i(TAG, "消息发送失败");
                    }
                });
            }
        } catch (MqttException e) {
            e.printStackTrace();
            Log.i(TAG, "消息发送失败");
        }
    }


    /**
     * 判断网络是否连接
     */
    private boolean isConnectIsNormal() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            String name = info.getTypeName();
            Log.i(TAG, "MQTT当前网络名称：" + name);
            return true;
        } else {
            Log.i(TAG, "MQTT 没有可用网络");
            return false;
        }
    }

    /**
     * 连接MQTT服务器
     */
    public void doClientConnection() {

        final String topic = Constants.ROOMTOPIC + App.getSPUtils().getString(Common.SPApi.LOGINID);

        Log.i(TAG, "doClientConnection: 连接MQTT服务器");

        if (client != null && !client.isConnected() && isConnectIsNormal()) {
            try {
                client.connect(mqttConnectOptions, new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        Log.i(TAG, "topic" + topic + "连接成功");
                        subriceTopic(topic, 2);
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                        Log.i(TAG, "连接失败,重新连接");
                    }
                });
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        stopSelf();
        try {
            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
        super.onDestroy();
        unregisterReceiver(netBroadcastReceiver);
    }


}
