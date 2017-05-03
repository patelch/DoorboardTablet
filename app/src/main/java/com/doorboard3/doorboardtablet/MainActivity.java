    package com.doorboard3.doorboardtablet;

    import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;


    public class MainActivity extends AppCompatActivity
            implements OnFragmentInteractionListener {

        /**
         * The {@link android.support.v4.view.PagerAdapter} that will provide
         * fragments for each of the sections. We use a
         * {@link FragmentPagerAdapter} derivative, which will keep every
         * loaded fragment in memory. If this becomes too memory intensive, it
         * may be best to switch to a
         * {@link android.support.v4.app.FragmentStatePagerAdapter}.
         */
        private SectionsPagerAdapter mSectionsPagerAdapter;

        /**
         * The {@link ViewPager} that will host the section contents.
         */
        private ViewPager mViewPager;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            DoorboardDbHelper dbHelper = new DoorboardDbHelper(this);
            dbHelper.clearDB();

            // Create the adapter that will return a fragment for each of the three
            // primary sections of the activity.
            mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

            // Set up the ViewPager with the sections adapter.
            mViewPager = (ViewPager) findViewById(R.id.container);
            mViewPager.setAdapter(mSectionsPagerAdapter);

            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(mViewPager);
        }

//        protected String getEventTitle(Calendar time) {
//            return String.format("Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH)+1, time.get(Calendar.DAY_OF_MONTH));
//        }
//
//        public void onEventClick(WeekViewEvent event, RectF eventRect) {
//            Toast.makeText(this, "Clicked " + event.getName(), Toast.LENGTH_SHORT).show();
//        }
//
//        public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
//            Toast.makeText(this, "Long pressed event: " + event.getName(), Toast.LENGTH_SHORT).show();
//        }
//
//        public void onEmptyViewLongPress(Calendar time) {
//            Toast.makeText(this, "Empty view long pressed: " + getEventTitle(time), Toast.LENGTH_SHORT).show();
//        }


        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        public void onFloorToggle(View view) {

            ((RadioGroup)findViewById(R.id.floor_toggle)).check(view.getId());
            ImageView floorMap = (ImageView) findViewById(R.id.floor_map);

            switch (view.getId()) {
                case R.id.floor_g:
                    floorMap.setImageResource(R.drawable.ground_floor);
                    break;
                case R.id.floor_1:
                    floorMap.setImageResource(R.drawable.first_floor);
                    break;
                case R.id.floor_2:
                case R.id.floor_3:
                case R.id.floor_4:
                case R.id.floor_5:
                    floorMap.setImageResource(R.drawable.upper_floor);
                    break;
            }
        }

    //    @Override
    //    public boolean onOptionsItemSelected(MenuItem item) {
    //        return super.onOptionsItemSelected(item);
    //    }


        public void onFragmentInteraction(int position) {
    //        FeedFragment articleFrag = (FeedFragment)
    //                getSupportFragmentManager().findFragmentById(position);

        }

        /**
         * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
         * one of the sections/tabs/pages.
         */
        public class SectionsPagerAdapter extends FragmentPagerAdapter {

            private final int mNumTabs = 4;

            public SectionsPagerAdapter(FragmentManager fm) {
                super(fm);
            }

            @Override
            public Fragment getItem(int position) {

                switch (position) {
                    case 0:
                        return InfoFragment.newInstance();
                    case 1:
                        return ScheduleFragment.newInstance();
                    case 2:
                        return MapFragment.newInstance();
                    case 3:
                        return SearchFragment.newInstance();
                    default:
                        return null;
                }
            }

            @Override
            public int getCount() {
                // Show 3 total pages.
                return mNumTabs;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "info";
                    case 1:
                        return "schedule";
                    case 2:
                        return "map";
                    case 3:
                        return "find room owner";
                    default:
                        return null;
                }
            }
        }
    }