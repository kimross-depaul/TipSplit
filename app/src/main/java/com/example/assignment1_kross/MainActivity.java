package com.example.assignment1_kross;

import static android.widget.Toast.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String FORMAT = "$%.2f";
    Integer tipPercent = 0;
    EditText txtTotal;
    TextView calcTipAmount;
    TextView calcTotalWithTip;
    EditText txtNumPeople;
    TextView calcTotalPerPerson;
    TextView calcOverage;
    RadioGroup rdoTip;
    Calculator calculator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtTotal = findViewById(R.id.txtTotal);
        calcTipAmount = findViewById(R.id.calcTipAmount);
        calcTotalWithTip = findViewById(R.id.calcTotalWTip);
        txtNumPeople = findViewById(R.id.txtPeople);
        calcTotalPerPerson = findViewById(R.id.calctotalPerPerson);
        calcOverage = findViewById(R.id.calcOverage);
        rdoTip = findViewById(R.id.radioGroup);
        calculator = new Calculator();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        saveState(txtTotal, "txtTotal", outState);
        saveState(txtNumPeople, "txtNumPeople", outState);
        if (tipPercent != null) outState.putInt("tipPercent", tipPercent);
        calculator.saveState(outState);
        super.onSaveInstanceState(outState);
    }

    private void saveState(EditText editText, String name, Bundle outState) {
       outState.putString(name, editText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        calculator.restoreState(savedInstanceState);

        txtTotal.setText(savedInstanceState.getString("txtTotal"));
        txtNumPeople.setText(savedInstanceState.getString("txtNumPeople"));
        tipPercent = savedInstanceState.getInt("tipPercent");
        calculateAmounts();
    }

    public void rdoGroupTipPercent_click(View v) {
        if (txtTotal.getText().toString().equals("")) {
            ((RadioButton) v).setChecked(false); // Unselects the Radio if the bill is empty
            tipPercent = null;
            return;
        }
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
        calculateAmounts();
    }
    public void btnGo_clicked(View v) {
        calculateAmounts();
    }
    public void btnClear_clicked(View v) {
        txtTotal.setText("");
        txtNumPeople.setText("");
        rdoTip.clearCheck();
        tipPercent = null;
        calculateAmounts();
    }
    public void calculateAmounts() {
        calculator.calculateTip(txtTotal, tipPercent);
        calculator.splitBill(txtNumPeople);

        calcTipAmount.setText(calculator.getTip(FORMAT));
        calcTotalWithTip.setText(calculator.getTotalWithTip(FORMAT));
        calcTotalPerPerson.setText(calculator.getBillPerPerson(FORMAT));
        calcOverage.setText(calculator.getOverage(FORMAT));
        validateFields();
    }
    private void validateFields() {
        String t = txtTotal.getText().toString();
        String p = txtNumPeople.getText().toString();
        if (t.equals("") && p.equals("")) {
            return;
        }
        //Check for a valid bill total
        try {
            Double d = Double.parseDouble(t);

            if (d <= 0 || d > 999999) {
                txtTotal.setError("Invalid Value!");
            }
        } catch (Exception ex) {
            txtTotal.setError("Invalid Value!");
        }
        //Check for a valid no. people
        try {
            int n = Integer.parseInt(p);

            if (n <= 0 || n > 9999) {
                txtNumPeople.setError("Invalid Value!");
            }

        } catch (Exception ex) {
            txtNumPeople.setError("Invalid Value!");
        }
    }


}
