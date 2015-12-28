package world.buff.buffcall.buffcall;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /** Called when the user clicks the Save button */
    public void onSaveSchedule(View view) {
/*
        Context context = getApplicationContext();
        CharSequence text = "Call is scheduled";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
*/
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout,
                (ViewGroup) findViewById(R.id.toast_layout_save));

        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText("Call is scheduled");

        Toast toast = new Toast(getApplicationContext());
        //toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showIncomingCallScreen();
            }
        }, getResources().getInteger(R.integer.incoming_call_screen_delay_display));

    }

    private void showIncomingCallScreen() {
        Intent intent = new Intent(this, IncomingCall.class);
        startActivity(intent);

        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        if(notification == null) {
            System.out.println("inside loop++++++++");
            notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        }


        Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        if(alert == null){
            // alert is null, using backup
            alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

            // I can't see this ever being null (as always have a default notification)
            // but just incase
            if(alert == null) {
                // alert backup is null, using 2nd backup
                alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            }
        }

        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
        r.play();

        //For PRODUCTION USE THIS! for test use above
        //MediaPlayer mp = MediaPlayer.create(getApplicationContext(), alert);
        //mp.setLooping(true);
        //mp.start();
        /*
        mp.stop();

        mp.prepare();
        mp.prepareAsync();
        mp.start();
        */



        //assuming your Layout is named linearlayout1:
       // LinearLayout ll = (LinearLayout) findViewById(R.id.linearlayout1);
       // ll.setBackgroundResource(R.drawable.sample);
    }
}
