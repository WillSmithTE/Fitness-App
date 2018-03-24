package grp2.fitness;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import grp2.fitness.Helpers.AccountHandler;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        AccountHandler accountHandler = new AccountHandler();
        checkLogin(accountHandler);

        mDrawerLayout   = findViewById(R.id.drawerLayout);
        navigationView  = findViewById(R.id.navMenu);

        drawerToggle    = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);

        mDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        //WHAT TO DO ONCE MENU ITEM HAS BEEN SELECTED

                        return true;
                    }
                }
        );
    }

    private void checkLogin(AccountHandler accountHandler){
        if(!accountHandler.isSignedIn()){
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
