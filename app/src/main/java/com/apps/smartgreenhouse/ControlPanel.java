package com.apps.smartgreenhouse;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.mqtt5.Mqtt5BlockingClient;

import org.eclipse.paho.android.service.MqttAndroidClient;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ControlPanel extends Fragment {
    Switch btnOpenWaterPump, btnTurnLight, btnTurnFan, btnTurnHeater;
    Button btnRunFullCheck;
    Mqtt5BlockingClient client;
    MqttAndroidClient clientAndroid;
    private static final String TAG = "ControlPanel";
    final String host = "ca231f4f1d494fe68b3ede42e3301723.s1.eu.hivemq.cloud";
    final String username = "hfgraduationproject";
    final String password = "HF1234567!hf";
    final String topicStr = "iot";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_control_panel, container, false);
        btnOpenWaterPump = v.findViewById(R.id.btnOpenWaterPump);
        btnTurnLight = v.findViewById(R.id.btnTurnLight);
        btnTurnFan = v.findViewById(R.id.btnTurnFan);
        btnTurnHeater = v.findViewById(R.id.btnTurnHeater);
        btnRunFullCheck = v.findViewById(R.id.btnRunFullCheck);

        btnTurnLight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                if (isChecked) {
                    Toast.makeText(getContext(), "Turning Light On", Toast.LENGTH_LONG).show();
                    client.publishWith()
                            .topic("iot")
                            .payload(UTF_8.encode("1"))
                            .send();
                } else {
                    Toast.makeText(getContext(), "Turning Light Off", Toast.LENGTH_LONG).show();
                    client.publishWith()
                            .topic("iot")
                            .payload(UTF_8.encode("2"))
                            .send();
                }
            }
        });
        btnOpenWaterPump.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                if (isChecked) {
                    client.publishWith()
                            .topic("iot")
                            .payload(UTF_8.encode("7"))
                            .send();
                    Toast.makeText(getContext(), "Water Pump On", Toast.LENGTH_LONG).show();

                } else {
                    client.publishWith()
                            .topic("iot")
                            .payload(UTF_8.encode("8"))
                            .send();
                    Toast.makeText(getContext(), "Water Pump Off", Toast.LENGTH_LONG).show();

                }
            }
        });
        btnTurnFan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                if (isChecked) {
                    client.publishWith()
                            .topic("iot")
                            .payload(UTF_8.encode("3"))
                            .send();
                    Toast.makeText(getContext(), "Fan On", Toast.LENGTH_LONG).show();

                } else {
                    client.publishWith()
                            .topic("iot")
                            .payload(UTF_8.encode("4"))
                            .send();
                    Toast.makeText(getContext(), "Fan Off", Toast.LENGTH_LONG).show();

                }
            }
        });
        btnTurnHeater.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                if (isChecked) {
                    client.publishWith()
                            .topic("iot")
                            .payload(UTF_8.encode("5"))
                            .send();
                    Toast.makeText(getContext(), "Heater On", Toast.LENGTH_LONG).show();

                } else {
                    client.publishWith()
                            .topic("iot")
                            .payload(UTF_8.encode("6"))
                            .send();
                    Toast.makeText(getContext(), "Heater Off", Toast.LENGTH_LONG).show();

                }
            }
        });
        btnRunFullCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client.publishWith()
                        .topic("iot")
                        .payload(UTF_8.encode("9"))
                        .send();
                Toast.makeText(getContext(), "FULL CHECK UP SENT TO TELEGRAM BOT ", Toast.LENGTH_LONG).show();
            }
        });

//        String clientId = MqttClient.generateClientId();
//         clientAndroid =
//                new MqttAndroidClient(this.getContext(), host,
//                        clientId);
//        MqttConnectOptions options = new MqttConnectOptions();
//        options.setUserName(username);
//        options.setPassword(password.toCharArray());
//
//        try {
//            IMqttToken token = clientAndroid.connect(options);
//            token.setActionCallback(new IMqttActionListener() {
//                @Override
//                public void onSuccess(IMqttToken asyncActionToken) {
//                    // We are connected
//                    Toast.makeText(getContext(), "Success Connection", Toast.LENGTH_SHORT).show();
//                    Log.d(TAG, "onSuccess");
//                }
//
//                @Override
//                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
//                    // Something went wrong e.g. connection timeout or firewall problems
//                    Toast.makeText(getContext(), "Failed Connection", Toast.LENGTH_SHORT).show();
//                    Log.d(TAG, "onFailure");
//
//                }
//            });
//        } catch (MqttException e) {
//            e.printStackTrace();
//        }


//        //create an MQTT client
        client = MqttClient.builder()
                .useMqttVersion5()
                .serverHost(host)
                .serverPort(8883)
                .sslWithDefaultConfig()
                .buildBlocking();

        //connect to HiveMQ Cloud with TLS and username/pw
        client.connectWith()
                .simpleAuth()
                .username(username)
                .password(UTF_8.encode(password))
                .applySimpleAuth()
                .send();

        //System.out.println("Connected successfully");

        //subscribe to the topic "my/test/topic"
        client.subscribeWith()
                .topicFilter("iot")
                .send();
//
//        //set a callback that is called when a message is received (using the async API style)
//        client.toAsync().publishes(ALL, publish -> {
//            System.out.println("Received message: " + publish.getTopic() + " -> " + UTF_8.decode(publish.getPayload().get()));
//
//            //disconnect the client after a message was received
//            client.disconnect();
//        });

        //publish a message to the topic "my/test/topic"
//        client.publishWith()
//                .topic("iot")
//                .payload(UTF_8.encode("Hello"))
//                .send();

//        client.publishWith()
//                .topic("iot")
//                .payload(UTF_8.encode("1"))
//                .send();
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        client.publishWith()
//                .topic("iot")
//                .payload(UTF_8.encode("2"))
//                .send();
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        return v;

    }
}

//
//<service android:name="org.eclipse.paho.android.service.MqttService" >
//</service>