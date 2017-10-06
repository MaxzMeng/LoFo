package maxandroid.me.lofo;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.bmob.v3.Bmob;

public class MainActivity extends AppCompatActivity {
    private LostFragment lostFragment;
    private AddFragment addFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this, "48153a3ebbf1f6aa65d64018ad371d1d");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        if (lostFragment == null) {
            lostFragment = new LostFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fl_container, lostFragment, "lostfragment").commitAllowingStateLoss();
        }

    }
}
