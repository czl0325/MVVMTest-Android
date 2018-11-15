# MVVMTest-Android
一步一步教你实现安卓下mvvm架构，双向数据绑定。


google 2015年9月推出了mvvm架构，实现了在xml上设置双向数据绑定，类似js。

开发步骤：

##  普通界面的数据绑定

1   首先要先在build.gradle内添加

```JAVA
dataBinding {
    enabled true
}
```

<img src="https://img-blog.csdnimg.cn/20181101155347895.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2N6bDAzMjU=,size_16,color_FFFFFF,t_70" width="320"/>


2.  建立一个Model类测试，如下，建立一个user类，带有三个参数，名字，密码，头像

```JAVA
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

```HTML
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

4. MainActivity 类

注意几点， setContentView写法不一样了。

新的写法是：ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

ActivityMainBinding的意思是，比如你的xml叫activity_main，系统编译时候会帮你生成ActivityMainBinding的类，命名为首字母大写，去掉下划线，然后最后加上Binding，把ActivityMainBinding对象和视图绑定。

```JAVA
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        user = new User("张三","123456","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541069934582&di=ae66155eb7cad0d71baa82498539fc52&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201411%2F23%2F20141123205812_Bircn.jpeg");
        binding.setUser(user);
    }
```

此时运行，发现两个textview都正常显示数据了。
你不在需要findviewbyid，也不在需要设置id，也不用写setText方法了，实现了数据绑定。

<img src="https://img-blog.csdnimg.cn/20181101161649900.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2N6bDAzMjU=,size_16,color_FFFFFF,t_70" width="320"/>

5. 接下来我们要改变user的值，然后textview的值也会自动改变，实现双向数据绑定！

首先修改user类，在变量的get方法加上@Bindable注解，set方法中加入notifyPropertyChanged(BR.name);  BR是系统编译生成的类，name是你绑定的变量名。只要你加入@Bindable，你的变量就会被系统加入到BR类中。


```JAVA
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
```

这样我们在button事件中修改下user的值看看。

```JAVA
public void clickbutton(View view) {
        tag++;
        if (tag > 3) {
            tag = 0;
        }
        user.setName(names[tag]);
    }
```

<img src="https://img-blog.csdnimg.cn/20181101162704672.gif" width="320"/>

看到没，只要修改user的model对象，控件绑定的值自动改变了！！！是不是很方便。
当然我们也可以在控件text前面加上自己的文字，写法是：

```
android:text="@{`姓名是:`+user.name}"
```

6   接下来要做网络加载头像的数据绑定了 

在user类中加入一个方法

```JAVA
@BindingAdapter("bind:avator")
    public static void getImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).into(view);
    }
```

注意一定要是静态方法。
然后在xml绑定到imageview

```html
<ImageView
            android:layout_width="80dp"
            android:layout_height="80dp"
            android:layout_marginTop="20dp"
            android:scaleType="fitXY"
            android:background="@color/colorPrimary"
            app:avator="@{user.avator}"
            />
```

看效果

<img src="https://img-blog.csdnimg.cn/20181102090259479.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2N6bDAzMjU=,size_16,color_FFFFFF,t_70" width="320"/>

出来了吧，数据的双向绑定！


##  recyclerview的数据绑定

1. 创建一个通用的adapter类，采用泛型来传入对应的model

```JAVA
public class ZLBindingAdapter<T> extends RecyclerView.Adapter<ZLBindingAdapter.BindingHolder> {
    private List<T> items ;
    private int variableId;
    private int layoutId;
 
    public ZLBindingAdapter(List<T> items, int variableId, int layoutId) {
        this.items = items;
        this.variableId = variableId;
        this.layoutId = layoutId;
    }
 
    @NonNull
    @Override
    public BindingHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), layoutId, viewGroup, false);
        return new BindingHolder(binding);
    }
 
    @Override
    public void onBindViewHolder(@NonNull BindingHolder bindingHolder, int i) {
        bindingHolder.bindData(items.get(i));
    }
 
    @Override
    public int getItemCount() {
        return items.size();
    }
 
    public class BindingHolder<T> extends RecyclerView.ViewHolder {
        ViewDataBinding binding;
        /**
         * @param binding   可以看作是这个hodler代表的布局的马甲，getRoot()方法会返回整个holder的最顶层的view
         * */
        public BindingHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
 
        public void bindData(T item) {
            binding.setVariable(variableId ,item);
        }
 
    }
}
```

items就是我们的数据列表，这里我们是user的数据

variableId就是绑定的model对象，比如我们绑定user这个对象，variableId就是BR.user

layoutId是我们布局文件的id。


2. 然后我们的activity是这么写的。

```JAVA
recyclerView = findViewById(R.id.recyclerview);
recyclerView.setLayoutManager(new LinearLayoutManager(this));
ZLBindingAdapter<User> bindingAdapter = new ZLBindingAdapter(users, BR.user, R.layout.item_belle);
recyclerView.setAdapter(bindingAdapter);
recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
```

3. 看效果：

<img src="https://img-blog.csdnimg.cn/20181102114656482.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2N6bDAzMjU=,size_16,color_FFFFFF,t_70" width="320"/>

强大吧，列表出来了。

我们要什么类型，只要对应的泛型传入即可，
以前我们是一个recyclerview写一个adapter，还用了很多简化版的第三方库来简化adapter，
现在我们所有recyclerview通用一个adapter！！节省了90%的代码量，强大吧！！！
