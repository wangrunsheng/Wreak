package com.wangrunsheng.wreak.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.wangrunsheng.wreak.R;
import com.wangrunsheng.wreak.fragment.CalendarFragment;
import com.wangrunsheng.wreak.fragment.HistoryFragment;
import com.wangrunsheng.wreak.fragment.MeFragment;
import com.wangrunsheng.wreak.fragment.TableFragment;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager;
    private TableFragment mTableFragment;
    private CalendarFragment mCalendarFragment;
    private HistoryFragment mHistoryFragment;
    private MeFragment mMeFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    transaction.replace(R.id.fragment, mTableFragment).commit();
                    return true;
                case R.id.navigation_calendar:
                    transaction.replace(R.id.fragment, mCalendarFragment).commit();
                    return true;
                case R.id.navigation_history:
                    transaction.replace(R.id.fragment, mHistoryFragment).commit();
                    return true;
                case R.id.navigation_me:
                    transaction.replace(R.id.fragment, mMeFragment).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentManager = getSupportFragmentManager();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        initFragments();
        navigation.setSelectedItemId(R.id.navigation_home);
    }

    private void initFragments() {
        mTableFragment = new TableFragment();
        mCalendarFragment = new CalendarFragment();
        mHistoryFragment = new HistoryFragment();
        mMeFragment = new MeFragment();
    }

    @SuppressLint("RestrictedApi")
    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
