package com.vgdengineering.dashboard.ui.screen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.vgdengineering.dashboard.R;
import com.vgdengineering.dashboard.database.communication.Dao;
import com.vgdengineering.dashboard.database.communication.IDao;
import com.vgdengineering.dashboard.database.entity.Climatronic;
import com.vgdengineering.dashboard.database.entity.GearBox;
import com.vgdengineering.dashboard.database.entity.Parktronik;
import com.vgdengineering.dashboard.observables.ObservableDatabaseData;
import com.vgdengineering.dashboard.ui.view.CircularSeekBar;

import java.util.Observable;
import java.util.Observer;


public class MainActivity extends AppCompatActivity implements Observer {

    private int rpm = 0;
    private int speed = 0;
    private int carTemp = 0, outTemp = 0, fan = 0;
    private int gear = 0, nextGear = 0;
    private int frontsensor = 0, backsensor = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        ObservableDatabaseData.getInstance().addObserver(this);
        Dao.createDummyData();

        rpm = 2000;
        CircularSeekBar seekbar = (CircularSeekBar) findViewById(R.id.circularSeekBar1);
        seekbar.setProgress(rpm);
        TextView txt = (TextView) findViewById(R.id.rpm);
        txt.setText(Integer.toString(rpm));
        seekbar.setOnSeekBarChangeListener(new CircleSeekBarListener());

        speed = 100;
        CircularSeekBar seekbar1 = (CircularSeekBar) findViewById(R.id.circularSeekBar2);
        seekbar1.setProgress(speed);
        TextView txt1 = (TextView) findViewById(R.id.speed);
        txt1.setText(Integer.toString(speed));
        seekbar1.setOnSeekBarChangeListener(new CircleSeekBarListener());

//        Thread t = new Thread() {
//
//            @Override
//            public void run() {
//                try {
//                    while (!isInterrupted()) {
//                        Thread.sleep(500);
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                updateClimatronic();
//                                updateParktronic();
//                                updateGear();
//                            }
//                        });
//                    }
//                } catch (InterruptedException e) {
//                }
//            }
//        };
//
//        t.start();
    }

    public void updateClimatronic() {
        TextView cartemp1 = (TextView) findViewById(R.id.txtTemp);
        TextView carfan1 = (TextView) findViewById(R.id.txtfan);
        TextView outTemp1 = (TextView) findViewById(R.id.txtoutsideTemp);

        cartemp1.setText(Integer.toString(carTemp));
        carfan1.setText(Integer.toString(fan));
        outTemp1.setText(Integer.toString(outTemp));
    }

    public void updateGear() {
        TextView currentgear1 = (TextView) findViewById(R.id.txtgear);
        TextView nextgear1 = (TextView) findViewById(R.id.txtnextGear);

        currentgear1.setText(Integer.toString(gear));
        nextgear1.setText(Integer.toString(nextGear));
    }

    public void updateParktronic() {
        TextView d1 = (TextView) findViewById(R.id.txtdled1);
        TextView d2 = (TextView) findViewById(R.id.txtdled2);
        TextView d3 = (TextView) findViewById(R.id.txtdled3);
        TextView d4 = (TextView) findViewById(R.id.txtdled4);
        TextView d5 = (TextView) findViewById(R.id.txtdled5);

        TextView r1 = (TextView) findViewById(R.id.txtrled1);
        TextView r2 = (TextView) findViewById(R.id.txtrled2);
        TextView r3 = (TextView) findViewById(R.id.txtrled3);
        TextView r4 = (TextView) findViewById(R.id.txtrled4);
        TextView r5 = (TextView) findViewById(R.id.txtrled5);

        if (frontsensor == 0) {
            d1.setVisibility(View.GONE);
            d2.setVisibility(View.GONE);
            d3.setVisibility(View.GONE);
            d4.setVisibility(View.GONE);
            d5.setVisibility(View.GONE);
        } else if (frontsensor == 1) {
            d1.setVisibility(View.VISIBLE);
            d2.setVisibility(View.GONE);
            d3.setVisibility(View.GONE);
            d4.setVisibility(View.GONE);
            d5.setVisibility(View.GONE);
        } else if (frontsensor == 2) {
            d1.setVisibility(View.VISIBLE);
            d2.setVisibility(View.VISIBLE);
            d3.setVisibility(View.GONE);
            d4.setVisibility(View.GONE);
            d5.setVisibility(View.GONE);
        } else if (frontsensor == 3) {
            d1.setVisibility(View.VISIBLE);
            d2.setVisibility(View.VISIBLE);
            d3.setVisibility(View.VISIBLE);
            d4.setVisibility(View.GONE);
            d5.setVisibility(View.GONE);
        } else if (frontsensor == 4) {
            d1.setVisibility(View.VISIBLE);
            d2.setVisibility(View.VISIBLE);
            d3.setVisibility(View.VISIBLE);
            d4.setVisibility(View.VISIBLE);
            d5.setVisibility(View.GONE);
        } else if (frontsensor == 5) {
            d1.setVisibility(View.VISIBLE);
            d2.setVisibility(View.VISIBLE);
            d3.setVisibility(View.VISIBLE);
            d4.setVisibility(View.VISIBLE);
            d5.setVisibility(View.VISIBLE);
        }


        if (backsensor == 0) {
            r1.setVisibility(View.GONE);
            r2.setVisibility(View.GONE);
            r3.setVisibility(View.GONE);
            r4.setVisibility(View.GONE);
            r5.setVisibility(View.GONE);
        } else if (backsensor == 1) {
            r1.setVisibility(View.VISIBLE);
            r2.setVisibility(View.GONE);
            r3.setVisibility(View.GONE);
            r4.setVisibility(View.GONE);
            r5.setVisibility(View.GONE);
        } else if (backsensor == 2) {
            r1.setVisibility(View.VISIBLE);
            r2.setVisibility(View.VISIBLE);
            r3.setVisibility(View.GONE);
            r4.setVisibility(View.GONE);
            r5.setVisibility(View.GONE);
        } else if (backsensor == 3) {
            r1.setVisibility(View.VISIBLE);
            r2.setVisibility(View.VISIBLE);
            r3.setVisibility(View.VISIBLE);
            r4.setVisibility(View.GONE);
            r5.setVisibility(View.GONE);
        } else if (backsensor == 4) {
            r1.setVisibility(View.VISIBLE);
            r2.setVisibility(View.VISIBLE);
            r3.setVisibility(View.VISIBLE);
            r4.setVisibility(View.VISIBLE);
            r5.setVisibility(View.GONE);
        } else if (backsensor == 5) {
            r1.setVisibility(View.VISIBLE);
            r2.setVisibility(View.VISIBLE);
            r3.setVisibility(View.VISIBLE);
            r4.setVisibility(View.VISIBLE);
            r5.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        IDao dao = Dao.getInstance();

        Climatronic clima1 = dao.getClimatronic();
        carTemp = clima1.getDesiredDegrees();
        outTemp = clima1.getCurrentDegrees();
        fan = clima1.getBlowerPower();

        GearBox gear1 = dao.getGearBox();
        gear = gear1.getCurrentGear();
        nextGear = gear1.getNextGear();

        Parktronik parking1 = dao.getParktronik();
        frontsensor = parking1.getFront();
        backsensor = parking1.getRear();

        updateClimatronic();
        updateParktronic();
        updateGear();
    }

    public class CircleSeekBarListener implements CircularSeekBar.OnCircularSeekBarChangeListener {
        @Override
        public void onProgressChanged(CircularSeekBar seekBar, int progress, boolean fromUser) {


        }

        @Override
        public void onStopTrackingTouch(CircularSeekBar seekBar) {

        }

        @Override
        public void onStartTrackingTouch(CircularSeekBar seekBar) {

        }
    }

}
