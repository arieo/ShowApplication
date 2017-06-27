package show.example.com.showapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import show.example.com.showapplication.Entities.Business;
import show.example.com.showapplication.Fragments.BusinessFragment;

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

        /*
        ---------------------- Solution 1 -------------------
        When you add the fragment in your transaction you should use a tag.

        fragTrans.replace(android.R.id.content, myFragment, "MY_FRAGMENT");
        ...and later if you want to check if the fragment is visible:

        MyFragment myFragment = (MyFragment)getFragmentManager().findFragmentByTag("MY_FRAGMENT");
        if (myFragment != null && myFragment.isVisible()) {
           // add your code here
        }

         */

        /*

        -------------------- Solution 2 ------------------------
        Fragment f = getActivity().getFragmentManager().findFragmentById(R.id.fragment_container);
        if (f instanceof CustomFragmentClass)
            // do something with f
            f.doSomething();

         */



        Toast.makeText(context,intent.getAction(),Toast.LENGTH_LONG).show();

    }
}
