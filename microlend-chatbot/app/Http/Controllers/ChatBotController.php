<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Http;


class ChatBotController extends Controller
{
    public function getResponse(Request $request)
    {
        $userMessage = $request->input('message');

        // Make API call to Serverless Inference
        $response = Http::post('https://api.yourserverlessendpoint.com/inference', [
            'input' => $userMessage,
        ]);

        return response()->json($response->json());
    }
}
