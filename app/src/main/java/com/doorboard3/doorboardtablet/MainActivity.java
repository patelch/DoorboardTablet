    package com.doorboard3.doorboardtablet;

    import android.content.Intent;
    import android.support.design.widget.TabLayout;
    import android.support.v7.app.AppCompatActivity;
    import android.support.v7.widget.Toolbar;

    import android.support.v4.app.Fragment;
    import android.support.v4.app.FragmentManager;
    import android.support.v4.app.FragmentPagerAdapter;
    import android.support.v4.view.ViewPager;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.view.View;
    import android.view.ViewGroup;
    import android.view.animation.Animation;

    import android.view.animation.AnimationUtils;


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

            // Create the adapter that will return a fragment for each of the three
            // primary sections of the activity.
            mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

            // Set up the ViewPager with the sections adapter.
            mViewPager = (ViewPager) findViewById(R.id.container);
            mViewPager.setAdapter(mSectionsPagerAdapter);

            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(mViewPager);

        }


        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
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
                // getItem is called to instantiate the fragment for the given page.
                // Return a PlaceholderFragment (defined as a static inner class below).
    //            return PlaceholderFragment.newInstance(position + 1);

                InfoFragment infoFragment = new InfoFragment();
                switch (position) {
                    case 0:
                        return infoFragment;
                    case 1:
                        return infoFragment;
                    case 2:
                        return infoFragment;
                    case 3:
                        return infoFragment;
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
                        return "map";
                    case 2:
                        return "calendar";
                    case 3:
                        return "search";
                    default:
                        return null;
                }
            }
        }
    }