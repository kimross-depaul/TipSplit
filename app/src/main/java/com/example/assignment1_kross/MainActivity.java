package com.example.assignment1_kross;

import static android.widget.Toast.*;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    int tipPercent = 0;

    //TextView output1 = findViewById(R.id.textViewWhatever)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void rdoGroupTipPercent_click(View v) {
        //((RadioButton)v).setChecked(false) - unselects the radio if the bill is empty
        Log.d(TAG, "-----------HEY!!!!!!!!__________");
        if (v.getId() == R.id.radioButton12) {
            tipPercent = 12;
        }else if (v.getId() == R.id.radioButton15) {
            tipPercent = 15;
        }else if (v.getId() == R.id.radioButton18) {
            tipPercent = 18;
        }else if (v.getId() == R.id.radioButton20) {
            tipPercent = 20;
        }else {
            String label = (String) ((RadioButton)v).getText();
            //Log.d(TAG, "TipPercent:  Unexpected Tip Percent Selected");
        }
        makeText(this, String.format("Radio was selected: %2d", tipPercent), LENGTH_SHORT).show();
        Log.d(TAG, String.format("Selected radio %d",tipPercent ));
    }
    public void calculateAmounts() {
        EditText txtTotal = findViewById(R.id.txtTotal);
        TextView calcTipAmount = findViewById(R.id.calcTipAmount);
        TextView calcTotalWithTip = findViewById(R.id.calcTotalWTip);
        EditText txtNumPeople = findViewById(R.id.txtPeople);
        TextView calcTotalPerPerson = findViewById(R.id.calctotalPerPerson);
        TextView calcOverage = findViewById(R.id.calcOverage);


    }
    //private get
}