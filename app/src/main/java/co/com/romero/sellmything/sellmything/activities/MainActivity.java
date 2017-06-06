package co.com.romero.sellmything.sellmything.activities;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import co.com.romero.sellmything.sellmything.MyConstants;
import co.com.romero.sellmything.sellmything.R;
import co.com.romero.sellmything.sellmything.fragments.BaseFragment;
import co.com.romero.sellmything.sellmything.fragments.CameraFragment;
import co.com.romero.sellmything.sellmything.fragments.MainActivityFragment;

public class MainActivity extends CameraActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFragment(MyConstants.MAIN_FRAGMENT);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                setFragment(MyConstants.CAMERA_FRAGMENT);
            }
        });
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


    private void setFragment(int idFragment){

        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        BaseFragment targetFragment = null;

        // Populate the fragment
        switch (idFragment) {
            case CameraFragment.ID: {
                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                fab.setVisibility(View.INVISIBLE);
                targetFragment = CameraFragment.newInstance();
                break;
            }
            case MainActivityFragment.ID: {
                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                fab.setVisibility(View.VISIBLE);
                targetFragment = MainActivityFragment.newInstance();
                break;
            }
            default:
                break;
        }

        // Select the fragment.
        fragmentManager.beginTransaction()
                .replace(R.id.container, targetFragment)
                .commit();
    }
}
