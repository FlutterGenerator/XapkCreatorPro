package com.kc.xapktool;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.github.angads25.filepicker.view.FilePickerDialog;
import com.github.angads25.filepicker.model.DialogConfigs;
import com.github.angads25.filepicker.model.DialogProperties;
import com.github.angads25.filepicker.controller.DialogSelectionListener;
import net.lingala.zip4j.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class AppextractActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private String targetFolder = "";
	private String appname = "";
	private double count = 0;
	private String fontName = "";
	private String typeace = "";
	private double any = 0;
	private double n = 0;
	
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ArrayList<String> name = new ArrayList<>();
	private ArrayList<String> pName = new ArrayList<>();
	private ArrayList<String> apkPath = new ArrayList<>();
	private ArrayList<String> search = new ArrayList<>();
	private ArrayList<String> list = new ArrayList<>();
	
	private LinearLayout linear1;
	private ListView listview1;
	private ProgressBar progressbar1;
	private TextView textview;
	
	private Intent sg = new Intent();
	private ProgressDialog jks;
	private TimerTask dp;
	private AlertDialog.Builder dialog;
	private SharedPreferences data;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.appextract);
		FontUtil.applyFont(this, getWindow().getDecorView());
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		listview1 = findViewById(R.id.listview1);
		progressbar1 = findViewById(R.id.progressbar1);
		textview = findViewById(R.id.textview);
		dialog = new AlertDialog.Builder(this);
		data = getSharedPreferences("data", Activity.MODE_PRIVATE);
		
		listview1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				
				return true;
			}
		});
	}
	
	private void initializeLogic() {
		(new GetAppsListTask()).execute();
	}
	
	public void _uselessMethod() {
	}
	
	private ArrayList<HashMap<String, android.graphics.drawable.Drawable>> icons = new ArrayList<HashMap<String, android.graphics.drawable.Drawable>>();
	private class GetAppsListTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			progressbar1.setVisibility(View.VISIBLE);
			listview1.setVisibility(View.GONE);
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
						
						icons.add(item);
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
				_view = _inflater.inflate(R.layout.app, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final ImageView image1 = _view.findViewById(R.id.image1);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final TextView text1 = _view.findViewById(R.id.text1);
			final TextView text2 = _view.findViewById(R.id.text2);
			
			text1.setText(name.get((int)(_position)));
			text2.setText(pName.get((int)(_position)));
			FontUtil.applyFont(AppextractActivity.this, text1);
			FontUtil.applyFont(AppextractActivity.this, text2);
			image1.setImageDrawable(icons.get(_position).get("ic"));
			linear1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)30, 0xFF232D37));
			text1.setTextIsSelectable(true);
			text2.setTextIsSelectable(true);
			linear1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					data.edit().putString("hhh", text2.getText().toString()).commit();
					data.edit().putString("iii", text1.getText().toString()).commit();
					sg.setClass(getApplicationContext(), MainActivity.class);
					startActivity(sg);
				}
			});
			image1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					data.edit().putString("hhh", text2.getText().toString()).commit();
					data.edit().putString("iii", text1.getText().toString()).commit();
					sg.setClass(getApplicationContext(), MainActivity.class);
					startActivity(sg);
				}
			});
			linear2.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					data.edit().putString("hhh", text2.getText().toString()).commit();
					data.edit().putString("iii", text1.getText().toString()).commit();
					sg.setClass(getApplicationContext(), MainActivity.class);
					startActivity(sg);
				}
			});
			text1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					data.edit().putString("hhh", text2.getText().toString()).commit();
					data.edit().putString("iii", text1.getText().toString()).commit();
					sg.setClass(getApplicationContext(), MainActivity.class);
					startActivity(sg);
				}
			});
			text2.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					data.edit().putString("hhh", text2.getText().toString()).commit();
					data.edit().putString("iii", text1.getText().toString()).commit();
					sg.setClass(getApplicationContext(), MainActivity.class);
					startActivity(sg);
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