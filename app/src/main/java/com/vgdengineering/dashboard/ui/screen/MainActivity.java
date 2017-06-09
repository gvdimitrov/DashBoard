package com.vgdengineering.dashboard.ui.screen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.vgdengineering.dashboard.R;
import com.vgdengineering.dashboard.database.communication.Dao;
import com.vgdengineering.dashboard.database.communication.IDao;
import com.vgdengineering.dashboard.database.entity.BeltsWarning;
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
    private int carTemp, outTemp, fan = 1;
    private int gear, nextGear;
    private int frontsensor = 0, backsensor = 0;
    private boolean beltsSensor;
    private String beltsSeverity;
    private boolean flag1 = false,flag2 = false,flag3 = false,flag4 = false,flag5 = false,flag6 = false,flagR = false;

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

        Thread t1 = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(100);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateSpeed();
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t1.start();
    }

    public void updateSpeed()
    {
        if (gear ==0)
        {
            rpm = 800;
            speed = 0;

            com.vgdengineering.dashboard.ui.view.CircularSeekBar seekbar = (com.vgdengineering.dashboard.ui.view.CircularSeekBar) findViewById(R.id.circularSeekBar1);
            seekbar.setProgress(rpm);
            TextView txt = (TextView) findViewById(R.id.rpm);
            txt.setText(Integer.toString(rpm));

            com.vgdengineering.dashboard.ui.view.CircularSeekBar seekbar1 = (com.vgdengineering.dashboard.ui.view.CircularSeekBar) findViewById(R.id.circularSeekBar2);
            seekbar1.setProgress(speed);
            TextView txt1 = (TextView) findViewById(R.id.speed);
            txt1.setText(Integer.toString(speed));
        }

        else if(gear == 1)
        {
            flag2 = false;
            flag3 = false;
            flag4 = false;
            flag5 = false;
            flag6 = false;
            flagR = false;
            if(flag1 == false)
            {
                rpm = 800;
                speed = 0;
                flag1 = true;
            }

            com.vgdengineering.dashboard.ui.view.CircularSeekBar seekbar = (com.vgdengineering.dashboard.ui.view.CircularSeekBar) findViewById(R.id.circularSeekBar1);
            seekbar.setProgress(rpm);
            TextView txt = (TextView) findViewById(R.id.rpm);
            txt.setText(Integer.toString(rpm));

            com.vgdengineering.dashboard.ui.view.CircularSeekBar seekbar1 = (com.vgdengineering.dashboard.ui.view.CircularSeekBar) findViewById(R.id.circularSeekBar2);
            seekbar1.setProgress(speed);
            TextView txt1 = (TextView) findViewById(R.id.speed);
            txt1.setText(Integer.toString(speed));

            if (rpm <= 2000)
            {

                rpm = rpm + 40;
                speed = speed + 1;

                seekbar.setProgress(rpm);
                txt.setText(Integer.toString(rpm));

                seekbar1.setProgress(speed);
                txt1.setText(Integer.toString(speed));
            }


        }

        else if(gear == 2)
        {
            flag1 = false;
            flag3 = false;
            flag4 = false;
            flag5 = false;
            flag6 = false;
            flagR = false;
            if(flag2 == false)
            {
                rpm = 1000;
                speed = 28;
                flag2 = true;

            }

            com.vgdengineering.dashboard.ui.view.CircularSeekBar seekbar = (com.vgdengineering.dashboard.ui.view.CircularSeekBar) findViewById(R.id.circularSeekBar1);
            seekbar.setProgress(rpm);
            TextView txt = (TextView) findViewById(R.id.rpm);
            txt.setText(Integer.toString(rpm));

            com.vgdengineering.dashboard.ui.view.CircularSeekBar seekbar1 = (com.vgdengineering.dashboard.ui.view.CircularSeekBar) findViewById(R.id.circularSeekBar2);
            seekbar1.setProgress(speed);
            TextView txt1 = (TextView) findViewById(R.id.speed);
            txt1.setText(Integer.toString(speed));

            if (rpm <= 2600)
            {
                rpm = rpm + 40;
                speed = speed + 1;

                seekbar.setProgress(rpm);
                txt.setText(Integer.toString(rpm));

                seekbar1.setProgress(speed);
                txt1.setText(Integer.toString(speed));
            }

        }

        else if(gear == 3)
        {
            flag1 = false;
            flag2 = false;
            flag4 = false;
            flag5 = false;
            flag6 = false;
            flagR = false;
            if(flag3 == false)
            {
                rpm = 1200;
                speed = 68;
                flag3 = true;

            }

            com.vgdengineering.dashboard.ui.view.CircularSeekBar seekbar = (com.vgdengineering.dashboard.ui.view.CircularSeekBar) findViewById(R.id.circularSeekBar1);
            seekbar.setProgress(rpm);
            TextView txt = (TextView) findViewById(R.id.rpm);
            txt.setText(Integer.toString(rpm));

            com.vgdengineering.dashboard.ui.view.CircularSeekBar seekbar1 = (com.vgdengineering.dashboard.ui.view.CircularSeekBar) findViewById(R.id.circularSeekBar2);
            seekbar1.setProgress(speed);
            TextView txt1 = (TextView) findViewById(R.id.speed);
            txt1.setText(Integer.toString(speed));

            if (rpm <= 3200)
            {
                rpm = rpm + 40;
                speed = speed + 1;

                seekbar.setProgress(rpm);
                txt.setText(Integer.toString(rpm));

                seekbar1.setProgress(speed);
                txt1.setText(Integer.toString(speed));
            }

        }

        else if(gear == 4)
        {
            flag1 = false;
            flag2 = false;
            flag3 = false;
            flag5 = false;
            flag6 = false;
            flagR = false;
            if(flag4 == false)
            {
                rpm = 1200;
                speed = 115;
                flag4 = true;

            }

            com.vgdengineering.dashboard.ui.view.CircularSeekBar seekbar = (com.vgdengineering.dashboard.ui.view.CircularSeekBar) findViewById(R.id.circularSeekBar1);
            seekbar.setProgress(rpm);
            TextView txt = (TextView) findViewById(R.id.rpm);
            txt.setText(Integer.toString(rpm));

            com.vgdengineering.dashboard.ui.view.CircularSeekBar seekbar1 = (com.vgdengineering.dashboard.ui.view.CircularSeekBar) findViewById(R.id.circularSeekBar2);
            seekbar1.setProgress(speed);
            TextView txt1 = (TextView) findViewById(R.id.speed);
            txt1.setText(Integer.toString(speed));

            if (rpm <= 3200)
            {
                rpm = rpm + 40;
                speed = speed + 1;

                seekbar.setProgress(rpm);
                txt.setText(Integer.toString(rpm));

                seekbar1.setProgress(speed);
                txt1.setText(Integer.toString(speed));
            }

        }

        else if(gear == 5)
        {
            flag1 = false;
            flag2 = false;
            flag3 = false;
            flag4 = false;
            flag6 = false;
            flagR = false;
            if(flag5 == false)
            {
                rpm = 1400;
                speed = 168;
                flag5 = true;

            }


            com.vgdengineering.dashboard.ui.view.CircularSeekBar seekbar = (com.vgdengineering.dashboard.ui.view.CircularSeekBar) findViewById(R.id.circularSeekBar1);
            seekbar.setProgress(rpm);
            TextView txt = (TextView) findViewById(R.id.rpm);
            txt.setText(Integer.toString(rpm));

            com.vgdengineering.dashboard.ui.view.CircularSeekBar seekbar1 = (com.vgdengineering.dashboard.ui.view.CircularSeekBar) findViewById(R.id.circularSeekBar2);
            seekbar1.setProgress(speed);
            TextView txt1 = (TextView) findViewById(R.id.speed);
            txt1.setText(Integer.toString(speed));

            if (rpm <= 3800)
            {
                rpm = rpm + 40;
                speed = speed + 1;

                seekbar.setProgress(rpm);
                txt.setText(Integer.toString(rpm));

                seekbar1.setProgress(speed);
                txt1.setText(Integer.toString(speed));
            }

        }

        else if(gear == 6)
        {
            flag1 = false;
            flag2 = false;
            flag3 = false;
            flag4 = false;
            flag5 = false;
            flagR = false;
            if(flag6 == false)
            {
                rpm = 1400;
                speed = 220;
                flag6 = true;

            }

            com.vgdengineering.dashboard.ui.view.CircularSeekBar seekbar = (com.vgdengineering.dashboard.ui.view.CircularSeekBar) findViewById(R.id.circularSeekBar1);
            seekbar.setProgress(rpm);
            TextView txt = (TextView) findViewById(R.id.rpm);
            txt.setText(Integer.toString(rpm));

            com.vgdengineering.dashboard.ui.view.CircularSeekBar seekbar1 = (com.vgdengineering.dashboard.ui.view.CircularSeekBar) findViewById(R.id.circularSeekBar2);
            seekbar1.setProgress(speed);
            TextView txt1 = (TextView) findViewById(R.id.speed);
            txt1.setText(Integer.toString(speed));

            if (rpm <= 1600)
            {
                rpm = rpm + 40;
                speed = speed + 2;

                seekbar.setProgress(rpm);
                txt.setText(Integer.toString(rpm));

                seekbar1.setProgress(speed);
                txt1.setText(Integer.toString(speed));
            }

        }

        else if(gear == -1)
        {
            flag1 = false;
            flag2 = false;
            flag3 = false;
            flag4 = false;
            flag5 = false;
            flag6 = false;
            if(flagR == false)
            {
                rpm = 800;
                speed = 0;
                flagR = true;

            }

            com.vgdengineering.dashboard.ui.view.CircularSeekBar seekbar = (com.vgdengineering.dashboard.ui.view.CircularSeekBar) findViewById(R.id.circularSeekBar1);
            seekbar.setProgress(rpm);
            TextView txt = (TextView) findViewById(R.id.rpm);
            txt.setText(Integer.toString(rpm));

            com.vgdengineering.dashboard.ui.view.CircularSeekBar seekbar1 = (com.vgdengineering.dashboard.ui.view.CircularSeekBar) findViewById(R.id.circularSeekBar2);
            seekbar1.setProgress(speed);
            TextView txt1 = (TextView) findViewById(R.id.speed);
            txt1.setText(Integer.toString(speed));

            if (rpm <= 1200)
            {
                rpm = rpm + 40;
                speed = speed + 1;

                seekbar.setProgress(rpm);
                txt.setText(Integer.toString(rpm));

                seekbar1.setProgress(speed);
                txt1.setText(Integer.toString(speed));
            }

        }
    }

    public void updateSeatBelt()
    {
        ImageView imgBelt = (ImageView) findViewById(R.id.seatbelts1);

        if(beltsSensor == true){


            if(beltsSeverity.equals("high")){
                final Animation animation = new AlphaAnimation((float) 1, 0); // Change alpha from fully visible to invisible
                animation.setDuration(250); // duration - half a second
                animation.setInterpolator(new LinearInterpolator()); // do not alter
                // animation
                // rate
                animation.setRepeatCount(Animation.INFINITE); // Repeat animation
                // infinitely
                animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the
                // end so the button will
                // fade back in
                imgBelt.startAnimation(animation);

            }
            else if(beltsSeverity.equals("low")){
                imgBelt.setVisibility(View.VISIBLE);
            }

        }else {
            imgBelt.setVisibility(View.GONE);
        }

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

        if(gear == (-1))
        {
            currentgear1.setText("R");
        }

        if(nextGear == (-1))
        {
            nextgear1.setText("R");
        }
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

        BeltsWarning belts1 = dao.getBeltsWarning();
        beltsSensor = belts1.isWarningForSeatBelt();
        beltsSeverity = belts1.getWarningSeverity();

        updateClimatronic();
        updateParktronic();
        updateGear();
        updateSeatBelt();
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

