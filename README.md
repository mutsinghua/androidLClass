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
