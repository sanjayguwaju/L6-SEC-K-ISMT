package com.example.dbwithlandr;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity {
    EditText Efname, Elname, Eemail, Epassword, Ecpassword, Ephone;
    Button rbtn;
    TextView redirecttologin;
    String gender;
    RadioButton rmale, rfemale, rother;
    Spinner countrydrp;
    String[] country = {"Nepal", "India", "Japan", "USA", "China"};
    // For pop up
    ConstraintLayout con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        database db = new database(this);

        //For input value
        Efname = findViewById(R.id.rfirstname);
        Elname = findViewById(R.id.rlastname);
        Ephone = findViewById(R.id.rnumber);
        Eemail = findViewById(R.id.remail);
        Epassword = findViewById(R.id.rpassword);
        Ecpassword = findViewById(R.id.rconfirmpassword);


        //For snackbar popup
        con = findViewById(R.id.constraintLayout);

        //For Registration and Login redirection Button
        rbtn = findViewById(R.id.btnRegister);
        redirecttologin = findViewById(R.id.rsingup);

        //For radio button
        rmale = findViewById(R.id.maler);
        rfemale =findViewById(R.id.femaler);
        rother = findViewById(R.id.otherr);


        //Arrayadpater for dropdown list
        countrydrp = findViewById(R.id.rcountry);
        ArrayAdapter<String> adp = new ArrayAdapter<>(RegisterActivity.this, R.layout.dropdown_design, country);
        adp.setDropDownViewResource(R.layout.dropdown_design);
        countrydrp.setAdapter(adp);


        //Clicklistner add for register button
        rbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //define string for input
                String fname = Efname.getText().toString();
                String lname = Elname.getText().toString();
                String email = Eemail.getText().toString();
                String phone = Ephone.getText().toString();
                String password = Epassword.getText().toString();
                String cpassword = Ecpassword.getText().toString();
                String country = String.valueOf(countrydrp.getSelectedItem());

                //Gender check
                if(rmale.isChecked()){
                    gender = rmale.getText().toString();
                }
                else if(rfemale.isChecked()) {
                    gender = rfemale.getText().toString();
                }
                else {
                    gender = rother.getText().toString();
                }


                //Emtpy value checked
               if (fname.isEmpty() || lname.isEmpty() || email.isEmpty() || password.isEmpty() || cpassword.isEmpty() ) {
                    Toast.makeText(RegisterActivity.this, "Enter all data", Toast.LENGTH_SHORT).show();
                }

                else {
                    //password and confirm password checked
                    if (password.equals(cpassword)){


                        //email already exist or not checked
                        boolean checkuser = db.checkemail(email);


                        if (checkuser == false) {
                            Toast.makeText(RegisterActivity.this, "user already exist", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            //insert data to database

                          boolean i = db.insert(fname, lname, email, phone, country, password, gender );


                          if(i == true) {
                              Toast.makeText(RegisterActivity.this, "successfully update", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                          }
                          else {
                              Toast.makeText(RegisterActivity.this, "Failed to update", Toast.LENGTH_SHORT).show();
                          }
                        }

                    } else{

                        Toast.makeText(RegisterActivity.this, "Email address no valid", Toast.LENGTH_SHORT).show();
                }}
            }
        });


        redirecttologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar snackbar = Snackbar.make(con, "Redirecting to Login Page", Snackbar.LENGTH_LONG);
                snackbar.show();
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
    }
}
