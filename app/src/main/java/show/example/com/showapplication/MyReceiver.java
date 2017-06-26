package show.example.com.showapplication;

import android.support.v4.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.Toast;

import static android.app.PendingIntent.getActivity;

/**
 * Created by Arye on 20/06/2017.
 */

public class MyReceiver extends BroadcastReceiver {

    int a =1;
    @Override
    public void onReceive(final Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        Log.d("my service" , "onReceive@@@@@@@@@@@@@");
       
        Toast.makeText(context,intent.getAction(),Toast.LENGTH_LONG).show();

    }
}
