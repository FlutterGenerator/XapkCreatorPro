package com.kc.xapktool;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.content.*;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.github.angads25.filepicker.view.FilePickerDialog;
import com.github.angads25.filepicker.model.DialogConfigs;
import com.github.angads25.filepicker.model.DialogProperties;
import com.github.angads25.filepicker.controller.DialogSelectionListener;
import com.google.android.material.appbar.AppBarLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.lingala.zip4j.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class SuccessActivity extends AppCompatActivity {
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private String my = "";
	private HashMap<String, Object> an = new HashMap<>();
	private String hell = "";
	
	private LinearLayout linear1;
	private TextView textview1;
	private Button button1;
	
	private Intent a = new Intent();
	private ProgressDialog pro;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.success);
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
		textview1 = findViewById(R.id.textview1);
		button1 = findViewById(R.id.button1);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				pro = new ProgressDialog(SuccessActivity.this);
				pro.setTitle("Installing Xapk...");
				pro.setMessage("Insatller gets files from the apk...");
				pro.setMax((int)100);
				pro.setProgress((int)0);
				pro.setCancelable(false);
				pro.setCanceledOnTouchOutside(false);
				pro.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				pro.setIndeterminate(true);
				my = getIntent().getStringExtra("jks");
				
				if (FileUtil.isExistFile("/storage/emulated/0/KC Tool Kit VIP/XAPK/Work")) {
					FileUtil.deleteFile("/storage/emulated/0/KC Tool Kit VIP/XAPK/Work");
					FileUtil.makeDir("/storage/emulated/0/KC Tool Kit VIP/XAPK/Work");
					hell = FileUtil.readFile("/storage/emulated/0/KC Tool Kit VIP/XAPK/Work/manifest.json");
					an = new Gson().fromJson(hell, new TypeToken<HashMap<String, Object>>(){}.getType());
					pro.show();
				
				new _BackgroundTaskClass(SuccessActivity.this) {
								        @Override
								        public void doInBackground() {
												try {
																net.lingala.zip4j.ZipFile zipFile = new net.lingala.zip4j.ZipFile(my);
																if (zipFile.isEncrypted()) {
																				zipFile.setPassword("".toCharArray());
																}
																zipFile.extractAll("/storage/emulated/0/KC Tool Kit VIP/XAPK/Work/"); 
												} catch (Exception e) {
												}
												            //put you background code
												            //same like doingBackground
												            //Background Thread
												
												
												
												        }
								
								        @Override
								        public void onPostExecute() {
												 pro.dismiss();
												 StrictMode.setVmPolicy(new
						StrictMode. VmPolicy.Builder().build());
						Intent intent = new
						Intent(Intent.ACTION_INSTALL_PACKAGE);
						intent.setData(Uri.fromFile(new
						java.io.File( "/storage/emulated/0/KC Tool Kit VIP/XAPK/Work/base.apk")));
						startActivity(intent);
						
						
						        final ProgressDialog progressDialog = new ProgressDialog(SuccessActivity.this);
						        Thread copyingThread;
						        
						        
						        final Handler mainHandler = new Handler(Looper.getMainLooper());
						        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
						        progressDialog.setTitle("Installing Xapk...");
						        progressDialog.setMessage("Installer gets files from the apk...");
						        progressDialog.setIndeterminate(false);
						        progressDialog.setCancelable(false);
						        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
						        progressDialog.show();
						
						        copyingThread = new Thread(new Runnable() {
							            @Override
							            public void run() {        
								
								
								
								                java.io.File sourceFile = new java.io.File("/storage/emulated/0/KC Tool Kit VIP/XAPK/Work/".concat(Uri.parse(an.get("install_path").toString()).getLastPathSegment()));
								                java.io.File destFile = new java.io.File(an.get("install_location").toString().concat("/").concat(an.get("install_path").toString()));
								                java.io.FileInputStream fileInputStream;
								                java.io.FileOutputStream fileOutputStream;
								                long length = sourceFile.length();
								                int bytesRead;
								                int totalBytesRead = 0;
								                byte[] buffer = new byte[4 * 1024]; // 4KB buffer
								                try {
										                    fileInputStream = new java.io.FileInputStream(sourceFile);
										                    fileOutputStream = new java.io.FileOutputStream(destFile);
										
										                    while (!Thread.currentThread().isInterrupted()
										                            && (bytesRead = fileInputStream.read(buffer)) != -1) {
												                   
												                        fileOutputStream.write(buffer, 0, bytesRead);
												
												                 
												                        totalBytesRead += bytesRead;
												                        progressDialog.setMax((int)sourceFile.length());
												                        final int fin = (int)(totalBytesRead);
												                        
												
												                      
												                        mainHandler.post(new Runnable() {
														                            @Override
														                            public void run() {
																                                progressDialog.setProgress(fin);
																                                
																                            }
														
														                        });
												                       if (fin == progressDialog.getMax()) {
														                       progressDialog.dismiss();
														                          }
												                    }
										
										                } catch (Exception e) {
										                    System.out.println("foo");
										                }
								            }
							
							        });
						     copyingThread.start();
						     
												            //hear is result part same
												            //same like post execute
												            //UI Thread(update your UI widget)
												        }
								    }.execute();
			}
			else {
				hell = FileUtil.readFile("/storage/emulated/0/KC Tool Kit VIP/XAPK/Work/manifest.json");
				an = new Gson().fromJson(hell, new TypeToken<HashMap<String, Object>>(){}.getType());
				pro.show();
			
			new _BackgroundTaskClass(SuccessActivity.this) {
							        @Override
							        public void doInBackground() {
											try {
															net.lingala.zip4j.ZipFile zipFile = new net.lingala.zip4j.ZipFile(my);
															if (zipFile.isEncrypted()) {
																			zipFile.setPassword("".toCharArray());
															}
															zipFile.extractAll("/storage/emulated/0/KC Tool Kit VIP/XAPK/Work/"); 
											} catch (Exception e) {
											}
											            //put you background code
											            //same like doingBackground
											            //Background Thread
											
											
											
											        }
							
							        @Override
							        public void onPostExecute() {
											 pro.dismiss();
											 StrictMode.setVmPolicy(new
					StrictMode. VmPolicy.Builder().build());
					Intent intent = new
					Intent(Intent.ACTION_INSTALL_PACKAGE);
					intent.setData(Uri.fromFile(new
					java.io.File( "/storage/emulated/0/KC Tool Kit VIP/XAPK/Work/base.apk")));
					startActivity(intent);
					
					
					        final ProgressDialog progressDialog = new ProgressDialog(SuccessActivity.this);
					        Thread copyingThread;
					        
					        
					        final Handler mainHandler = new Handler(Looper.getMainLooper());
					        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
					        progressDialog.setTitle("Installing Xapk...");
					        progressDialog.setMessage("Installer gets files from the apk...");
					        progressDialog.setIndeterminate(false);
					        progressDialog.setCancelable(false);
					        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
					        progressDialog.show();
					
					        copyingThread = new Thread(new Runnable() {
						            @Override
						            public void run() {        
							
							
							
							                java.io.File sourceFile = new java.io.File("/storage/emulated/0/KC Tool Kit VIP/XAPK/Work/".concat(Uri.parse(an.get("install_path").toString()).getLastPathSegment()));
							                java.io.File destFile = new java.io.File(an.get("install_location").toString().concat("/").concat(an.get("install_path").toString()));
							                java.io.FileInputStream fileInputStream;
							                java.io.FileOutputStream fileOutputStream;
							                long length = sourceFile.length();
							                int bytesRead;
							                int totalBytesRead = 0;
							                byte[] buffer = new byte[4 * 1024]; // 4KB buffer
							                try {
									                    fileInputStream = new java.io.FileInputStream(sourceFile);
									                    fileOutputStream = new java.io.FileOutputStream(destFile);
									
									                    while (!Thread.currentThread().isInterrupted()
									                            && (bytesRead = fileInputStream.read(buffer)) != -1) {
											                   
											                        fileOutputStream.write(buffer, 0, bytesRead);
											
											                 
											                        totalBytesRead += bytesRead;
											                        progressDialog.setMax((int)sourceFile.length());
											                        final int fin = (int)(totalBytesRead);
											                        
											
											                      
											                        mainHandler.post(new Runnable() {
													                            @Override
													                            public void run() {
															                                progressDialog.setProgress(fin);
															                                
															                            }
													
													                        });
											                       if (fin == progressDialog.getMax()) {
													                       progressDialog.dismiss();
													                          }
											                    }
									
									                } catch (Exception e) {
									                    System.out.println("foo");
									                }
							            }
						
						        });
					     copyingThread.start();
					     
											            //hear is result part same
											            //same like post execute
											            //UI Thread(update your UI widget)
											        }
							    }.execute();
		}
	}
});
}

private void initializeLogic() {
	_rippleRoundStroke(button1, "#FF2196F3", "#FFF5F5F5", 50, 0, "#FF2196F3");
		textview1.setText("xapk created successfully...(".concat(getIntent().getStringExtra("jks").concat(")\n\nIf you want to test the file you can click the install button below and go ahead or else you can ignore...")));
}
public static abstract class _BackgroundTaskClass {
	
	    private Activity activity;
	    public _BackgroundTaskClass(Context activity) {
		        this.activity = (Activity) activity;
		    }
	
	    private void startBackground() {
		        new Thread(new Runnable() {
			            public void run() {
				
				                doInBackground();
				                activity.runOnUiThread(new Runnable() {
					                    public void run() {
						
						                        onPostExecute();
						                    }
					                });
				            }
			        }).start();
		    }
	    public void execute(){
		        startBackground();
		    }
	
	    public abstract void doInBackground();
	    public abstract void onPostExecute();
	
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
