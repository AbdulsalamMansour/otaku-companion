package com.example.abdulsalam.otakucompanion;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.abdulsalam.otakucompanion.Fragments.MainDisplayFragment;
import com.example.abdulsalam.otakucompanion.Fragments.ViewByGenreFragment;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainDisplayActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    Matcher matcher ;
    Pattern pattern;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_display);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


         pattern = Pattern.compile(".*[a-zA-Z]+.*");


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //---------------------------------------------------------------------------------------------


            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new MainDisplayFragment()).commit();
            navigationView.setCheckedItem(R.id.top_anime);

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            FragmentManager fragmentManager = this.getSupportFragmentManager();
            fragmentManager.popBackStack();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem menuItem = menu.findItem(R.id.search_menu);

        final SearchView searchView =(SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                matcher = pattern.matcher(s);
                if(s.length() < 3){
                    Toast.makeText(MainDisplayActivity.this, "Search word must be longer than 3 letters", Toast.LENGTH_SHORT).show();
                }else if (!(matcher.matches())) {
                    Toast.makeText(MainDisplayActivity.this, "String Must Contain at Least One Letter", Toast.LENGTH_SHORT).show();
                }else{
                    MainDisplayFragment mainDisplayFragment = new MainDisplayFragment();

                    Bundle bundle = new Bundle();

                    bundle.putString("search_keyword", s);

                    int size = navigationView.getMenu().size();
                    for (int i = 0; i < size; i++) {
                        navigationView.getMenu().getItem(i).setChecked(false);
                    }

                    searchView.setQuery("",false);

                    searchView.setIconified(true);
                    mainDisplayFragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            mainDisplayFragment).addToBackStack(null).commit();

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.top_anime) {



            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new MainDisplayFragment()).addToBackStack(null).commit();


        } else if (id == R.id.by_genre) {


            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ViewByGenreFragment()).addToBackStack(null).commit();


        } else if (id == R.id.favorites) {


            MainDisplayFragment mainDisplayFragment = new MainDisplayFragment();

            Bundle bundle = new Bundle();

            bundle.putBoolean("favorites",true);

            mainDisplayFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    mainDisplayFragment).addToBackStack(null).commit();




        }  else if (id == R.id.nav_share) {

            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "Here is the share content body";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));

        } else if (id == R.id.logout) {

            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }



        DrawerLayout drawer =findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }






}
