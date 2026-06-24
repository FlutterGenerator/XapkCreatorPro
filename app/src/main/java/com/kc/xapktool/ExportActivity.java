package com.kc.xapktool;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class ExportActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private double n = 0;
	private double prog = 0;
	
	private ArrayList<String> map = new ArrayList<>();
	private ArrayList<String> files = new ArrayList<>();
	
	private LinearLayout linear1;
	private EditText edittext1;
	private LinearLayout linear2;
	private Button button1;
	
	private Intent ent = new Intent();
	private TimerTask t;
	private ProgressDialog gg;
	private Notification nn;
	private Intent nm = new Intent();
	private AlertDialog.Builder zs;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.export);
		FontUtil.applyFont(this, getWindow().getDecorView());
		initialize(_savedInstanceState);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		edittext1 = findViewById(R.id.edittext1);
		linear2 = findViewById(R.id.linear2);
		button1 = findViewById(R.id.button1);
		zs = new AlertDialog.Builder(this, R.style.DarkDialogTheme);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
				java.io.File f15 = new java.io.File("/storage/emulated/0/KC Tool Kit VIP/XAPK/Exported/KC_Signed.zip");
				java.io.File f25 = new java.io.File("/storage/emulated/0/KC Tool Kit VIP/XAPK/Exported/".concat(edittext1.getText().toString().concat("_kc_signed.xapk")));
				boolean b5 = f15.renameTo(f25);
				
				SketchwareUtil.showMessage(getApplicationContext(), "/storage/emulated/0/KC Tool Kit VIP/XAPK/Exported/".concat(edittext1.getText().toString().concat("_kc_signed.xapk")));
				ent.setClass(getApplicationContext(), HomeActivity.class);
				startActivity(ent);
				Notification.Builder nn = new
				Notification.Builder(ExportActivity.this);
				nn.setSmallIcon(R.drawable.gmi_wrench);
				nn.setContentTitle("Backup created successfully...");
				nn.setContentText("Tap to view");
				NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				Intent nnm = new Intent(
				getApplicationContext(), HomeActivity.class);
				PendingIntent nnn = PendingIntent.getActivity(
				getApplicationContext(),
				0,
				nnm,
				PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
				nn.setContentIntent(nnn).setAutoCancel(true);
				nn.setOngoing(false);
				int onx = 30;
				notificationManager.notify(onx, nn.build());
				zs.setTitle("Backup Successfully");
				zs.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				AlertDialog backupDialog = zs.create();
				backupDialog.show();
				FontUtil.applyFont(ExportActivity.this, backupDialog.getWindow().getDecorView());
				SketchwareUtil.showMessage(getApplicationContext(), edittext1.getText().toString().concat(" backup saved Successfully"));
				finish();
			}
		});
	}
	
	private void initializeLogic() {
		button1.setVisibility(View.VISIBLE);
		_rippleRoundStroke(button1, "#FF2196F3", "#FFF5F5F5", 50, 0, "#FF2196F3");
	}
	
	@Override
	public void onBackPressed() {
		if (edittext1.getText().toString().equals("")) {
			SketchwareUtil.showMessage(getApplicationContext(), "Please save current file first");
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

	public void _rippleRoundStroke(final View _view, final String _focus, final String _pressed, final double _round, final double _stroke, final String _strokeclr) {
		android.graphics.drawable.GradientDrawable GG = new android.graphics.drawable.GradientDrawable();
		GG.setColor(android.graphics.Color.parseColor(_focus));
		GG.setCornerRadius((float)_round);
		GG.setStroke((int) _stroke, android.graphics.Color.parseColor("#" + _strokeclr.replace("#", "")));
		android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ android.graphics.Color.parseColor(_pressed)}), GG, null);
		_view.setBackground(RE);
	}
}
