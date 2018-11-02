package com.github.mvvmtest;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.mvvmtest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private User user;
    private String[] names = {"张三","李四","王五","赵六"};
    private int tag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        user = new User("张三","123456","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541069934582&di=ae66155eb7cad0d71baa82498539fc52&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201411%2F23%2F20141123205812_Bircn.jpeg");
        binding.setUser(user);
    }

    public void clickbutton(View view) {
        tag++;
        if (tag > 3) {
            tag = 0;
        }
        user.setName(names[tag]);
    }

    public void onNext(View view) {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
}
