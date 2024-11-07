<?php

use Illuminate\Support\Facades\Route;

Route::get('/', function () {
    return view('welcome');
});
Route::post('/api/chatbot', [ChatBotController::class, 'getResponse']);
Route::post('/financial-advice', [FinancialAdvisoryController::class, 'getAdvice']);