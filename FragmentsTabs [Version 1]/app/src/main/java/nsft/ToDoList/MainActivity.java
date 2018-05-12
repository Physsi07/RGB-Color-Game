package nsft.ToDoList;

import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import petrov.kristiyan.colorpicker.ColorPicker;

import static nsft.ToDoList.R.array.Colors_For_SideBar;

// TO DO //
// USER NOTIFICATION
// SETTING AND
// USER PREFERENCES
// NOTIFIES ME WHEN I GET TO A SPECIFIC ADDRESS
// CHAPTER 9
// SETTINGS FOR THE USER CONTROL
// CREATE AN ALERT DIALOG SAYING "ARE YOU SURE YOU WANT TO DELETE"

public class MainActivity extends AppCompatActivity implements Tab1.OnFragmentInteractionListener,
        Tab2.OnFragmentInteractionListener, Tab3.OnFragmentInteractionListener {

    // MY VARIABLES //
    Toolbar toolbar;
    TabLayout tablayout;
    TabItem personal;
    TabItem business;
    TabItem finder;
    ViewPager viewPager;
    PagerAdapter pagerAdapter;

    // A STRING VARIABLE //
    private String selecteditem;

    // MY SPINNER FOR THE USER SETTING //A
    Spinner spinner1;

    private static final String TAG = "MainActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // MY TOOLBAR -> GETTING ITS ID, SETTING THE TITLE AND THE SUBTITLE OF IT //
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("To Do List");
        toolbar.setSubtitle("Welcome");
        setSupportActionBar(toolbar);

        // MY TABS //
        // WHAT YOU PUT IN THE TEXT WILL BE DISPLAY IN THE TAB //
        tablayout = findViewById(R.id.tapLayout);
        personal = findViewById(R.id.personalTab);
        business = findViewById(R.id.businessTab);
        finder = findViewById(R.id.finderTab);

        // MY SPINNERS //
        spinner1 = findViewById(R.id.toolbar_spinner);
        spinnerFunc();

        // MY VIEW PAGER //
        viewPager = (ViewPager) findViewById(R.id.pager);

        /* OBJECT OF THE PAGE ADAPTER CLASS */
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tablayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // SINKING THE VIEW PAGER WITH THE TABS //
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));

        // ADAPTERS //
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.Colors_For_SideBar,
                android.R.layout.simple_spinner_item);

        // SETTING THE ADAPTER //
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_multiple_choice);
        spinner1.setAdapter(adapter);

    }

    public void spinnerFunc() {
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selecteditem = parent.getItemAtPosition(position).toString();
//                selecteditem = parent.getSelectedItem().toString();

                // GETTING WHICH ITEM IN THE SPINNER IS SELECTED //
                if (getColor() != getResources().getColor(R.color.colorPrimary)) {
                    // COLOR THE TOOLBAR AND THE STATUS BAR //
                    toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }

                Log.d(TAG, "THE STRING: " + selecteditem);

                if (selecteditem.equals("Red")) {
                    Log.i(TAG, "COLOR IS RED");
                    // TOOLBAR //
                    toolbar.setBackgroundColor(getResources().getColor(R.color.Red));
                    toolbar.setTitleTextColor(getResources().getColor(R.color.white));
                    toolbar.setSubtitleTextColor(getResources().getColor(R.color.white));

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(getResources().getColor(R.color.Red));
                    }

                    storedColor(getResources().getColor(R.color.Red));
                    Log.d(TAG, "getColor() =" + getResources().getColor(R.color.Red));


                } else if (selecteditem.equals("Green")) {
                    Log.i(TAG, "COLOR IS GREEN");
                    // TOOLBAR //
                    toolbar.setBackgroundColor(getResources().getColor(R.color.Green));
                    toolbar.setTitleTextColor(getResources().getColor(R.color.black_de));
                    toolbar.setSubtitleTextColor(getResources().getColor(R.color.black_de));

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(getResources().getColor(R.color.Green));
                    }

                    storedColor(getResources().getColor(R.color.Green));

                } else if (selecteditem.equals("Blue")) {
                    Log.i(TAG, "COLOR IS BLUE");
                    // TOOLBAR //
                    toolbar.setBackgroundColor(getResources().getColor(R.color.Blue));
                    toolbar.setTitleTextColor(getResources().getColor(R.color.white));
                    toolbar.setSubtitleTextColor(getResources().getColor(R.color.white));

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(getResources().getColor(R.color.Blue));
                    }

                    storedColor(getResources().getColor(R.color.Blue));
                }

                if (selecteditem == "Colors"){
                    Toast.makeText(getApplicationContext(), selecteditem, Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void storedColor(int color) {
        SharedPreferences sharedPreferences = getSharedPreferences("Color", MODE_PRIVATE);
        SharedPreferences.Editor mEditor = sharedPreferences.edit();
        mEditor.putInt("Color", color);

        // SAVE THE CHANGES INTO SHARE PREFERENCE //
        mEditor.apply();
//        mEditor.commit();
    }

    public int getColor() {
        // DECLARING THE SHARE PREFERENCE VARIABLE //
        SharedPreferences sharedPreferences = getSharedPreferences("Color", MODE_PRIVATE);

        // JUST INCASE THE SHARED PREFERENCE IS EMPTY WE DONT WANT AN EXEPTION ERROR SO THEN WE JUST APPLY THE PRIMARY COLOR //
        int selectedColor = sharedPreferences.getInt("Color", getResources().getColor(R.color.colorPrimary));

        return selectedColor;
    }

}