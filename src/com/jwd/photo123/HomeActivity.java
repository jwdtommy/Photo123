package com.jwd.photo123;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.jwd.base.BaseActivity;
import com.jwd.fragment.HomeFragment;
import com.umeng.update.UmengUpdateAgent;

public class HomeActivity extends BaseActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {
	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	private long exitTime = 0;
	private final long APP_EXIT_TIMER = 2000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		mTitle = "最新";
		UmengUpdateAgent.setUpdateOnlyWifi(true);
		UmengUpdateAgent.update(this);
		Log.i("jwd", "onCreate()");
		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();
		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
		// new NetWorkTask(this,Constants.NET_TAG_GET_MESSAGE,1).startLoading();
		Log.i("jwd", "onCreate()aaa");
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments

		Log.i("jwd", "onNavigationDrawerItemSelected=" + position);
		updateFragment(position);
	}

	public void updateFragment(int position) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		HomeFragment frag = new HomeFragment(position);
		fragmentManager.beginTransaction().replace(R.id.container, frag)
				.commit();
		changeTitle(position);
	}

	public void changeTitle(int position) {
		switch (position) {
		case 0:
			mTitle = "最新";
			break;
		case 1:
			mTitle = "最衰";
			break;
		case 2:
			mTitle = "最该";
			break;
		default:
			break;
		}
		getActionBar().setTitle(mTitle + "");
	}

	public void onSectionAttached(int number) {
		switch (number) {
		// case 1:
		// mTitle = getString(R.string.menu_item_remen);
		// break;
		case 1:
			mTitle = getString(R.string.menu_item_zuixin);
			break;
		case 2:
			mTitle = getString(R.string.menu_item_zuishai);
			break;
		case 3:
			mTitle = getString(R.string.menu_item_zuigai);
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			// getMenuInflater().inflate(R.menu.home, menu);
			getMenuInflater().inflate(R.menu.home, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if (mNavigationDrawerFragment.isDrawerOpen()) {
				mNavigationDrawerFragment.closeDrawer();
				return true;
			}
			if ((System.currentTimeMillis() - exitTime) > APP_EXIT_TIMER) {
				Toast.makeText(getApplicationContext(), "再按一次退出草蛋",
						Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		// if (id == R.id.action_settings) {
		// return true;
		// }
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void bindData(int code, int tag, Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onError(int code, String massage) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		this.finish();
	}

}
