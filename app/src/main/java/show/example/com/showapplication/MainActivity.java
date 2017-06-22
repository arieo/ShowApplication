package show.example.com.showapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {


    //views
    BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;

    //Fragments
    ActionFragment actionFragment;
    BusinessFragment businessFragment;
    LogoutFragment logOutFragment;
    MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        //Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_business:
                                viewPager.setCurrentItem(0);
                                //selectedFragment = BusinessFragment.newInstance();
                                break;
                            case R.id.action_action:
                                viewPager.setCurrentItem(1);
                                //selectedFragment = ActionFragment.newInstance();
                                break;
                            case R.id.action_logout:
                                viewPager.setCurrentItem(2);
                                //selectedFragment = LogoutFragment.newInstance();
                                break;
                        }
                        //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        //transaction.replace(R.id.frame_layout, selectedFragment);
                        //transaction.commit();
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: "+position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setupViewPager(viewPager);
        //Manually displaying the first fragment - one time only
       // FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
       // transaction.replace(R.id.frame_layout, BusinessFragment.newInstance());
      //  transaction.commit();

        //Used to select an item programmatically
        //bottomNavigationView.getMenu().getItem(2).setChecked(true);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        businessFragment = new BusinessFragment();
        actionFragment = new ActionFragment();
        logOutFragment = new LogoutFragment();
        adapter.addFragment(businessFragment);
        adapter.addFragment(actionFragment);
        adapter.addFragment(logOutFragment);
        viewPager.setAdapter(adapter);
    }
}
