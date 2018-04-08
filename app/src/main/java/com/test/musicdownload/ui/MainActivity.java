package com.test.musicdownload.ui;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.test.musicdownload.FileUtil;
import com.test.musicdownload.ListviewAdapter;
import com.test.musicdownload.R;
import com.test.musicdownload.javabean.BaseMp3Info;
import com.test.musicdownload.presenter.MainPresenter;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IBaseView {
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.edit_query)
    EditText editText;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.listview)
    ListView listView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private boolean apiChange = false;
    private ListviewAdapter adapter;
    private MainPresenter presenter = new MainPresenter(this);
    private List<BaseMp3Info> list;
    String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setNavView(navigationView);
        permission();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                if (!"".equals(name)) {
                    if (!apiChange) {
                        presenter.requestInfo(name);
                        //Toast.makeText(MainActivity.this,"当前API为：网易云音乐",Toast.LENGTH_SHORT).show();
                    } else {
                        presenter.requestInfo2(name);
                        //Toast.makeText(MainActivity.this,"当前API为：QQ音乐",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "歌名为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!apiChange) {
                    id = list.get(position).getId();
                    presenter.getUrl(id);
                    filename = list.get(position).getArtist() + "-" + list.get(position).getTitle() + ".mp3";
                } else {
                    String mid = list.get(position).getMid();
                    presenter.getVkey(mid);
                    filename = list.get(position).getArtist() + "-" + list.get(position).getTitle() + ".m4a";
                }
            }
        });
    }

    public void permission() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission_group.STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE
            }, 1);
        }
    }

    private void setNavView(NavigationView navView) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_163:
                        apiChange = false;
                        drawerLayout.closeDrawers();
                        Toast.makeText(MainActivity.this, "当前API为：网易云音乐", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navigation_qq:
                        apiChange = true;
                        drawerLayout.closeDrawers();
                        Toast.makeText(MainActivity.this, "当前API为：QQ音乐", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"关于");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case 1:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("闪退：确保手机有网络连接\n下载失败：不要连接VPN");
                builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                break;
        }
        return true;
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showDialog(final String url) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(filename + "");
        if (!"null".equals(url)) {
            builder.setMessage("下载地址为:" + url);
            builder.setPositiveButton("下载", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    FileUtil fileUtil = new FileUtil(MainActivity.this);
                    fileUtil.downMusic(url, filename);
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
        } else {
            builder.setMessage("暂无下载地址，请更换API尝试");
            builder.setNegativeButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
        }
        builder.show();

    }

    @Override
    public void listviewUpdata(List<BaseMp3Info> list) {
        this.list = list;
        adapter = new ListviewAdapter(list, this);
        listView.setAdapter(adapter);
        //hideProgress();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMsg(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
