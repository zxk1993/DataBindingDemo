package zxk.demo.databindingdemo.main;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import zxk.demo.databindingdemo.BR;
import zxk.demo.databindingdemo.DataBindingViewHolder;
import zxk.demo.databindingdemo.R;

/**
 * Created by zxk on 2018/8/2.
 * Description:
 */
public class MainAdapter extends RecyclerView.Adapter<DataBindingViewHolder> {

    private static final String ACTION_PRE = "zxk.demo.action.";
    private String[] types = new String[]{"common", "custom"};
    private List<ItemType> mDatas;

    public MainAdapter() {
        mDatas = new ArrayList<>();
        initData();
    }

    private void initData() {
        mDatas.clear();
        for (String type : types) {
            mDatas.add(new ItemType(type, ACTION_PRE + type));
        }
    }

    @Override
    public DataBindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.layout_item,
                parent,
                false);
        binding.setVariable(BR.presenter, new Presenter());
        DataBindingViewHolder holder = new DataBindingViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(DataBindingViewHolder holder, int position) {
        holder.getBinding().setVariable(BR.item, mDatas.get(position));
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }
}
