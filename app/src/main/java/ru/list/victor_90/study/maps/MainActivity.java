package ru.list.victor_90.study.maps;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txt_long,txt_lat, txt_time,txt_date;
    MyLocationListener listener;
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_lat = (TextView) findViewById(R.id.txt_lat);
        txt_long = (TextView) findViewById(R.id.txt_long);
        txt_date = (TextView) findViewById(R.id.txt_date);
        txt_time = (TextView) findViewById(R.id.txt_time);

        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.btn_date).setOnClickListener(this);
        findViewById(R.id.btn_time).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listener = new MyLocationListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        listener.disableLocationProvider();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.button:{

                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("dialog Title")
                        .setMessage("Требуется подключение к сети")
                        .setIcon(R.drawable.ic_airplanemode_off_24dp)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    location = listener.getLocation();
                                    txt_lat.setText(String.valueOf(location.getLatitude()));
                                    txt_long.setText(String.valueOf(location.getLongitude()));
                                } catch (Exception e) {
                                    Log.d("Что-то случилось", "невозможно получить координаты, преверье соединение");
                                }
                            }
                        })
                        .create();
                dialog.show();
                break;
            }
            case R.id.btn_date:{
                new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        txt_date.setText(monthOfYear + "-" + dayOfMonth + "-" + year);
                    }
                },0,0,0).show();
                break;
            }
            case R.id.btn_time:{
                new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        txt_time.setText(hourOfDay + ":" + minute);
                    }
                },0,0,true).show();
                break;
            }
        }


    }
}
