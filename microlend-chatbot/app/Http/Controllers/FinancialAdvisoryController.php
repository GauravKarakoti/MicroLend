<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;

class FinancialAdvisoryController extends Controller
{
    public function getAdvice(Request $request)
    {
        $query = $request->input('query'); // Query from the user

        // Simple rule-based logic for demonstration
        if (strpos($query, 'invest') !== false) {
            $response = "We recommend diversifying your investments between stocks, bonds, and mutual funds based on your risk profile.";
        } elseif (strpos($query, 'loan') !== false) {
            $response = "To apply for a loan, make sure you have a steady income and a good credit score.";
        } else {
            $response = "I can help with investment tips, loan guidance, or budgeting advice. What would you like to know?";
        }

        return response()->json(['response' => $response]);
    }
}
