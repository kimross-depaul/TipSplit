package com.example.assignment1_kross;

import android.util.Log;
import android.widget.EditText;

class Calculator {
    private Double dblTip;
    private Double dblTotalWithTip;
    private Double perPerson;
    int intPeople;

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
    public Double splitBill(EditText txtNumPeople) {
        try {
            intPeople = Integer.parseInt(txtNumPeople.getText().toString());
            if (intPeople > 0 && dblTotalWithTip != null) {
                perPerson = roundUp(dblTotalWithTip / intPeople);
                return perPerson;
            }
        }catch(NumberFormatException nex) {
            return null;
        }
        return null;
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


}