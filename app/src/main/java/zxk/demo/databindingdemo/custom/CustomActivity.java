package zxk.demo.databindingdemo.custom;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.databinding.library.baseAdapters.BR;

import zxk.demo.databindingdemo.R;
import zxk.demo.databindingdemo.databinding.ActivityCustomBinding;

/**
 * Created by zxk on 2018/8/2.
 * Description: 双向绑定 之 自定义
 */
public class CustomActivity extends AppCompatActivity {

    private ActivityCustomBinding binding;
    private String num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_custom);
        num = "123";
        binding.setVariable(BR.num, num);
    }
}
