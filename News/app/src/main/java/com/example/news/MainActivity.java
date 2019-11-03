package com.example.news;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_design);
        View v = navigationView.getHeaderView(0);
        CircleImageView circleImageView = v.findViewById(R.id.icon_image);
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);
        list = new ArrayList<>();
    }
    @Override
    protected void onStart(){
        super.onStart();
        toolbar.setTitle("今日头条");
        setSupportActionBar(toolbar);//将Toolbar实例传入
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            //通过HomeAsUp来让导航按钮显示出来
            actionBar.setDisplayHomeAsUpEnabled(true);//设置导航按钮
            //设置Indicator来添加一个点击图标
            actionBar.setHomeAsUpIndicator(R.color.colorpink);//设置菜单点击按钮的颜色
        }
        //设置菜单项的监听
        list.add("头条");
        list.add("社会");
        list.add("国内");
        list.add("国际");
        list.add("娱乐");
        list.add("体育");
        list.add("军事");
        list.add("科技");
        list.add("财经");
        //得到当前页的标题，设置当前的viewPager页面是tabLayout对应的标题
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public CharSequence getPageTitle(int position){//CharSequence与String都能用于定义字符串，
                // 但CharSequence的值是可读可写序列，而String的值是只读序列。
                return list.get(position);
            }
            @Override
            public Fragment getItem(int position) {
                NewsFragment newsFragment = new NewsFragment();
                //判断所选的标题，进行传值显示
                Bundle bundle = new Bundle();
                if(list.get(position).equals("头条")){
                    bundle.putString("name","top");
                }else if(list.get(position).equals("社会")){
                    bundle.putString("name","shehui");
                }else if(list.get(position).equals("国内")){
                    bundle.putString("name","guonei");
                }else if(list.get(position).equals("国际")){
                    bundle.putString("name","guoji");
                }else if(list.get(position).equals("娱乐")){
                    bundle.putString("name","yule");
                }else if(list.get(position).equals("体育")){
                    bundle.putString("name","tiyu");
                }else if(list.get(position).equals("军事")){
                    bundle.putString("name","junshi");
                }else if(list.get(position).equals("科技")){
                    bundle.putString("name","keji");
                }else if(list.get(position).equals("财经")){
                    bundle.putString("name","caijing");
                }else if(list.get(position).equals("时尚")){
                    bundle.putString("name","shishang");
                }
                newsFragment.setArguments(bundle);
                return newsFragment;
            }
            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container,int position){
                NewsFragment newsFragment =(NewsFragment)super.instantiateItem(container,position);
                return newsFragment;
            }

            @Override
            public int getCount() {
                return list.size();
            }
            @Override
            public int getItemPosition(@NonNull Object object){
                return FragmentStatePagerAdapter.POSITION_NONE;
            }
        });
    }
}
