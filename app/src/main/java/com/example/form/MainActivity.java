package com.example.form;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

import static android.app.DatePickerDialog.OnDateSetListener;

public class MainActivity extends AppCompatActivity implements OnDateSetListener {

    private Button button;
    private TextView textView;
    private EditText name;
    private EditText username;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = (TextView) findViewById(R.id.date);
        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.containsKey(Constants.KEY_TEXTVIEW_TEXT)) {
            textView.setText(savedInstanceState.getString(Constants.KEY_TEXTVIEW_TEXT));
        }

        if (savedInstanceState.containsKey(Constants.KEY_NAME)) {
            name.setText(savedInstanceState.getString(Constants.KEY_NAME));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState,outPersistentState);

        outState.putString(Constants.KEY_TEXTVIEW_TEXT, textView.getText().toString());
        outState.putString(Constants.KEY_NAME, name.getText().toString());

    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        LocalDate bday = LocalDate.of(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
        LocalDate today = LocalDate.now();

        String dateString = DateFormat.getDateInstance(DateFormat.SHORT).format(cal.getTime());

        textView = (TextView) findViewById(R.id.date);
        textView.setText(dateString);

        int age = Period.between(today, bday).getYears();

        if(age < 17 ){
            textView.setError("Must be 18 Years or Older");
            return;
        }
    }

    public static boolean emailValid(CharSequence c){
        return !TextUtils.isEmpty(c) && Patterns.EMAIL_ADDRESS.matcher(c).matches();

    }


    public void openSecondActivity(View view){

        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);

        if (name.getText().toString().isEmpty()){
            name.setError("Please Enter Name");
            return;
        }
        if (username.getText().toString().isEmpty()){
            username.setError("Please Enter Username");
            return;
        }
        if(!emailValid(email.getText())){
            email.setError("Enter valid Email");
            return;
        }


        Intent intent = new Intent(this, SecondActivity.class);
        Bundle bundle = new Bundle();

        bundle.putString(Constants.KEY_USER, username.getText().toString());
        intent.putExtras(bundle);
        startActivity(intent);
    }

}