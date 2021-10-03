package com.example.assignment1_kross;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.EditText;

class Calculator {
    private Double dblTip;
    private Double dblTotalWithTip;
    private Double perPerson;
    int intPeople;

    public Calculator() {}

    public void calculateTip(EditText txtDouble, Integer intTip) {
        Double d;
        try {
            d = Double.parseDouble(txtDouble.getText().toString());
        }catch (NumberFormatException nex) {
            dblTip = null;
            dblTotalWithTip = null;
            return;
        }
        dblTip = d * (intTip * .01f);
        dblTotalWithTip = dblTip + d;
    }
    public void splitBill(EditText txtNumPeople) {
        try {
            intPeople = Integer.parseInt(txtNumPeople.getText().toString());
            if (intPeople > 0 && dblTotalWithTip != null) {
                perPerson = roundUp(dblTotalWithTip / intPeople);
            }else {
                perPerson = null;
            }
        }catch(NumberFormatException nex) {
            perPerson = null;
        }
    }
    private Double roundUp(Double d) {
        return Math.round(d * 10.0) / 10.0;
    }
    public String getTip(String format) {
        return dblTip != null ? String.format(format, dblTip) : "";
    }
    public String getTotalWithTip(String format) {
        return dblTotalWithTip != null ? String.format(format, dblTotalWithTip) : "";
    }
    public String getBillPerPerson(String format) {
        return perPerson != null ? String.format(format, perPerson) : "";
    }
    public String getOverage(String format) {
        Double ovg = calcOverage();
        return ovg != null ? String.format(format, ovg) : "";
    }
    Double calcOverage() {
        try {
            return (perPerson * intPeople) - dblTotalWithTip;
        }catch (Exception ex) {
            return null;
        }
    }


    public void saveState(Bundle outState) {
        outState.putDouble("dblTip", dblTip);
        outState.putDouble("dblTotalWithTip", dblTotalWithTip);
        outState.putDouble("perPerson", perPerson);
        outState.putInt("intPeople", intPeople);
    }

    public void restoreState(Bundle savedInstanceState) {
        dblTip = savedInstanceState.getDouble("dblTip");
        dblTotalWithTip = savedInstanceState.getDouble("dblTotalWithTip");
        perPerson = savedInstanceState.getDouble("perPerson");
        intPeople = savedInstanceState.getInt("intPeople");
    }
}