package zxk.demo.databindingdemo.common;

import android.databinding.InverseMethod;
import android.widget.TextView;

/**
 * Created by zxk on 2018/8/2.
 * Description:
 */
public class CommonPresenter {

    @InverseMethod("switchToInt")
    public static String switchToString(int age) {
        return "age = " + age;
    }


    public static int switchToInt(String age) {
        String result = age.substring(6);
        return Integer.parseInt(result);
    }
}
