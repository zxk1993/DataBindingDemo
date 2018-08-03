package zxk.demo.databindingdemo.common;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import zxk.demo.databindingdemo.BR;

/**
 * Created by zxk on 2018/8/2.
 * Description:
 */
public class User extends BaseObservable {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Bindable
    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        notifyPropertyChanged(BR.age);
    }
}
