package show.example.com.showapplication.Fragments;

import android.media.Image;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import show.example.com.showapplication.R;

public class LogoutFragment extends Fragment {

    private ImageView img;

    public static LogoutFragment newInstance() {
        LogoutFragment fragment = new LogoutFragment();
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /*

    public View onCreateView(LayoutInflater inflater,
                         ViewGroup container,
                         Bundle savedInstanceState) {
     View view = inflater.inflate(R.layout.testclassfragment, container, false);
     ImageView imageView = (ImageView) view.findViewById(R.id.my_image);
     return view;
}




     */
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_logout, container, false);
        img = (ImageView)view.findViewById(R.id.truiton_image);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().moveTaskToBack(true);
                getActivity().finish();
                System.exit(0);
            }
        });
        return view;
    }
}
