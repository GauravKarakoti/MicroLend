package com.microlend.app.expense;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.microlend.app.R;
import com.microlend.app.model.Expense;

import java.util.List;

public class ExpenseAdapter extends ArrayAdapter<Expense> {
    public ExpenseAdapter(Context context, List<Expense> expenses) {
        super(context, 0, expenses);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Expense expense = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_expense, parent, false);
        }
        TextView textViewAmount = convertView.findViewById(R.id.textViewAmount);
        textViewAmount.setText(String.valueOf(expense.getAmount()));
        return convertView;
    }
}
