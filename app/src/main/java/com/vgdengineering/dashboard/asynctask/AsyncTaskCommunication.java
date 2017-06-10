package com.vgdengineering.dashboard.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vgdengineering.dashboard.communication.ResponseModule;
import com.vgdengineering.dashboard.DashboardApplication;
import com.vgdengineering.dashboard.database.communication.Dao;
import com.vgdengineering.dashboard.database.entity.BeltsWarning;
import com.vgdengineering.dashboard.database.entity.Climatronic;
import com.vgdengineering.dashboard.database.entity.GearBox;
import com.vgdengineering.dashboard.database.entity.Parktronik;
import com.vgdengineering.dashboard.database.entity.TripComputer;

import java.lang.reflect.Type;
import java.util.List;

public class AsyncTaskCommunication extends AsyncTask<Void, Void, Void>{
    private static final String TAG = AsyncTaskCommunication.class.getSimpleName();
    private static void getData() {

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(DashboardApplication.getAppContext());
        String url ="http://165.227.134.146:8080/communication/messages";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("AsyncTaskCommunication", response);

                        Gson gson = new Gson();

                        Type type = new TypeToken<List<ResponseModule>>(){}.getType();
                        List<ResponseModule> a = gson.fromJson(response, type);

                        for(ResponseModule r : a){
                            switch (r.getModuleName()){
                                case "trip" : {
                                    Log.d(TAG, "TRIP: " + r.getValueAsString());
                                    Type tripType = new TypeToken<TripComputer>(){}.getType();
                                    TripComputer tc = gson.fromJson(r.getValueAsString(), tripType);
                                    Dao.getInstance().saveTripComputer(tc);
                                    break;
                                }
                                case "belt" : {
                                    Log.d(TAG, "BELT: " + r.getValueAsString());
                                    Type beltType = new TypeToken<BeltsWarning>(){}.getType();
                                    BeltsWarning bw = gson.fromJson(r.getValueAsString(), beltType);
                                    Dao.getInstance().saveBeltWarning(bw);
                                    break;
                                }
                                case "headlights" : {break;}
                                case "climatronic" : {
                                    Log.d(TAG, "CLIMATRONIC: " + r.getValueAsString());
                                    Type climatronicType = new TypeToken<Climatronic>(){}.getType();
                                    Climatronic c = gson.fromJson(r.getValueAsString(), climatronicType);
                                    Dao.getInstance().saveClimatronic(c);
                                    break;
                                }
                                case "parktronic" : {
                                    Log.d(TAG, "PARKTRONIC: " + r.getValueAsString());
                                    Type parktronicType = new TypeToken<Parktronik>(){}.getType();
                                    Parktronik p = gson.fromJson(r.getValueAsString(), parktronicType);
                                    Dao.getInstance().saveParktronik(p);
                                    break;
                                }
                                case "gear" : {
                                    Log.d(TAG, "GEARBOX: " + r.getValueAsString());
                                    Type gearBoxType = new TypeToken<GearBox>(){}.getType();
                                    GearBox gb = gson.fromJson(r.getValueAsString(), gearBoxType);
                                    Dao.getInstance().saveGearBox(gb);
                                    break;
                                }
                            }
                            Log.d(AsyncTaskCommunication.class.getSimpleName(),"ModuleName: "+ r.getModuleName());
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("AsyncTaskCommunication", error.getMessage());
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }


    @Override
    protected Void doInBackground(Void... params) {
        getData();
        return null;
    }
}
