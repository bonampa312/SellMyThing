package co.com.romero.sellmything.sellmything.activities;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import co.com.romero.sellmything.sellmything.utilities.MyConstants;
import co.com.romero.sellmything.sellmything.R;
import co.com.romero.sellmything.sellmything.fragments.BaseFragment;
import co.com.romero.sellmything.sellmything.fragments.CameraFragment;
import co.com.romero.sellmything.sellmything.fragments.MainActivityFragment;

public class MainActivity extends CameraActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SellMyThing.setContext(getApplicationContext());
        setContentView(R.layout.activity_main);
        setFragment(MyConstants.MAIN_FRAGMENT);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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


    public void setFragment(int idFragment){

        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        BaseFragment targetFragment = null;

        // Populate the fragment
        switch (idFragment) {
            case CameraFragment.ID: {
                targetFragment = CameraFragment.newInstance();
                break;
            }
            case MainActivityFragment.ID: {
                targetFragment = MainActivityFragment.newInstance();
                break;
            }
            default:
                break;
        }
        // Select the fragment.
        fragmentManager.beginTransaction()
                .replace(R.id.container, targetFragment).addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed(){
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            Log.i("MainActivity", "popping backstack");
            fm.popBackStack();
        } else {
            Log.i("MainActivity", "nothing on backstack, calling super");
            super.onBackPressed();
        }
    }
}
