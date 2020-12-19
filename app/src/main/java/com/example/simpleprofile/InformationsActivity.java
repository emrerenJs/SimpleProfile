package com.example.simpleprofile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.simpleprofile.Models.Profile;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class InformationsActivity extends AppCompatActivity {

    private EditText firstnameET,lastnameET,birthdayET;
    private RadioButton maleRB,femaleRB;
    private CheckBox hobbiesTravelCB,hobbiesReadingCB,hobbiesGamingCB;
    private Spinner citiesSpinner;

    private void fillCitiesCombobox(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.cities,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citiesSpinner.setAdapter(adapter);
    }

    private void bindViews(){
        firstnameET = findViewById(R.id.firstnameET);
        lastnameET = findViewById(R.id.lastnameET);
        birthdayET = findViewById(R.id.birthdayET);
        maleRB = findViewById(R.id.maleRB);
        femaleRB = findViewById(R.id.femaleRB);
        hobbiesTravelCB = findViewById(R.id.hobbiesTravelCB);
        hobbiesReadingCB = findViewById(R.id.hobbiesReadingCB);
        hobbiesGamingCB = findViewById(R.id.hobbiesGamingCB);
        citiesSpinner = findViewById(R.id.citiesSpinner);
    }

    private boolean isFieldsEmpty(){
        return  firstnameET.getText().toString().trim().equals("") ||
                lastnameET.getText().toString().trim().equals("") ||
                birthdayET.getText().toString().trim().equals("") ||
                citiesSpinner.getSelectedItem().toString().equals("Seçiniz..") ||
                (!maleRB.isChecked() && !femaleRB.isChecked()) ||
                (!hobbiesReadingCB.isChecked() && !hobbiesTravelCB.isChecked() && !hobbiesGamingCB.isChecked());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informations);
        bindViews();
        birthdayET.setInputType(InputType.TYPE_NULL);
        fillCitiesCombobox();
    }

    private ArrayList<String> getHobbies(){
        ArrayList<String> hobbies = new ArrayList<>();
        ViewGroup layout = findViewById(R.id.hobbiesGroup);
        CheckBox cb = null;
        for(int i = 0; i < layout.getChildCount(); i++){
            if(layout.getChildAt(i) instanceof CheckBox){
                cb = ((CheckBox)layout.getChildAt(i));
                if(cb.isChecked())
                    hobbies.add(cb.getText().toString());
            }
        }
        cb = null;
        return hobbies;
    }

    private int getAge(){
        int year = Integer.parseInt(birthdayET.getText().toString().substring(0,4));
        int recent = Calendar.getInstance().get(Calendar.YEAR);
        return recent - year;
    }

    public void loginBTNOnClick(View view){
        if(!isFieldsEmpty())
        {
            Toast.makeText(this, "Welcome " + firstnameET.getText() + " " + lastnameET.getText(), Toast.LENGTH_SHORT).show();
            Profile profile = new Profile();
            profile.setFirstname(firstnameET.getText().toString());
            profile.setLastname(lastnameET.getText().toString());
            profile.setCity(citiesSpinner.getSelectedItem().toString());
            profile.setGender(maleRB.isChecked());
            profile.setHobbies(getHobbies());
            profile.setAge(getAge());
            Intent intent = new Intent(this,ProfileActivity.class);
            intent.putExtra("Profile",profile);
            startActivity(intent);
        }
        else{
            new AlertDialog.Builder(this)
                    .setTitle("Boş alan var!")
                    .setMessage("Giriş yapabilmek için tüm alanları doldurmak zorundasınız!")
                    .setNeutralButton(android.R.string.ok,null)
                    .show();
        }
    }
    private void showDateDialog(){
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                birthdayET.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        new DatePickerDialog(this,
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
    public void birthdayETOnClick(View view){
        showDateDialog();
    }

}