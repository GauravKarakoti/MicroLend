package com.microlend.app.loan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.microlend.app.R;

import java.util.ArrayList;

public class LoanAdapter extends RecyclerView.Adapter<LoanAdapter.LoanViewHolder> {
    private ArrayList<LoanApplication> loanApplications;

    public LoanAdapter(ArrayList<LoanApplication> loanApplications) {
        this.loanApplications = loanApplications;
    }

    @NonNull
    @Override
    public LoanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_loan_application, parent, false);
        return new LoanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanViewHolder holder, int position) {
        LoanApplication loanApplication = loanApplications.get(position);
        holder.textViewLoanAmount.setText("Loan Amount: " + loanApplication.getAmount());
    }

    @Override
    public int getItemCount() {
        return loanApplications.size();
    }

    static class LoanViewHolder extends RecyclerView.ViewHolder {
        TextView textViewLoanAmount;

        LoanViewHolder(View itemView) {
            super(itemView);
            textViewLoanAmount = itemView.findViewById(R.id.textViewLoanAmount);
        }
    }
}
