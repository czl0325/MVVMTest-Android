package com.github.mvvmtest;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.renderscript.ScriptGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class User extends BaseObservable {
    private String name;
    private String password;
    private String avator;

    public User(String name, String password, String avator) {
        this.name = name;
        this.password = password;
        this.avator = avator;
    }

    @BindingAdapter("bind:avator")
    public static void getImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).into(view);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }
}
