package show.example.com.showapplication.Fragments;


import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import show.example.com.showapplication.Entities.Business;
import show.example.com.showapplication.R;

public class ActionFragment extends Fragment {

    Cursor mCursor;

    public static ActionFragment newInstance() {
        ActionFragment fragment = new ActionFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
       // launchReceiver();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_action, container, false);
    }


    //IntentFilter filter = new IntentFilter("com.example.loginapplication.UPDATE");

   // MyReceiver receiver = new MyReceiver();
   // registerReceiver(receiver, filter);

    /*private void launchReceiver()
    {
         final BroadcastReceiver actionReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //Intent in = getIntent();
                //finish();
                Log.d("actionReceiver", "onReceive: BROADCAST RECIBIDO!!!");
                Toast.makeText(context,intent.getAction(),Toast.LENGTH_LONG).show();
            }
        };
    }*/
}