package com.github.mvvmtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.mvvmtest.baseadapter.ZLBindingAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private List<User> users = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        users.add(new User("刘亦菲","0",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541069934582&di=ae66155eb7cad0d71baa82498539fc52&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201411%2F23%2F20141123205812_Bircn.jpeg"));
        users.add(new User("刘亦菲","1",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541069934582&di=ae66155eb7cad0d71baa82498539fc52&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201411%2F23%2F20141123205812_Bircn.jpeg"));
        users.add(new User("刘亦菲","2",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541069934582&di=ae66155eb7cad0d71baa82498539fc52&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201411%2F23%2F20141123205812_Bircn.jpeg"));
        users.add(new User("刘亦菲","3",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541069934582&di=ae66155eb7cad0d71baa82498539fc52&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201411%2F23%2F20141123205812_Bircn.jpeg"));
        users.add(new User("刘亦菲","4",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541069934582&di=ae66155eb7cad0d71baa82498539fc52&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201411%2F23%2F20141123205812_Bircn.jpeg"));
        users.add(new User("刘亦菲","5",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541069934582&di=ae66155eb7cad0d71baa82498539fc52&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201411%2F23%2F20141123205812_Bircn.jpeg"));
        ZLBindingAdapter<User> bindingAdapter = new ZLBindingAdapter(users, BR.user, R.layout.item_belle);
        recyclerView.setAdapter(bindingAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }

}
