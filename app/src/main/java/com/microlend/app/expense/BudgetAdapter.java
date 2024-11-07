package com.microlend.app.expense;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.microlend.app.R;
import com.microlend.app.model.Budget;

import java.util.List;

public class BudgetAdapter extends ArrayAdapter<Budget> {
    public BudgetAdapter(Context context, List<Budget> budgets) {
        super(context, 0, budgets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Budget budget = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_budget, parent, false);
        }
        TextView textViewAmount = convertView.findViewById(R.id.textViewAmount);
        textViewAmount.setText(String.valueOf(budget.getAmount()));
        return convertView;
    }
}
