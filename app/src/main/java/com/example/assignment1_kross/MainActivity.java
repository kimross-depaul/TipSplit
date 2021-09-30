package com.example.assignment1_kross;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    String TAG = "";
    int tipPercent = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void rdoGroupTipPercent_click(View v) {
        //((RadioButton)v).setChecked(false) - unselects the radio if the bill is empty

        if (v.getId() == R.id.radioButton) {
            tipPercent = 5;
        }else if (v.getId() == R.id.radioButton2) {
            tipPercent = 10;
        }else if (v.getId() == R.id.radioButton3) {
            tipPercent = 15;
        }else if (v.getId() == R.id.radioButton) {
            tipPercent = 25;
        }else {
            String label = (String) ((RadioButton)v).getText();
            //Log.d(TAG, "TipPercent:  Unexpected Tip Percent Selected");
        }
    }
}