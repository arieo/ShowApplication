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

import show.example.com.showapplication.Adpters.ActionAdapter;
import show.example.com.showapplication.Entities.ActDescription;
import show.example.com.showapplication.Entities.Action;
import show.example.com.showapplication.MainActivity;
import show.example.com.showapplication.R;

public class ActionFragment extends Fragment {
    Cursor mCursor;
    Uri mUri;
    private RecyclerView recyclerView;
    private ActionAdapter adapter;
    private List<Action> actionList;
    View view;

    public static ActionFragment newInstance() {
        ActionFragment fragment = new ActionFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_action, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_action);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

        initCollapsingToolbar();

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_action);

        actionList = new ArrayList<>();
        adapter = new ActionAdapter(this.getContext(), actionList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this.getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new ActionFragment.GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        new getActionCursor().execute();

        ActionReceiver busiReciver= new ActionReceiver();
        getActivity().registerReceiver(busiReciver,new IntentFilter("com.example.loginapplication.UPDATE"));
        //prepareActions();

        try {
            Glide.with(this).load(R.drawable.giphy).into((ImageView) view.findViewById(R.id.backdrop_action));
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
                (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar_action);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) view.findViewById(R.id.appbar_action);
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

    /**
     * Adding few actions for testing
     */
    private void prepareActions() {
        int[] covers = new int[]{
                R.drawable.a1,
                R.drawable.a2,
                R.drawable.a3,
                R.drawable.a4,
                R.drawable.a5,
                R.drawable.a6,
                R.drawable.a7,
                R.drawable.a8,
                R.drawable.a9,
                R.drawable.a10,
                R.drawable.a11,
                R.drawable.a12,
                R.drawable.a13,
                R.drawable.a14,
                R.drawable.a15,
                R.drawable.a16
        };
        int counter = 0;
        try {
            while (mCursor.moveToNext()) {
                Action action = new Action();
                action.setBusinessID(mCursor.getString(0));
                action.setActStart(mCursor.getString(1));
                action.setActEnd(mCursor.getString(2));
                action.setActType(mCursor.getString(3));
                action.setActPrice(mCursor.getLong(4));
                action.setActDescription(mCursor.getString(6));
                action.setActState(mCursor.getString(5));
                //String tmpDescription = mCursor.getString(6);
                //action.setActDescription(ActDescription.airlineCompany.valueOf(tmpDescription));
                action.setThumbnail(covers[counter++]);
                actionList.add(action);
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

    private class getActionCursor extends AsyncTask<Void, Void, Cursor> {


        @Override
        protected Cursor doInBackground(Void... params) {
            mUri = Uri.parse("content://com.example.loginapplication.Model.BackEnd.BusinessAndActionProvider/businessAction");
            return getActivity().getApplicationContext().getContentResolver().query(mUri, null, null, null, null);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            mCursor = cursor;
            prepareActions();
        }
    }

    public  class ActionReceiver extends BroadcastReceiver {

        int a = 1;

        @Override
        public void onReceive(final Context context, Intent intent) {

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(ActionFragment.this).attach(ActionFragment.this).commit();
            Log.d("my service", "onReceive@@@@@@@@@@@@@");

            Toast.makeText(context, "Itamar", Toast.LENGTH_LONG).show();
        }
    }
}
