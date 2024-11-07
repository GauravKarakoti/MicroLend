package com.microlend.app.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.microlend.app.dao.LoanApplicationDao;
import com.microlend.app.database.AppDatabase;
import com.microlend.app.model.LoanApplication2;
import java.util.List;

public class LoanApplicationViewModel extends AndroidViewModel {
    private final LoanApplicationDao loanApplicationDao;
    private final LiveData<List<LoanApplication2>> allLoanApplications;

    public LoanApplicationViewModel(Application application) {
        super(application);
        AppDatabase db = AppDatabase.getInstance(application);
        loanApplicationDao = db.loanApplicationDao();
        allLoanApplications = loanApplicationDao.getAllLoanApplications(); // Directly use without casting
    }

    public LiveData<List<LoanApplication2>> getAllLoanApplications() {
        return allLoanApplications;
    }

    public void insert(LoanApplication2 loanApplication) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            try {
                loanApplicationDao.insert(loanApplication);
            } catch (Exception e) {
                e.printStackTrace(); // Log the exception
            }
        });
    }

    public void delete(LoanApplication2 loanApplication) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            try {
                loanApplicationDao.delete(loanApplication);
            } catch (Exception e) {
                e.printStackTrace(); // Log the exception
            }
        });
    }
}
