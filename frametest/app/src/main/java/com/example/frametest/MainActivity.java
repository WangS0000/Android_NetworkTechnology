package com.example.frametest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

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
        if(Build.VERSION.SDK_INT>=21){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colornothing));
        }
        toolbar = findViewById(R.id.toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);//获取抽屉布局
        navigationView = findViewById(R.id.design);//获取菜单控件实例
        View v = navigationView.getHeaderView(0);//获取header
        //NavigationView是一个RecyclerView（在23.1.0版本之前是ListView），header布局通常是0号元素。
        CircleImageView circleImageView = v.findViewById(R.id.icon_image);//获取滑动菜单栏个人信息主页实例
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        list = new ArrayList<>();
    }
    @Override
    protected void onStart(){
        super.onStart();
        toolbar.setTitle("               今日头条");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            //设置actionBar是否可用
            actionBar.setHomeAsUpIndicator(R.color.colororange);
            //设置点击按钮的颜色
        }
        navigationView.setCheckedItem(R.id.call);//设置第一个默认选中
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem){
                mDrawerLayout.closeDrawers();
                switch(menuItem.getItemId()){
                    case R.id.call:
                        //暂时编辑资料还没有功能
                        break;
                    case R.id.friends:
                        Toast.makeText(MainActivity.this, "点击了好友", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.location:
                        Toast.makeText(MainActivity.this, "点击了发布新闻", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.favourite:
                        Toast.makeText(MainActivity.this, "点击了个人收藏", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.setting:
                        Toast.makeText(MainActivity.this, "需要做出登出功能，可以扩展做出夜间模式，离线模式，检查更新", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.exit:
                        break;
                        default:
                }
                return true;
            }
        });
        //TabLayout的内容
        list.add("头条");
        list.add("社会");
        list.add("国内");
        list.add("国际");
        list.add("娱乐");
        list.add("体育");
        list.add("军事");
        list.add("科技");
        list.add("财经");
        //viewPager与Fragment联用
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            //viewPager的适配器
            @NonNull
            @Override
            public CharSequence getPageTitle(int position){
                return list.get(position);
                //返回list对应页签位置的内容
            }
            @Override
            public Fragment getItem(int position) {
                NewsFragment newsFragment = new NewsFragment();
                Bundle bundle = new Bundle();//该类作为list的载体，依次传入name的值，与Map用法类似
                if(list.get(position).equals("头条")){
                    bundle.putString("name","top");
                }else if (list.get(position).equals("社会")){
                    bundle.putString("name","shehui");
                }else if (list.get(position).equals("国内")){
                    bundle.putString("name","guonei");
                }else if (list.get(position).equals("国际")){
                    bundle.putString("name","guoji");
                }else if (list.get(position).equals("娱乐")){
                    bundle.putString("name","yule");
                }else if (list.get(position).equals("体育")){
                    bundle.putString("name","tiyu");
                }else if (list.get(position).equals("军事")){
                    bundle.putString("name","junshi");
                }else if (list.get(position).equals("科技")){
                    bundle.putString("name","keji");
                }else if (list.get(position).equals("财经")){
                    bundle.putString("name","caijing");
                }else if (list.get(position).equals("时尚")){
                    bundle.putString("name","shishang");
                }
                newsFragment.setArguments(bundle);
                //Activity重新创建时，会重新构建它所管理的Fragment
                //原先的Fragment的字段值将会全部丢失
                //但是通过Fragment.setArguments(Bundle bundle)方法设置的bundle会保留下来
                return newsFragment;
            }
            @NonNull
            @Override
            public Object instantiateItem (@NonNull ViewGroup container,int position){
                NewsFragment newsFragment = (NewsFragment) super.instantiateItem(container,position);
                return newsFragment;
            }

            @Override
            public int getItemPosition(@NonNull Object object){
                return FragmentStatePagerAdapter.POSITION_NONE;
            }

            @Override
            public int getCount() {
                return list.size();
                //获取list存储的个数大小
            }
        });
        //TabLayout要与ViewPager关联显示
        tabLayout.setupWithViewPager(viewPager);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //获取Toolbar菜单
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            //R.id.home修改导航按钮的点击事件为打开滑动侧栏
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);//打开测滑动栏
                break;
            case R.id.fab:
                mDrawerLayout.openDrawer(GravityCompat.START);//打开测滑动栏
                break;
            case R.id.userFeedback:
                final EditText ed = new EditText(MainActivity.this);
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);//创建对话框
                dialog.setTitle("用户反馈");
                dialog.setView(ed);
                dialog.setCancelable(false);
                //点击对话框其他位置，不会关闭
                dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "反馈成功", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setNegativeButton("取消",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog,int which){

                    }
                });
                dialog.show();
                break;
            case R.id.userExit:
                //Toast.makeText(this, "你将点击退出", Toast.LENGTH_SHORT).show();
                System.exit(0);//退出
                break;
                default:
        }
        return true;
    }
}
