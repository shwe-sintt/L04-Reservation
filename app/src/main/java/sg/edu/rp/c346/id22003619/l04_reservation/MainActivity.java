package sg.edu.rp.c346.id22003619.l04_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
EditText name;

EditText number;
EditText pax;
DatePicker dp;
TimePicker tp;
CheckBox smokeYes;
Button btnConfirm;
Button btnReset;
TextView details;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.editTextName);
        number=findViewById(R.id.editTextPhone);
        pax=findViewById(R.id.editTextPax);
        dp=findViewById(R.id.datePicker);
        tp=findViewById(R.id.timePicker);
        smokeYes=findViewById(R.id.checkBoxSmoking);
        btnConfirm=findViewById(R.id.buttonConfirm);
        btnReset=findViewById(R.id.buttonReset);
        details=findViewById(R.id.textViewDetails);

        tp.setIs24HourView(true);
        tp.setCurrentHour(19);
        tp.setCurrentMinute(30);
        dp.updateDate(2023,05,01);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                number.setText("");
                pax.setText("");
                smokeYes.setChecked(false);
                tp.setCurrentHour(19);
                tp.setCurrentMinute(30);
                dp.updateDate(2023,05,01);

            }

        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nameCheck= name.getText().toString().trim().length();
                int numCheck=number.getText().toString().trim().length();
                int paxCheck=pax.getText().toString().trim().length();
                if(nameCheck==0||numCheck==0||paxCheck==0){
                    Toast.makeText(MainActivity.this, "Please input necessary fields", Toast.LENGTH_SHORT).show();
                }else{

                    new CountDownTimer(3500, 1000) {

                        String theMessage=String.format("Full Name: %s\nPhone Number: %s\nGroup Size: %s\nDate: %d/%d/%d\nTime: %d:%d\nSmoking Area: %s", name.getText(), number.getText(), pax.getText(), dp.getDayOfMonth(), dp.getMonth()+1, dp.getYear(), tp.getCurrentHour(), tp.getCurrentMinute(), smokeYes.isChecked());

                        public void onTick(long millisUntilFinished) {

                            details.setText(theMessage);
                        }

                        public void onFinish() {

                            details.setText("");
                        }
                    }.start();

                }



            }
        });
        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if(tp.getCurrentHour()<8){
                    tp.setCurrentHour(8);
                    tp.setCurrentMinute(00);
                }else if(tp.getCurrentHour()>20) {
                    tp.setCurrentHour(20);
                    tp.setCurrentMinute(00);
                }
                Toast.makeText(MainActivity.this, ("Time is "+tp.getCurrentHour()+":"+tp.getCurrentMinute()), Toast.LENGTH_SHORT).show();

            }
        });
        dp.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Toast.makeText(MainActivity.this, ("Date is "+dp.getDayOfMonth()+"/"+(dp.getMonth()+1)+"/"+dp.getYear()), Toast.LENGTH_SHORT).show();
            }
        });

    }
}