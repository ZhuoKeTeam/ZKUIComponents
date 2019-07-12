# ZKUIComponents
[![](https://jitpack.io/v/ZhuoKeTeam/ZKUIComponents.svg)](https://jitpack.io/#ZhuoKeTeam/ZKUIComponents)

## 说明

这个项目的目的是为了将公用的 UI 组件封装到里面，例如：启动页面，关于我们等等。

创建一个 app, 我们很有可能需要一些基础页面，而这些基础页面有时候很鸡肋，但是又不得不用。

所以封装下来后，如果需要直接启动对应的 Activity，不需要就先放着，可以在需要的时候再使用。

做一些基础页面，后面会添加多套模板进行动态替换，可以有多种风格。


## 显示屏幕相关信息的 Dialog

```
ScreenInfoDialog().show(this)
```

## 调整到默认的 WebView 界面中

```
private fun startWebViewActivity(url: String) {
    startActivity(Intent(this, ZKWebViewActivity::class.java)
        .putExtra(ZKWebViewActivity.FLAG_ZK_UI_WEBVIEW_URL, url))
}
```

## SplashActivity 闪屏页面

通过 permissions 数组对象定义需要申请动态的权限 

```
private String[] permissions = new String[]{Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE};
```

**注意：权限同时需要在清单文件中声明**

## 添加 ZKViewPager UI 组件

1. 简单使用方式：

添加布局：
```
        <com.zkteam.ui.components.viewpager.ZKViewPager
            android:id="@+id/zkViewPager"
            android:layout_width="match_parent"
            android:layout_height="match_parent"/>
```

简单直接配置使用（默认有动画）：
```
        zkViewPager.viewPager.adapter = object : ZKFragmentAdapter(this) {
            override fun getItemCount(): Int {
                return 10
            }

            override fun createFragment(position: Int): Fragment {
                return TestFragment.create(position)
            }
        }
```


2. 详细使用参数：

```
        val builder = ZKViewPagerTransformerAnimation.Builder()
        builder.setTranslation(true)    // 支持翻转
        builder.setTranslationX(100F)   // X 轴的翻转距离
        builder.setTranslationY(100F)   // Y 轴的翻转距离
        builder.setRotation(true)       // 支持旋转
        builder.setRotationCircle(90F)  // 旋转的角度
        builder.setScale(true)          // 支持缩放
        zkViewPager.viewPager.setPageTransformer(ZKViewPagerTransformerAnimation(builder, zkViewPager.viewPager)) // builder 可以为空， 
        zkViewPager.setZKAnimationBuilder(builder) //添加动画的 builder
        zkViewPager.showZKAnimation(false)  //禁用动画效果，默认开启
        zkViewPager.viewPager.adapter = object : ZKFragmentAdapter(this) {
            override fun getItemCount(): Int {
                return 10                               //列表中 Fragment 的总数
            }

            override fun createFragment(position: Int): Fragment {
                return TestFragment.create(position)    //创建 Fragment
            }
        }
```

使用 builder 添加相关参数。