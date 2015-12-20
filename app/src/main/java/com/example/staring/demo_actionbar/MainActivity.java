package com.example.staring.demo_actionbar;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button_hide;
    private Button button_show;
    private Button to_another_activity;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        button_hide = (Button) findViewById(R.id.hide_actionbar);
        button_hide.setOnClickListener(this);
        button_show = (Button) findViewById(R.id.show_actionbar);
        button_show.setOnClickListener(this);
        to_another_activity = (Button) findViewById(R.id.to_another_activity);
        to_another_activity.setOnClickListener(this);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setLogo(getResources().getDrawable(R.mipmap.burning));  //为什么不会显示logo
//        actionBar.setTitle("这里改变title");
//        actionBar.setSubtitle("居然还有子title");  //好像并没有什么用。。。
//        actionBar.setStackedBackgroundDrawable(getResources().getDrawable(R.mipmap.burning));   //居然是Tab的背景
//        actionBar.setSplitBackgroundDrawable(getResources().getDrawable(R.mipmap.guomin));   //好像并没有什么用啊
        // setup action bar for tabs
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//        actionBar.setDisplayShowTitleEnabled(false);
        ActionBar.Tab tab = actionBar.newTab()
                .setText(R.string.artist)
                .setTabListener(new TabListener<ArtistFragment>(
                        this, "artist", ArtistFragment.class));
        actionBar.addTab(tab);

        tab = actionBar.newTab()
                .setText(R.string.album)
                .setTabListener(new TabListener<AlbumFragment>(
                        this, "album", AlbumFragment.class));
        actionBar.addTab(tab);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.demo_menu_one, menu);  //将menu加载到这里来

        //得到这个SearchView
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        // Configure the search info and add any event listeners

        // Set up ShareActionProvider's default share intent
        MenuItem shareItem = menu.findItem(R.id.action_share);
        ShareActionProvider mShareActionProvider = (ShareActionProvider)
                MenuItemCompat.getActionProvider(shareItem);
        mShareActionProvider.setShareIntent(getDefaultIntent());


        return super.onCreateOptionsMenu(menu);
    }

    private Intent getDefaultIntent() {
        //为什么这里没有反应呢 send的action 然后里面装的是image
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        return intent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hide_actionbar:
                actionBar.hide();
                break;
            case R.id.show_actionbar:
                actionBar.show();
                break;
            case R.id.to_another_activity:
                startActivity(new Intent(this, Activity_Another_One.class));
                break;
        }
    }
}
