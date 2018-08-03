package zxk.demo.databindingdemo.main;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import zxk.demo.databindingdemo.R;
import zxk.demo.databindingdemo.databinding.ActivityMainBinding;


/**
 * Created by zxk on 2018/8/2.
 * Description:
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setAdapter(new MainAdapter());
        binding.setLayoutManager(new LinearLayoutManager(this));
    }
}
