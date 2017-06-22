package show.example.com.showapplication.Fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import show.example.com.showapplication.R;

public class BusinessFragment extends Fragment {
    Cursor mCursor;
    public static BusinessFragment newInstance() {
        BusinessFragment fragment = new BusinessFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_business, container, false);
    }
}
