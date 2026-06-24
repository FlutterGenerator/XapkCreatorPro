package com.kc.xapktool;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.Intent;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.github.angads25.filepicker.view.FilePickerDialog;
import com.github.angads25.filepicker.model.DialogConfigs;
import com.github.angads25.filepicker.model.DialogProperties;
import com.github.angads25.filepicker.controller.DialogSelectionListener;
import com.google.android.material.appbar.AppBarLayout;
import net.lingala.zip4j.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class AppsActivity extends AppCompatActivity {
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ArrayList<String> pName = new ArrayList<>();
	private ArrayList<String> name = new ArrayList<>();
	private ArrayList<String> apkPath = new ArrayList<>();
	
	private LinearLayout linear1;
	private ListView listview1;
	private ProgressBar progressbar1;
	private TextView textview1;
	
	private Intent khud = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.apps);
		FontUtil.applyFont(this, getWindow().getDecorView());
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_app_bar = findViewById(R.id._app_bar);
		_coordinator = findViewById(R.id._coordinator);
		_toolbar = findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		linear1 = findViewById(R.id.linear1);
		listview1 = findViewById(R.id.listview1);
		progressbar1 = findViewById(R.id.progressbar1);
		textview1 = findViewById(R.id.textview1);
	}
	
	private void initializeLogic() {
		(new GetAppsListTasks()).execute();
	}
	
	public void _uselessMethod() {
	}
	
	private ArrayList<HashMap<String, android.graphics.drawable.Drawable>> icon = new ArrayList<HashMap<String, android.graphics.drawable.Drawable>>();
	private class GetAppsListTasks extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			progressbar1.setVisibility(View.VISIBLE);
			listview1.setVisibility(View.GONE);
			textview1.setVisibility(View.GONE);
		}
		
		@Override
		protected Void doInBackground(Void... path) {
			android.content.pm.PackageManager pm = getPackageManager();
			
			List<android.content.pm.PackageInfo> packages = pm.getInstalledPackages(0);
			
			for(android.content.pm.PackageInfo info : packages){
				
				if((info.applicationInfo.flags & android.content.pm.ApplicationInfo.FLAG_SYSTEM) == 0){
					
					name.add(info.applicationInfo.loadLabel(pm).toString());
					
					pName.add(info.packageName);
					
					apkPath.add(info.applicationInfo.sourceDir);
					{
						HashMap<String, android.graphics.drawable.Drawable> item = new HashMap<>();
						
						item.put("ic", info.applicationInfo.loadIcon(pm));
						
						icon.add(item);
					}
					{
						HashMap<String, Object> _item = new HashMap<>();
						_item.put("x", "x");
						listmap.add(_item);
					}
					
				}}
			return null;
		}
		@Override
		protected void onProgressUpdate(Void... values) {
		}
		@Override
		protected void onPostExecute(Void param){
			progressbar1.setVisibility(View.GONE);
			textview1.setVisibility(View.GONE);
			listview1.setVisibility(View.VISIBLE);
			listview1.setAdapter(new Listview1Adapter(listmap));
		}}{
	}
	
	public class Listview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.apk_info, null);
			}
			
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			
			textview1.setText(name.get((int)(_position)));
			FontUtil.applyFont(AppsActivity.this, textview1);
			imageview1.setImageDrawable(icon.get(_position).get("ic"));
			linear1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					try{
							BitmapDrawable imageview1BD = (BitmapDrawable) imageview1.getDrawable();
							Bitmap imageview1B = imageview1BD.getBitmap();
							FileOutputStream imageview1FOS = null;
							File imageview1F = Environment.getExternalStorageDirectory();
							File imageview1F2 = new File("/storage/emulated/0/KC Tool Kit VIP/XAPK/Work/");
							imageview1F2.mkdirs();
							String imageview1FN = "icon.png";
							File imageview1F3 = new File(imageview1F2, imageview1FN);
							imageview1FOS = new FileOutputStream(imageview1F3); 
							imageview1B.compress(Bitmap.CompressFormat.JPEG, 99, imageview1FOS);
							imageview1FOS.flush();
							imageview1FOS.close(); 
							Intent imageview1I = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
							imageview1I.setData(Uri.fromFile(imageview1F)); sendBroadcast(imageview1I);
					}catch(Exception e){
					}
					khud.setClass(getApplicationContext(), MainActivity.class);
					startActivity(khud);
					SketchwareUtil.showMessage(getApplicationContext(), "icon picked");
					finish();
				}
			});
			
			return _view;
		}
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}