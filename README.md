# MVVMTest-Android
一步一步教你实现安卓下mvvm架构，双向数据绑定。


google 2015年9月推出了mvvm架构，实现了在xml上设置双向数据绑定，类似js。

开发步骤：

1   首先要先在build.gradle内添加

```
dataBinding {
    enabled true
}
```

![](https://img-blog.csdnimg.cn/20181101155347895.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2N6bDAzMjU=,size_16,color_FFFFFF,t_70)


2.  建立一个Model类测试，如下，建立一个user类，带有三个参数，名字，密码，头像

```
public class User {
    private String name;
    private String password;
    private String avator;
 
    public User(String name, String password, String avator) {
        this.name = name;
        this.password = password;
        this.avator = avator;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getAvator() {
        return avator;
    }
 
    public void setAvator(String avator) {
        this.avator = avator;
    }
}
```

3  编写xml

这里的xml和之前有不同，首先外面有一层layout标签，
data中name表示你在MainActivity类中要绑定的数据对象。 type为该对象的完整包路径
然后如代码将你的控件和user对应的属性绑定。

```
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">
    <data>
        <variable
            name="user"
            type="com.github.mvvmtest.User"/>
    </data>
 
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:paddingVertical="20dp"
        android:orientation="vertical"
        android:gravity="center_horizontal">
 
        <ImageView
            android:layout_width="40dp"
            android:layout_height="40dp"
            app:header="@{user.avator}"
            />
 
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:gravity="center_horizontal"
            android:text="@{user.name}"/>
 
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:gravity="center_horizontal"
            android:text="@{user.password}"/>
 
    </LinearLayout>
 
</layout>
```
