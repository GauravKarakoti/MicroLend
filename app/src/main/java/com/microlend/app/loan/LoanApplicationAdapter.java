package com.microlend.app.loan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.microlend.app.R;
import com.microlend.app.model.LoanApplication2;

import java.util.List;

public class LoanApplicationAdapter extends ArrayAdapter<LoanApplication2> {
    public LoanApplicationAdapter(Context context, List<LoanApplication2> loanApplications) {
        super(context, 0, loanApplications);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LoanApplication2 loanApplication = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_loan_application, parent, false);
        }
        TextView textViewAmount = convertView.findViewById(R.id.textViewAmount);
        textViewAmount.setText(String.valueOf(loanApplication.getAmount()));
        return convertView;
    }
}
