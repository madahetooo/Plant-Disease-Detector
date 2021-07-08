package com.apps.smartgreenhouse;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.mqtt5.Mqtt5BlockingClient;

import org.eclipse.paho.android.service.MqttAndroidClient;

import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class MainActivity extends Fragment implements View.OnClickListener {

    TextView tvTempAndHumid, tvSmoke, tvTankLevel, tvPHLevel, tvMoisture, tvLDR;
    Button btnTempAndHumid, btnSmoke, btnTankLevel, btnPHLevel, btnMoisTure, btnLDR, btnGoToImageClassification;
    private DatabaseReference databaseReference;
    FirebaseUser mUser;
    Query lastQuery;
    private static final String TAG = "MainActivity";
    Mqtt5BlockingClient client;
    MqttAndroidClient clientAndroid;
    final String host = "ca231f4f1d494fe68b3ede42e3301723.s1.eu.hivemq.cloud";
    final String username = "hfgraduationproject";
    final String password = "HF1234567!hf";
    final String topicStr = "iot";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main, container, false);
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        tvTempAndHumid = v.findViewById(R.id.tvTempAndHumid);
        tvTankLevel = v.findViewById(R.id.tvTankLevel);
        tvPHLevel = v.findViewById(R.id.tvPHLevel);
        tvMoisture = v.findViewById(R.id.tvMoisture);
        tvLDR = v.findViewById(R.id.tvLDR);
        btnTempAndHumid = v.findViewById(R.id.btnTempAndHumid);
        btnTankLevel = v.findViewById(R.id.btnTankLevel);
        btnPHLevel = v.findViewById(R.id.btnPHLevel);
        btnMoisTure = v.findViewById(R.id.btnMoisTure);
        btnLDR = v.findViewById(R.id.btnLDR);
        btnTempAndHumid.setOnClickListener(this);
        btnTankLevel.setOnClickListener(this);
        btnPHLevel.setOnClickListener(this);
        btnMoisTure.setOnClickListener(this);
        btnLDR.setOnClickListener(this);

        //Calling the Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference();
        //  getdata();
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
        return v;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnTempAndHumid:
                DatabaseReference tempAndHumid = databaseReference.child("dht11-readings");
                Query tempAndHumidQuery = tempAndHumid.orderByKey().limitToLast(1);
                tempAndHumidQuery.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<String> tempAndHumidList = new ArrayList<String>();
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            tempAndHumidList.add(postSnapshot.getValue().toString());
                            tvTempAndHumid.setText("" + tempAndHumidList);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });
//                lastQuery = databaseReference.child("dht11-readings").orderByKey().limitToLast(1);
//                lastQuery.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
//                        for (String childKey: map.keySet()){
//                            Map<String, Object> currentObject = (Map<String, Object>) map.get(childKey);
//                            //You can access each variable like so: String variableName = (String) currentLubnaObject.get("INSERT_VARIABLE_HERE"); //data, description, taskid, time, title
//                           //  Log.d(TAG, "Value is: " + currentObject);
//                            tvTempAndHumid.setText("" + currentObject);
//
//                        }
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        Toast.makeText(getContext(), "Fail to get data.", Toast.LENGTH_SHORT).show();
//                        Log.w(TAG, "Failed to read value.", error.toException());
//
//                    }
//                });
                break;

            case R.id.btnTankLevel:
                DatabaseReference tankLevel = databaseReference.child("ultrasonic readings");
                Query TankQuery = tankLevel.orderByKey().limitToLast(1);
                TankQuery.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<String> TankList = new ArrayList<String>();
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            TankList.add(postSnapshot.getValue().toString());
                            tvTankLevel.setText("" + TankList);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });

                break;
//            case R.id.btnPHLevel:
//                lastQuery = databaseReference.child("ldr - readings").orderByKey().limitToLast(1);
//                lastQuery.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                        Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
//                        for (String childKey: map.keySet()){
//                            Map<String, Object> currentLubnaObject = (Map<String, Object>) map.get(childKey);
//                            //You can access each variable like so: String variableName = (String) currentLubnaObject.get("INSERT_VARIABLE_HERE"); //data, description, taskid, time, title
//                            Log.d(TAG, "Value is: " + currentLubnaObject);
//                            tvPHLevel.setText("" + currentLubnaObject);
//
//                        }
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        Toast.makeText(MainActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
//                        Log.w(TAG, "Failed to read value.", error.toException());
//
//                    }
//                });
//                break;
            case R.id.btnMoisTure:
                DatabaseReference moistureReadings = databaseReference.child("moisture-readings");
                Query moistureReadingsQuery = moistureReadings.orderByKey().limitToLast(1);
                moistureReadingsQuery.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<String> moistureReadingsList = new ArrayList<String>();
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            moistureReadingsList.add(postSnapshot.getValue().toString());
                            tvMoisture.setText("" + moistureReadingsList);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }

                });
                break;
            case R.id.btnLDR:
                client.publishWith()
                        .topic("iot")
                        .payload(UTF_8.encode("10"))
                        .send();
                DatabaseReference ldrReadings = databaseReference.child("LDR - readings");
                Query ldrQuery = ldrReadings.orderByKey().limitToLast(1);
                ldrQuery.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<String> ldrReadingsList = new ArrayList<String>();
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            ldrReadingsList.add(postSnapshot.getValue().toString());
                            tvLDR.setText("" + ldrReadingsList);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }

                });
                break;


        }
    }
}


//    private void getdata() {
//        lastQuery.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String address =snapshot.child("dh11_value").getValue(String.class);
//               // tvTempAndHumid.setText(address);
//                Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
//                Log.d(TAG, "Value is: " + map);
//                tvTempAndHumid.setText("" + map);
//
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(MainActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
//                Log.w(TAG, "Failed to read value.", error.toException());
//
//            }
//        });
//    }