package zxk.demo.databindingdemo;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by zxk on 2018/8/2.
 * Description:
 */
public class DataBindingViewHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding binding;

    public DataBindingViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public ViewDataBinding getBinding() {
        return binding;
    }
}
