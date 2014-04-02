package com.evgen3.xmlparser.app.application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.*;
import android.widget.TextView;
import android.widget.Toast;
import com.evgen3.xmlparser.app.R;

import java.util.ArrayList;
import java.util.Arrays;

public class ShowResult extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    final String LOG_TAG = "myLogs";

    static ArrayList<String> placeDX = new ArrayList<>(5);
    static ArrayList<String> originplaceDX = new ArrayList<>(5);

    static String person = ""; // Так как всегда будет один
    static ArrayList<String> department = new ArrayList<String>(5);
    static ArrayList<String> qualification = new ArrayList<String>(5);
    static ArrayList<String> licence = new ArrayList<String>(5);
    static ArrayList<String> speciality = new ArrayList<String>(5);
    static ArrayList<String> budget = new ArrayList<String>(5);
    static ArrayList<String> place = new ArrayList<String>(5);
    static ArrayList<String> originplace = new ArrayList<String>(5);
    static ArrayList<String> rating = new ArrayList<String>(5);
    static ArrayList<String> privilege = new ArrayList<String>(5);
    static ArrayList<String> docstate = new ArrayList<String>(5);
    static ArrayList<String> title = new ArrayList<String>(5);
    static ArrayList<String> comment = new ArrayList<String>(5);

    /*todo
    ? Автообновление
        - Обновлять через промежутки времени
        - Хранить эти промежутки в локальном пар-ре настроек
     */

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 0:
                mTitle = department.get(number);
                break;
            case 1:
                mTitle = department.get(number);
                break;
            case 2:
                mTitle = department.get(number);
                break;
            case 3:
                mTitle = department.get(number);
                break;
            case 4:
                mTitle = department.get(number);
                break;
            default:
                Log.e(LOG_TAG, "Error department number." + number);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);// Title, который по середине при каждом выборе меняется тут - onSectionAttached
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_example) {
            Intent n = getIntent();
            updateXD();
            fillXD();
            updateArrays();
            if (new MainActivity().update(n.getStringExtra("id"), getApplicationContext())){
                onNavigationDrawerItemSelected(PlaceholderFragment.n);
            } else{
                Toast.makeText(this, "Error while update.", Toast.LENGTH_LONG).show();
                return true;
            }
            /*DialogConfirm dialog = new DialogConfirm();
            dialog.setActivity(this);
            dialog.onCreateDialog(getSavedInstanceState()).show();*/
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static int n;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            n = sectionNumber;
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            if (rootView == null) throw new AssertionError();
            TextView tv0 = (TextView) rootView.findViewById(R.id.abiturient);
            tv0.append(person);
            TextView tv1 = (TextView) rootView.findViewById(R.id.speciality);
            tv1.append(speciality.get(n));
            TextView tv2 = (TextView) rootView.findViewById(R.id.qualification);
            tv2.append(qualification.get(n));
            TextView tv3 = (TextView) rootView.findViewById(R.id.licence);
            tv3.append(licence.get(n));
            TextView tv4 = (TextView) rootView.findViewById(R.id.budget);
            tv4.append(budget.get(n));
            TextView tv5 = (TextView) rootView.findViewById(R.id.rating);
            tv5.append(rating.get(n));//getArguments().getInt(ARG_SECTION_NUMBER)
            TextView tv6 = (TextView) rootView.findViewById(R.id.privilege);
            tv6.append(privilege.get(n));
            TextView tv7 = (TextView) rootView.findViewById(R.id.docstate);
            tv7.setText(docstate.get(n));
            TextView tv8 = (TextView) rootView.findViewById(R.id.title);
            tv8.setText(title.get(n));
            TextView tv9 = (TextView) rootView.findViewById(R.id.comment);
            tv9.setText(comment.get(n));
            TextView tv10 = (TextView) rootView.findViewById(R.id.place);
            tv10.append(place.get(n));
            TextView tv11 = (TextView) rootView.findViewById(R.id.originplace);
            tv11.append(originplace.get(n));
            // Вывести разницу в рейтинге, если есть.
            if (placeDX.size() > 0){
                TextView tv101 = (TextView) rootView.findViewById(R.id.placeDX);
                tv101.setText(" ( " + String.valueOf(Integer.valueOf(placeDX.get(n))
                        - Integer.valueOf(place.get(n))) + " )");
            }
            if (originplaceDX.size() > 0){
                TextView tv101 = (TextView) rootView.findViewById(R.id.originplaceDX);
                tv101.setText(" ( " + String.valueOf(Integer.valueOf(originplaceDX.get(n))
                        - Integer.valueOf(originplace.get(n))) + " )");
            }
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((ShowResult) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

    static public String[] toArr(ArrayList<String> s){
        return Arrays.copyOf(s.toArray(), s.size(), String[].class);
    }

    static public void updateArrays() {
        speciality.clear();
        department.clear();
        qualification.clear();
        licence.clear();
        speciality.clear();
        budget.clear();
        place.clear();
        originplace.clear();
        rating.clear();
        privilege.clear();
        docstate.clear();
        title.clear();
        comment.clear();
    }

    static public void updateXD(){
        placeDX.clear();
        originplaceDX.clear();
    }
    private void fillXD(){
        for (String aPlace : place) {
            placeDX.add(aPlace);
        }
        for (String anOriginplace : originplace) {
            originplaceDX.add(anOriginplace);
        }
    }
}
