## Material Design 实现之主题使用Theme ##

Material Design 是Google在Android L推出来的一套新的设计规范，
有着鲜明的色彩，极致的用户体验，酷炫的动画。

本系例将带给大家一套完整的Material实现方案。
预计本系例将覆盖以下几个主题：
##### 1.MaterialDesign主题的使用
##### 2.Toolbar的应用，及其菜单动画
##### 3.属性动画的基本介绍（基础)
##### 4.Transition场景动画
##### 5.Activity的切换动画
##### 6.reveal动画
##### 7.触动反馈之Ripple的使用
##### 8.触动反馈之响应动画
##### 9.阴影介绍与CardView
##### 10.RecycleView的使用
##### 11.RecycleView的特效
##### 12.AndroidL风格的下拉刷新


这是本系例的第一篇，主题的使用。


##原生主题的使用

在Android 5.0中，新引入了以Material为关键字的主题。
    
    @android:style/Theme.Material
    @android:style/Theme.Material.Light
    @android:style/Theme.Material.Light.DarkActionBar
    
使用方式都是在styles.xml的定义中，
	
    
    <style name="MaterialTheme" 	parent="android:Theme.Material">
    
    </style>

    
但注意，这个定义不能放在values中，只能放在values-v21中。因为低版本的机型不识别这个主题。


因此，为了兼容低于androidL的机型，通常的做法，需要在values中，添加一个中间主题CustomCompactTheme。

对于values-21(高于5.0的版本), CustomCompactTheme可继承android.Theme.Material,

    <style name="CustomeCompatTheme" parent="android:Theme.Material">
    
    </style>

对于values(低于5.0的版本)，CustomCompactTheme可继承Holo。

    <style name="CustomeCompatTheme" parent="android:Theme.Holo">
    
    </style>

完整代码如下：
values-v21/styles.xml

    <?xml version="1.0" encoding="utf-8"?>
    <resources>
     <!--定义基础主题 -->
    <style name="CustomeCompatTheme" parent="android:Theme.Material">
    
    </style>
    
    </resources>


values/styles.xml


    <resources>
    <!--定义基础主题 -->
    <style name="CustomeCompatTheme" parent="android:Theme.Holo">
    
    </style>
    
    <!--定义使用的主题-->
    <!-- Base application theme. -->
    <style name="AppTheme" parent="CustomeCompatTheme">
    <!-- Customize your theme here. -->
    </style>
    
    
    </resources>

##兼容低版本主题的使用

幸运的是在android-support-v7包（21版以上）已经为我们实现了兼容方案，只要引入相关的主题即可。

加入v7包，在gradle.build文件的dependencies节点中加入

    compile 'com.android.support:appcompat-v7:23.0.0'


则styles.xml可以精简为

    
    <style name="AppTheme" parent="Theme.AppCompat.NoActionBar">
    <!-- customize the color palette -->
    </style>



##主题的配色

在theme中，我们可以为系统界面自定义一些配色，见下图。

![这里写图片描述](http://img.blog.csdn.net/20150916165811504)


这些配色，可以在主题中进行设置如

    <item name="colorPrimary">#675634</item>
    <item name="colorPrimaryDark">#993309</item>
    <item name="colorAccent">#7767ff</item>
    <item name="android:textColorPrimary">#ffff33</item>
    <item name="android:navigationBarColor">#44ef54</item>

效果图：

![这里写图片描述](http://img.blog.csdn.net/20150916165919320)

同样，在代码设置如下方式

    getWindow().setStatusBarColor(0xff873434);
    getWindow().setNavigationBarColor(0xff345644);

效果图





###ThemeOverlay

通常情况下，主题只能应用到全局，粒度最低只能到Activity这一层次，
那如果说只想把界面中的某一个布局使用主题，怎么办？这在以前是行不通的，但现在新引入Overlay系列主题就能使用到某一个ViewGroup上面，如：

    <LinearLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:theme="@style/ThemeOverlay.AppCompat.Dark"
    >
    <Button
    android:layout_width="wrap_content"
    android:text="33333"
    android:layout_height="wrap_content" />
    
    <CheckBox
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="New CheckBox"
    android:id="@+id/checkBox"
    android:layout_below="@+id/button"
    android:layout_alignParentStart="true"
    android:layout_marginTop="51dp" />
    </LinearLayout>


这样LinearLayout所有的子元素都将继承ThemeOverlay.AppCompat.Dark的风格。



###使用预定义的数值

为了与保持整个应用程序的统一，有时我们需要使用系统预定义的值，比如不同的主题下，ActionBar的高度或不一样，则为了兼容各种主题，则我们在代码中不需要把高度写死，应该使用引用的方式的设置高度，如


    <Toolbar  
    android:layout_height="?android:attr/actionBarSize"
    android:layout_width="match_parent"
    android:background="?android:attr/colorPrimaryDark"
     />

如上所示，同样的，在必要的时候，我们也需要引用主题的颜色，这样当换一个主题的时候，UI的颜色就随着变改，增强代码的灵活性。

源码下载：
[https://github.com/mutsinghua/androidLClass](https://github.com/mutsinghua/androidLClass)

在Android5.0中，Google推出了更加灵活的Toolbar来取代ActionBar。Toolbar的优点是更加灵活，比如以前一个界面只能设置一个ActionBAR，但可以有多个Toolbar。而Toolbar同样可以设置菜单，颜色图标等，可以自定义的元素比ActionBAR多。因此，在Android应用程序中使用toolbar是一种趋势。

为了提高程序的兼容性，我们通常都使用V7包中的Toolbar（当然如果你的应用只需要在Android5.0上运行，则可以直接使用android.widget.Toolbar），要在程序中使用toolbar，则需要在gradle脚本中加上依赖项：

```
compile 'com.android.support:appcompat-v7:23.0.0'
```

如果是使用eclipse开发，则需要加入appcompat的库工程。


使用Toolbar的注意事项，
1. 由于Actionbar和Toolbar本身是有冲突的，因此，如果使用了Toolbar，则需要使用不带Actionbar的主题
如：Theme.AppCompat.NoActionBar。
2. 由于使用的v7中的toolbar，因此，activity的基类也需要是v7中的AppCompatActivity。
3. 由于使用了AppCompatActivity对应的主题也需要是v7中的Theme.AppCompat.Light.NoActionBar

先看简单的效果图.


![这里写图片描述](http://img.blog.csdn.net/20151009234256183)
布局文件：

```
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools" android:layout_width="match_parent"
    android:layout_height="match_parent" tools:context=".MainActivity">

    <android.support.v7.widget.Toolbar

        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        android:minHeight="?attr/actionBarSize"
        android:background="?attr/colorPrimary"
        ></android.support.v7.widget.Toolbar>

    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"></RelativeLayout>
</RelativeLayout>
```

MainActivity

```
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
```

这里要介绍的是setSupportActionBar(对应Activity的setActionBar)这个方法，它是在AppCompatActivity 中的，我们需要调用它，把布局中的Toolbar设置为actionbar。

如此简单的，就完成了一个toolbar的实现。如果你需要对Toolbar进行自定义，比如修改背景色，只需要在对应的属性上修改就好，如

```
android:background="?attr/colorPrimary"
```
当然，目前的实现仅仅是框架的一部分。我们继续添加常见的侧滑菜单，即android.support.v4.widget.DrawerLayout。

Drawerlayout包括两个子元素，第一个是主界面，即正常显示的界面，第二元素是侧滑菜单的布局。
我们要实现的界面如下，当菜单展开：
![这里写图片描述](http://img.blog.csdn.net/20151009235132823)
当菜单关闭
![这里写图片描述](http://img.blog.csdn.net/20151009235203658)

我们的布局文件如下：

```
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.win16.materialtheme.MaterialActivity">

    <android.support.v7.widget.Toolbar

        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        android:minHeight="?attr/actionBarSize"
        app:contentInsetLeft="72dp"
        app:contentInsetStart="72dp"
        android:background="?attr/colorPrimary"
        ></android.support.v7.widget.Toolbar>


    <android.support.v4.widget.DrawerLayout
        android:id="@+id/drawer"
        android:layout_below="@id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
        <!--第一个元素,主界面 -->
        <RelativeLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent">
            <Button
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="New Button"
                android:id="@+id/button"
                android:layout_marginTop="55dp"
                android:layout_alignParentTop="true"
                android:layout_alignParentStart="true" />
        </RelativeLayout>
         <!--第二个元素,侧滑菜单 -->
        <LinearLayout
            android:id="@+id/menu_frame"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_gravity="start|left"
            android:layout_marginEnd="10dip"
            android:background="#087654"
            android:layout_marginRight="10dip"
            android:orientation="vertical"
            android:paddingTop="@dimen/abc_action_bar_default_height_material" />
        </android.support.v4.widget.DrawerLayout>


</RelativeLayout>
```

对于主界面

```
public class MaterialActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        mDrawerToggle =
                new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close) {
                    public void onDrawerClosed(View view) {
                        super.onDrawerClosed(view);
                    }

                    public void onDrawerOpened(View drawerView) {
                        super.onDrawerOpened(drawerView);

                    }

                };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
```

这里除了DrawerLayout, 还引入了ActionBarDrawerToggle，它其实是一个DrawerListener，映应菜单展开和关闭的事件，还有一点，它实现了菜单图标的动画过渡。有兴趣的同学可以看看它的源码，这个动画图标主要是v7包中的DrawerArrowDrawable这个类来实现的。

一个通用的框架到这里理论就差不多了。但这里在深入扩展下，对于测滑菜单，除了自已实现之外，可以使用google为我们提供的Desgin包中的控件NavigationView，使用方法非常简单，如下：

```
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.win16.materialtheme.MaterialActivity">

    <android.support.v7.widget.Toolbar

        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        android:minHeight="?attr/actionBarSize"
        app:contentInsetLeft="72dp"
        app:contentInsetStart="72dp"
        android:background="?attr/colorPrimary"
        ></android.support.v7.widget.Toolbar>


    <android.support.v4.widget.DrawerLayout
        android:id="@+id/drawer"
        android:layout_below="@id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
        <RelativeLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical"
            android:padding = "10dp"
            >

            <Button
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="New Button"
                android:id="@+id/button"
                android:layout_marginTop="55dp"
                android:layout_alignParentTop="true"
                android:layout_alignParentStart="true" />

            <CheckBox
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="New CheckBox"
                android:id="@+id/checkBox"
                android:layout_below="@+id/button"
                android:layout_alignParentStart="true"
                android:layout_marginTop="51dp" />

            <Switch
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="New Switch"
                android:id="@+id/switch1"
                android:layout_centerVertical="true"
                android:layout_alignParentStart="true" />

            <ProgressBar
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:id="@+id/progressBar"
                android:layout_below="@+id/switch1"
                android:layout_alignParentStart="true"
                android:layout_marginTop="55dp" />
        </LinearLayout>

    <android.support.design.widget.FloatingActionButton
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="right|bottom"
        android:src="@drawable/ic_menu_share"
        android:layout_alignParentRight="true"
        android:layout_alignParentBottom="true"
        android:layout_margin = "20dp"
        app:elevation="6dp"
        app:pressedTranslationZ="12dp"
        />
        </RelativeLayout>
        <!--取代自定义的菜单-->
        <android.support.design.widget.NavigationView
                android:id="@+id/navigation"
                android:layout_width="wrap_content"
                android:layout_height="match_parent"
                android:layout_gravity="start"
                android:fitsSystemWindows="true"
                app:headerLayout="@layout/drawer_header"
                app:menu="@menu/my_navigation_items" />
        </android.support.v4.widget.DrawerLayout>
    
</RelativeLayout>
```

app:menu中写入你自定义的菜单项，在headerLayout定义你的菜单头部，通常侧滑菜单看起来像

![这里写图片描述](http://img.blog.csdn.net/20151010002208219)
详细使用方法可以参考：
http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0608/3011.html


本文中的源码可在github上下载：
https://github.com/mutsinghua/androidLClass





