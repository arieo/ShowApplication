package show.example.com.showapplication.Fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import show.example.com.showapplication.Adpters.BusinessAdapter;
import show.example.com.showapplication.Entities.Business;
import show.example.com.showapplication.MainActivity;
import show.example.com.showapplication.R;

public class BusinessFragment extends Fragment {
    Cursor mCursor;
    Uri mUri;
    private RecyclerView recyclerView;
    private BusinessAdapter adapter;
    private List<Business> businessList;
    View view;

    public static BusinessFragment newInstance() {
        BusinessFragment fragment = new BusinessFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_business, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_busi);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

        initCollapsingToolbar();

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_busi);

        businessList = new ArrayList<>();
        adapter = new BusinessAdapter(this.getContext(), businessList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this.getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        new getBusinessCursor().execute();

        BusiReceiver busiReciver= new BusiReceiver();
        getActivity().registerReceiver(busiReciver,new IntentFilter("com.example.loginapplication.UPDATE"));
        //prepareBusinesss();

        try {
            Glide.with(this).load(R.drawable.businesspic).into((ImageView) view.findViewById(R.id.backdrop_busi));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {

        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar_busi);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) view.findViewById(R.id.appbar_busi);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }


    private void prepareBusinesss() {
        int[] covers = new int[]{
                R.drawable.v1,
                R.drawable.v2,
                R.drawable.v3,
                R.drawable.v4,
                R.drawable.v5,
                R.drawable.v6,
                R.drawable.v7,
                R.drawable.v8,
                R.drawable.v9,
                R.drawable.v10,
                R.drawable.v11,
                R.drawable.v12,
                R.drawable.v13,
                R.drawable.v14,
                R.drawable.v15,
                R.drawable.v16
        };
        int counter = 0;

       // mUri = Uri.parse("content://com.example.loginapplication.Model.BackEnd.BusinessAndActionProvider/business");
        //mCursor = getActivity().getApplicationContext().getContentResolver().query(mUri, null, null, null, null);
        try {


            while (mCursor.moveToNext()) {
                Business business = new Business();
                business.setBusiId(mCursor.getString(0));
                business.setBusiEmail(mCursor.getString(1));
                business.setBusiWebSite(mCursor.getString(2));
                business.setBusiState(mCursor.getString(3));
                business.setBusiCity(mCursor.getString(4));
                business.setBusiAddress(mCursor.getString(5));
                business.setBusiPhone(mCursor.getString(6));
                business.setThumbnail(covers[counter++]);
                businessList.add(business);
            }

        } catch (Exception e) {
            Log.d("EXCEPTION", e.toString());
        }

        adapter.notifyDataSetChanged();
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public  class BusiReceiver extends BroadcastReceiver {

        int a = 1;

        @Override
        public void onReceive(final Context context, Intent intent) {
            // TODO: This method is called when the BroadcastReceiver is receiving
            // an Intent broadcast.
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(BusinessFragment.this).attach(BusinessFragment.this).commit();
            Log.d("my service", "onReceive@@@@@@@@@@@@@");

            Toast.makeText(context, intent.getAction(), Toast.LENGTH_LONG).show();

        }

    }

    ;


    private class getBusinessCursor extends AsyncTask<Void, Void, Cursor> {


        @Override
        protected Cursor doInBackground(Void... params) {
            //if (android.os.Debug.isDebuggerConnected())
            //    android.os.Debug.waitForDebugger();
            mUri = Uri.parse("content://com.example.loginapplication.Model.BackEnd.BusinessAndActionProvider/business");
            return getActivity().getApplicationContext().getContentResolver().query(mUri, null, null, null, null);
            //prepareBusinesss();
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            mCursor = cursor;
            prepareBusinesss();
        }
    }
}
