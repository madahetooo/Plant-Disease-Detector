package com.apps.smartgreenhouse;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SetupActivity extends Fragment implements AdapterView.OnItemSelectedListener {
    String[] timeZones = { "Africa/Cairo +02:00", "Europe/Berlin +02:00", "US/Arizona −05:00", "Australia/Sydney +11:00"};
    String[] plant = { "Bell Pepper", "Carrot", "Tomato"};
    EditText etEmailAddress;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_setup, container, false);
        Spinner spinnerTimePicker = v.findViewById(R.id.spinnerTimePicker);
        etEmailAddress = v.findViewById(R.id.etEmaillAddress);



        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter arrayAdapterTimePicker = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,timeZones);
        arrayAdapterTimePicker.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinnerTimePicker.setAdapter(arrayAdapterTimePicker);
        spinnerTimePicker.setOnItemSelectedListener(this);
        Spinner spinnerPlantPicker = v.findViewById(R.id.spinnerPlantPicker);
        ArrayAdapter arrayAdapterPlantPicker = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,plant);
        arrayAdapterPlantPicker.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinnerPlantPicker.setAdapter(arrayAdapterPlantPicker);
        spinnerPlantPicker.setOnItemSelectedListener(this);
        return v;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spin = (Spinner)parent;
        Spinner spin2 = (Spinner)parent;
        if(spin.getId() == R.id.spinnerTimePicker)
        {
            Toast.makeText(getContext(),timeZones[position] +" Selected", Toast.LENGTH_LONG).show();
        }
        if(spin2.getId() == R.id.spinnerPlantPicker)
        {
            Toast.makeText(getContext(),plant[position] +" Selected", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}