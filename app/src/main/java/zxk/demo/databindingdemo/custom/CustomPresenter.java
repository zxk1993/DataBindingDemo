package zxk.demo.databindingdemo.custom;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;

/**
 * Created by zxk on 2018/8/6.
 * Description:
 */
public class CustomPresenter {

    @InverseBindingAdapter(attribute = "num")
    public static String getNum(NumberEditView view) {
        return view.getNum();
    }

    @BindingAdapter("num")
    public static void setNum(NumberEditView view, String num) {
        if (!num.equals(view.getNum())) {
            view.setNum(num);
        }
    }

    @BindingAdapter("numAttrChanged")
    public static void setNumChangeListener(NumberEditView view, final InverseBindingListener listener) {
        if (listener == null) {
            view.setOnNumChangeListener(null);
        } else {
            view.setOnNumChangeListener(new NumberEditView.OnNumChangeListener() {
                @Override
                public void onNumChange() {
                    listener.onChange();
                }
            });
        }
    }
}
