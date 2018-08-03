package zxk.demo.databindingdemo.common;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

import zxk.demo.databindingdemo.R;
import zxk.demo.databindingdemo.databinding.ActivityCommonBinding;

/**
 * Created by zxk on 2018/8/2.
 * Description: 双向绑定 之 系统自带控件
 */
public class CommonActivity extends AppCompatActivity {

    private ActivityCommonBinding binding;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_common);
        user = new User("zxk 25", 25);
        binding.setUser(user);
    }

    public void changeModel(View view) {
        int age = new Random().nextInt(100);
        binding.setUser(new User("zxk " + age, age));
    }

    public void changeView(View view) {
        binding.name.setText("zxk 25");
        binding.age.setText("age = " + 25);
        Toast.makeText(CommonActivity.this, "name:" + user.getName() + ",age:" + user.getAge(), Toast.LENGTH_SHORT).show();
    }
}
