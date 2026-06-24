package com.kc.xapktool;

import com.kc.xapktool.HomeActivity;
import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
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
import com.google.android.material.textfield.*;
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

public class MainActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private double prog1 = 0;
	private double prog2 = 0;
	private double prog3 = 0;
	private double zipper = 0;
	private double B = 0;
	private double KB = 0;
	private double MB = 0;
	private double GB = 0;
	private double TB = 0;
	private String returnedSize = "";
	private double obbb = 0;
	private double apkk = 0;
	private double radius = 0;
	private String white = "";
	private String fontName = "";
	private String typeace = "";
	private String path = "";
	private double set = 0;
	private double minSDK = 0;
	private double tarSDK = 0;
	private String vName = "";
	private double vCode = 0;
	private String Package_name = "";
	private String vcode = "";
	private String err = "";
	private String apkFile = "";
	
	private ArrayList<HashMap<String, Object>> uselessMap = new ArrayList<>();
	private ArrayList<String> files = new ArrayList<>();
	private ArrayList<String> setting = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> sett = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> app = new ArrayList<>();
	private ArrayList<String> name = new ArrayList<>();
	private ArrayList<String> pName = new ArrayList<>();
	private ArrayList<String> apkPath = new ArrayList<>();
	
	private ImageView imageview1;
	private ScrollView Home;
	private LinearLayout linear2;
	private TextView textview2;
	private Button button7;
	private TextView textview14;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private LinearLayout linear9;
	private Button button4;
	private LinearLayout linear10;
	private Button button5;
	private LinearLayout linear11;
	private Button button6;
	private LinearLayout linear13;
	private Button button1;
	private TextView textview3;
	private TextInputLayout textinputlayout1;
	private EditText edittext3;
	private TextView textview4;
	private TextInputLayout textinputlayout7;
	private EditText edittext4;
	private TextView textview5;
	private TextInputLayout textinputlayout2;
	private EditText edittext5;
	private TextView textview6;
	private TextInputLayout textinputlayout3;
	private EditText edittext6;
	private TextView textview7;
	private TextInputLayout textinputlayout4;
	private EditText edittext7;
	private TextView textview8;
	private TextInputLayout textinputlayout5;
	private EditText edittext8;
	
	private Intent a = new Intent();
	private AlertDialog.Builder d;
	private FilePickerDialog apkPick;
	private FilePickerDialog obbPick;
	private FilePickerDialog XapkIcon;
	private TimerTask timer;
	private ProgressDialog icon;
	private ProgressDialog zip;
	private ProgressDialog apk;
	private ProgressDialog obb;
	private SharedPreferences data;
	private AlertDialog.Builder work;
	private AlertDialog.Builder export;
	private AlertDialog appp;
	private AlertDialog.Builder info;
	private Notification nn;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
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
		imageview1 = findViewById(R.id.imageview1);
		Home = findViewById(R.id.Home);
		linear2 = findViewById(R.id.linear2);
		textview2 = findViewById(R.id.textview2);
		button7 = findViewById(R.id.button7);
		textview14 = findViewById(R.id.textview14);
		linear3 = findViewById(R.id.linear3);
		linear4 = findViewById(R.id.linear4);
		linear5 = findViewById(R.id.linear5);
		linear6 = findViewById(R.id.linear6);
		linear7 = findViewById(R.id.linear7);
		linear8 = findViewById(R.id.linear8);
		linear9 = findViewById(R.id.linear9);
		button4 = findViewById(R.id.button4);
		linear10 = findViewById(R.id.linear10);
		button5 = findViewById(R.id.button5);
		linear11 = findViewById(R.id.linear11);
		button6 = findViewById(R.id.button6);
		linear13 = findViewById(R.id.linear13);
		button1 = findViewById(R.id.button1);
		textview3 = findViewById(R.id.textview3);
		textinputlayout1 = findViewById(R.id.textinputlayout1);
		edittext3 = findViewById(R.id.edittext3);
		textview4 = findViewById(R.id.textview4);
		textinputlayout7 = findViewById(R.id.textinputlayout7);
		edittext4 = findViewById(R.id.edittext4);
		textview5 = findViewById(R.id.textview5);
		textinputlayout2 = findViewById(R.id.textinputlayout2);
		edittext5 = findViewById(R.id.edittext5);
		textview6 = findViewById(R.id.textview6);
		textinputlayout3 = findViewById(R.id.textinputlayout3);
		edittext6 = findViewById(R.id.edittext6);
		textview7 = findViewById(R.id.textview7);
		textinputlayout4 = findViewById(R.id.textinputlayout4);
		edittext7 = findViewById(R.id.edittext7);
		textview8 = findViewById(R.id.textview8);
		textinputlayout5 = findViewById(R.id.textinputlayout5);
		edittext8 = findViewById(R.id.edittext8);
		d = new AlertDialog.Builder(this, R.style.DarkDialogTheme);
		data = getSharedPreferences("data", Activity.MODE_PRIVATE);
		work = new AlertDialog.Builder(this, R.style.DarkDialogTheme);
		export = new AlertDialog.Builder(this, R.style.DarkDialogTheme);
		info = new AlertDialog.Builder(this, R.style.DarkDialogTheme);
		
		button7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				a.setClass(getApplicationContext(), AppextractActivity.class);
				startActivity(a);
			}
		});
		
		button4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (edittext4.getText().toString().equals("")) {
					SketchwareUtil.showMessage(getApplicationContext(), "package name required to choose apk");
				}
				else {
					apkPick.setDialogSelectionListener(new DialogSelectionListener() {
						@Override public void onSelectedFilePaths(String[] files) {
							a.putExtra("ap", "");
							apk = new ProgressDialog(MainActivity.this);
							apk.setMessage("Copied Soon");
							apk.setMax((int)100);
							apk.setProgress((int)apkk);
							apk.setCancelable(false);
							apk.setCanceledOnTouchOutside(false);
							apk.setProgressStyle(ProgressDialog.STYLE_SPINNER);
							apk.show();
							apkFile = Arrays.asList(files).get((int) 0).toString();
							
							
							
							        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
							        Thread copyingThread;
							        
							        
							        final Handler mainHandler = new Handler(Looper.getMainLooper());
							        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
							        progressDialog.setTitle("Copying Files...");
							        progressDialog.setMessage("Copying apk file...");
							        progressDialog.setIndeterminate(false);
							        progressDialog.setCancelable(false);
							        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
							        progressDialog.show();
							        fixDialogTextColors(progressDialog);
							
							        copyingThread = new Thread(new Runnable() {
								            @Override
								            public void run() {        
									
									
									
									                java.io.File sourceFile = new java.io.File(apkFile);
									                java.io.File destFile = new java.io.File("/storage/emulated/0/KC Tool Kit VIP/XAPK/Work/".concat(edittext4.getText().toString().concat(".apk")));
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
							     
							apk.dismiss();
						} 
					});
					apkPick.show();
					FontUtil.applyFont(MainActivity.this, apkPick.getWindow().getDecorView());
				}
			}
		});
		
		button5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (edittext4.getText().toString().equals("") || edittext8.getText().toString().equals("")) {
					SketchwareUtil.showMessage(getApplicationContext(), "This Fields Are Required To Copy Apk File !");
					textinputlayout7.setError("This Elements Required");
					textinputlayout5.setError("This Elements Required");
				}
				else {
					obbPick.setDialogSelectionListener(new DialogSelectionListener() {
						@Override public void onSelectedFilePaths(String[] files) {
							obbb = 0;
							obb = new ProgressDialog(MainActivity.this);
							obb.setProgressStyle(ProgressDialog.STYLE_SPINNER);
							obb.setMessage("Copying obb files...");
							obb.setMax((int)500);
							obb.setProgress((int)obbb);
							obb.setCancelable(false);
							obb.setCanceledOnTouchOutside(false);
							obb.show();
							apkFile = Arrays.asList(files).get((int) 0).toString();
							
							
							
							        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
							        Thread copyingThread;
							        
							        
							        final Handler mainHandler = new Handler(Looper.getMainLooper());
							        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
							        progressDialog.setTitle("Copying Files...");
							        progressDialog.setMessage("Copying apk file...");
							        progressDialog.setIndeterminate(false);
							        progressDialog.setCancelable(false);
							        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
							        progressDialog.show();
							        fixDialogTextColors(progressDialog);
							
							        copyingThread = new Thread(new Runnable() {
								            @Override
								            public void run() {        
									
									
									
									                java.io.File sourceFile = new java.io.File(apkFile);
									                java.io.File destFile = new java.io.File("/storage/emulated/0/KC Tool Kit VIP/XAPK/Work/main.".concat(edittext8.getText().toString().concat(".".concat(edittext4.getText().toString())).concat(".obb")));
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
							     
							obb.dismiss();
						} 
					});
					obbPick.show();
					FontUtil.applyFont(MainActivity.this, obbPick.getWindow().getDecorView());
				}
			}
		});
		
		button6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				d.setTitle("Select icon from");
				d.setPositiveButton("SD Card", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						XapkIcon.setDialogSelectionListener(new DialogSelectionListener() {
							@Override public void onSelectedFilePaths(String[] files) {
								apkFile = Arrays.asList(files).get((int) 0).toString();
								
								
								
								        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
								        Thread copyingThread;
								        
								        
								        final Handler mainHandler = new Handler(Looper.getMainLooper());
								        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
								        progressDialog.setTitle("Copying Files...");
								        progressDialog.setMessage("Copying apk file...");
								        progressDialog.setIndeterminate(false);
								        progressDialog.setCancelable(false);
								        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
								        progressDialog.show();
								        fixDialogTextColors(progressDialog);
								
								        copyingThread = new Thread(new Runnable() {
									            @Override
									            public void run() {        
										
										
										
										                java.io.File sourceFile = new java.io.File(apkFile);
										                java.io.File destFile = new java.io.File("/storage/emulated/0/KC Tool Kit VIP/XAPK/Work/".concat(edittext4.getText().toString().concat(".apk")));
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
								     
								SketchwareUtil.showMessage(getApplicationContext(), "Icon Selected");
							} 
						});
						XapkIcon.show();
						FontUtil.applyFont(MainActivity.this, XapkIcon.getWindow().getDecorView());
					}
				});
				d.setNegativeButton("Installed Applications", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						a.setClass(getApplicationContext(), AppsActivity.class);
						startActivity(a);
					}
				});
				AlertDialog selectIconDialog = d.create();
				selectIconDialog.show();
				FontUtil.applyFont(MainActivity.this, selectIconDialog.getWindow().getDecorView());
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (edittext3.getText().toString().equals("") || (edittext4.getText().toString().equals("") || (edittext5.getText().toString().equals("") || (edittext6.getText().toString().equals("") || (edittext7.getText().toString().equals("") || edittext8.getText().toString().equals("")))))) {
					SketchwareUtil.showMessage(getApplicationContext(), "All Fields Are Required");
				}
				else {
					FileUtil.listDir("/storage/emulated/0/KC Tool Kit VIP/XAPK/Work/", files);
					path = "/storage/emulated/0/KC Tool Kit VIP/XAPK/Work".concat("/manifest.json");
					
					{
						try{
							int count;
							java.io.InputStream input= MainActivity.this.getAssets().open("manifest.json");
							java.io.OutputStream output = new  java.io.FileOutputStream("/storage/emulated/0/KC Tool Kit VIP/XAPK/Work/" + "manifest.json");
							byte data[] = new byte[1024];
							while ((count = input.read(data))>0) {
								output.write(data, 0, count);
							}
							output.flush();
							output.close();
							input.close();
							
							}catch(Exception e){
									
							}
					}
					FileUtil.writeFile(path, FileUtil.readFile(path).replace("Shadow Fight 2", edittext3.getText().toString()));
					FileUtil.writeFile(path, FileUtil.readFile(path).replace("com.nekki.shadowfight", edittext4.getText().toString()));
					FileUtil.writeFile(path, FileUtil.readFile(path).replace("16", edittext5.getText().toString()));
					FileUtil.writeFile(path, FileUtil.readFile(path).replace("28", edittext6.getText().toString()));
					FileUtil.writeFile(path, FileUtil.readFile(path).replace("2.0.6", edittext7.getText().toString()));
					FileUtil.writeFile(path, FileUtil.readFile(path).replace("2000017", edittext8.getText().toString()));
					FileUtil.writeFile(path, FileUtil.readFile(path).replace("755935077", edittext8.getText().toString()));
					FileUtil.listDir("/storage/emulated/0/KC Tool Kit VIP/XAPK/Work/", files);
					zipper = 0;
					zip = new ProgressDialog(MainActivity.this);
					zip.setProgressStyle(ProgressDialog.STYLE_SPINNER);
					zip.setMessage("Scanning Resources...");
					zip.setMax((int)10);
					zip.setProgress((int)0);
					zip.setCancelable(false);
					zip.setCanceledOnTouchOutside(false);
					zip.show();
					err = "Resources not found!";
					if (FileUtil.isExistFile("/storage/emulated/0/KC Tool Kit VIP/XAPK/Work/".concat(edittext4.getText().toString().concat(".apk")))) {
						if (FileUtil.isExistFile("/storage/emulated/0/KC Tool Kit VIP/XAPK/Work/".concat("icon".concat(".png")))) {
							if (FileUtil.isExistFile("/storage/emulated/0/KC Tool Kit VIP/XAPK/Work/".concat("main.".concat(edittext8.getText().toString().concat(".".concat(edittext4.getText().toString()))).concat(".obb")))) {
								if (FileUtil.isExistFile("/storage/emulated/0/KC Tool Kit VIP/XAPK/Work/".concat("manifest".concat(".json")))) {
									if (FileUtil.isExistFile("/storage/emulated/0/KC Tool Kit VIP/XAPK/Exported/".concat(edittext3.getText().toString().concat(".xapk")))) {
										FileUtil.deleteFile("/storage/emulated/0/KC Tool Kit VIP/XAPK/Exported/".concat(edittext3.getText().toString().concat(".xapk")));
									}
									timer = new TimerTask() {
										@Override
										public void run() {
											runOnUiThread(new Runnable() {
												@Override
												public void run() {
													zip.setMessage("Linking Resources...");
													FileUtil.listDir("/storage/emulated/0/KC Tool Kit VIP/XAPK/Work/", files);
													/*thanks to Star2Droid channel & some zip4j library & some help from kind people on stackoverflow to help me out */
													
													
													new _BackgroundTaskClass(MainActivity.this) {
																        @Override
																        public void doInBackground() {
																			files = files;
																			for (String vb: files)  {
																							try {
																											try {
																															java.lang.Thread.sleep(50);
																											}  catch (java.lang.InterruptedException exc){} 
																												net.lingala.zip4j.model.ZipParameters zipParameters = new net.lingala.zip4j.model.ZipParameters();
																											zipParameters.setCompressionLevel(net.lingala.zip4j.model.enums.CompressionLevel.ULTRA); 
																											zipParameters.setCompressionMethod(net.lingala.zip4j.model.enums.CompressionMethod.STORE);
																											net.lingala.zip4j.ZipFile zipFile = new net.lingala.zip4j.ZipFile("/storage/emulated/0/KC Tool Kit VIP/XAPK/Exported/".concat(edittext3.getText().toString().concat(".xapk")));
																											if (FileUtil.isDirectory(vb)) {
																													
																		zipFile.addFolder(new java.io.File(vb), zipParameters);
																									}
																									else {
																													
																		zipFile.addFile(new java.io.File(vb), zipParameters);
																									}
																							} catch (Exception e) {
																							}
																			}
																			            //put you background code
																			            //same like doingBackground
																			            //Background Thread
																			
																			
																			
																			        }
																
																        @Override
																        public void onPostExecute() {
																			  
																			            //hear is result part same
																			            //same like post execute
																			            //UI Thread(update your UI widget)
																			        }
																    }.execute();
													zip.setMessage("Building xapk...");
													timer = new TimerTask() {
														@Override
														public void run() {
															runOnUiThread(new Runnable() {
																@Override
																public void run() {
																	zip.setMessage("Securing xapk...");
																	zip.dismiss();
																	d.setTitle("😎CONGRATULATIONS😎");
																	d.setMessage("Your xapk exported successfully!");
																	d.setPositiveButton("ok", new DialogInterface.OnClickListener() {
																		@Override
																		public void onClick(DialogInterface _dialog, int _which) {
																			a.setClass(getApplicationContext(), HomeActivity.class);
																			startActivity(a);
																			finish();
																		}
																	});
																	d.create().show();
																	d.setCancelable(false);
																}
															});
														}
													};
													_timer.schedule(timer, (int)(90000));
												}
											});
										}
									};
									_timer.schedule(timer, (int)(5000));
								}
								else {
									zip.dismiss();
									SketchwareUtil.showMessage(getApplicationContext(), err);
									SketchwareUtil.showMessage(getApplicationContext(), "Data not found");
								}
							}
							else {
								zip.dismiss();
								SketchwareUtil.showMessage(getApplicationContext(), err);
								SketchwareUtil.showMessage(getApplicationContext(), "Please select obb");
							}
						}
						else {
							zip.dismiss();
							SketchwareUtil.showMessage(getApplicationContext(), err);
							SketchwareUtil.showMessage(getApplicationContext(), "Please select icon");
						}
					}
					else {
						zip.dismiss();
						SketchwareUtil.showMessage(getApplicationContext(), err);
						SketchwareUtil.showMessage(getApplicationContext(), "Please select apk");
					}
				}
			}
		});
	}
	
	private void initializeLogic() {
		radius = 50;
		white = "#2196F3";
		_rippleRoundStroke(button1, "#FF2196F3", "#FFF5F5F5", radius, 0, "#FF2196F3");
		_rippleRoundStroke(button4, "#FF2196F3", "#FFF5F5F5", radius, 0, "#FF2196F3");
		_rippleRoundStroke(button5, "#FF2196F3", "#FFF5F5F5", radius, 0, "#FF2196F3");
		_rippleRoundStroke(button6, "#FF2196F3", "#FFF5F5F5", radius, 0, "#FF2196F3");
		_rippleRoundStroke(button7, "#FF2196F3", "#FFF5F5F5", radius, 0, "#FF2196F3");
		textinputlayout1.setBoxStrokeColor(0xFFFFFFFF);
		textinputlayout7.setBoxStrokeColor(0xFFFFFFFF);
		textinputlayout2.setBoxStrokeColor(0xFFFFFFFF);
		textinputlayout3.setBoxStrokeColor(0xFFFFFFFF);
		textinputlayout4.setBoxStrokeColor(0xFFFFFFFF);
		textinputlayout5.setBoxStrokeColor(0xFFFFFFFF);
		DialogProperties apkPickp = new DialogProperties();
		apkPickp.selection_mode = DialogConfigs.SINGLE_MODE;
		apkPickp.selection_type = DialogConfigs.FILE_SELECT;
		apkPickp.root = new java.io.File("/storage/emulated/0/");
		apkPickp.error_dir = new java.io.File("/storage/emulated/0/");
		apkPickp.offset = new java.io.File("/storage/emulated/0/");
		apkPickp.extensions = new String[] {".apk"};
		apkPick = new FilePickerDialog(MainActivity.this,apkPickp);
		apkPick.setTitle("Choose Apk");
		apkPick.setPositiveBtnName("Select");
		apkPick.setNegativeBtnName("Cancel");
		DialogProperties obbPickp = new DialogProperties();
		obbPickp.selection_mode = DialogConfigs.SINGLE_MODE;
		obbPickp.selection_type = DialogConfigs.FILE_SELECT;
		obbPickp.root = new java.io.File("/storage/emulated/0/");
		obbPickp.error_dir = new java.io.File("/storage/emulated/0/");
		obbPickp.offset = new java.io.File("/storage/emulated/0/");
		obbPickp.extensions = new String[] {".obb"};
		obbPick = new FilePickerDialog(MainActivity.this,obbPickp);
		obbPick.setTitle("Choose obb");
		obbPick.setPositiveBtnName("Select");
		obbPick.setNegativeBtnName("Cancel");
		DialogProperties XapkIconp = new DialogProperties();
		XapkIconp.selection_mode = DialogConfigs.SINGLE_MODE;
		XapkIconp.selection_type = DialogConfigs.FILE_SELECT;
		XapkIconp.root = new java.io.File("/storage/emulated/0/");
		XapkIconp.error_dir = new java.io.File("/storage/emulated/0/");
		XapkIconp.offset = new java.io.File("/storage/emulated/0/");
		XapkIconp.extensions = new String[] {".png"};
		XapkIcon = new FilePickerDialog(MainActivity.this,XapkIconp);
		XapkIcon.setTitle("Choose Icon");
		XapkIcon.setPositiveBtnName("Select");
		XapkIcon.setNegativeBtnName("Cancel");
		_AcfivityFont("myfont");
	}
	
	@Override
	public void onBackPressed() {
		a.setClass(getApplicationContext(), HomeActivity.class);
		startActivity(a);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		if (data.contains("hhh")) {
			appp = new AlertDialog.Builder(MainActivity.this).create();
			LayoutInflater apppLI = getLayoutInflater();
			View apppCV = (View) apppLI.inflate(R.layout.info, null);
			appp.setView(apppCV);
			FontUtil.applyFont(MainActivity.this, apppCV);
			final TextView textt = (TextView)
			apppCV.findViewById(R.id.textview1);
			final LinearLayout linearr = (LinearLayout)
			apppCV.findViewById(R.id.linear1);
			final Button buttonn = (Button)
			apppCV.findViewById(R.id.button1);
			final TextView textt2 = (TextView)
			apppCV.findViewById(R.id.textview2);
			appp.setCancelable(true);
			appp.show();
			linearr.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)20, 0xFF101D24));
			buttonn.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)20, 0xFF38424A));
			textt.setText(data.getString("iii", ""));
			textt2.setText(data.getString("hhh", ""));
			buttonn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					Package_name = textt2.getText().toString();
					try {
						android.content.pm.PackageInfo pinfo = getPackageManager().getPackageInfo( Package_name, android.content.pm.PackageManager.GET_ACTIVITIES);
						vCode = pinfo.versionCode; }
					catch (Exception e){
						    SketchwareUtil.showMessage(getApplicationContext(), e.toString());
					}
					try {
						android.content.pm.PackageInfo pinfo = getPackageManager().getPackageInfo( Package_name, android.content.pm.PackageManager.GET_ACTIVITIES);
						vName = pinfo.versionName; }
					catch (Exception e){ 
						    SketchwareUtil.showMessage(getApplicationContext(), e.toString());
						 }
					try {
							android.content.pm.PackageInfo pinfo = getPackageManager().getPackageInfo( Package_name,
							android.content.pm.PackageManager.GET_ACTIVITIES);
							tarSDK = pinfo.applicationInfo.targetSdkVersion; }
					catch (Exception e){ 
						    SketchwareUtil.showMessage(getApplicationContext(), e.toString());
						 }
					try {
							android.content.pm.PackageInfo pinfo = getPackageManager().getPackageInfo( Package_name,
							android.content.pm.PackageManager.GET_ACTIVITIES);
							minSDK = pinfo.applicationInfo.minSdkVersion; }
					catch (Exception e){ 
						    SketchwareUtil.showMessage(getApplicationContext(), e.toString());
						 }
					edittext3.setText(textt.getText().toString());
					edittext4.setText(textt2.getText().toString());
					edittext5.setText(String.valueOf((long)(minSDK)));
					edittext6.setText(String.valueOf((long)(tarSDK)));
					edittext7.setText(vName);
					edittext8.setText(String.valueOf((long)(vCode)));
					appp.dismiss();
					SketchwareUtil.showMessage(getApplicationContext(), "Apk Info Applied Successfully!");
					data.edit().remove("hhh").commit();
					data.edit().remove("iii").commit();
					a.removeExtra("hhh");
					a.removeExtra("iii");
				}
			});
		}
		else {
			SketchwareUtil.showMessage(getApplicationContext(), "💯💯💯");
		}
	}
	public void _background_working_lib() {
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
	{
	}
	
	
	public void _rippleRoundStroke(final View _view, final String _focus, final String _pressed, final double _round, final double _stroke, final String _strokeclr) {
		android.graphics.drawable.GradientDrawable GG = new android.graphics.drawable.GradientDrawable();
		GG.setColor(Color.parseColor(_focus));
		GG.setCornerRadius((float)_round);
		GG.setStroke((int) _stroke,
		Color.parseColor("#" + _strokeclr.replace("#", "")));
		android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor(_pressed)}), GG, null);
		_view.setBackground(RE);
	}
	
	
	public void _AcfivityFont(final String _fontname) {
		fontName = "fonts/".concat(_fontname.concat(".ttf")); 
		overrideFonts(this,getWindow().getDecorView()); 
	} 

	// Делает текст заголовка и сообщения в ProgressDialog тёмным и читаемым на белом фоне,
	// и применяет к нему кастомный шрифт. Вызывается сразу после progressDialog.show().
	private void fixDialogTextColors(ProgressDialog dialog) {
		try {
			int darkText = 0xFF101D24;
			int id1 = android.R.id.message;
			TextView messageView = dialog.findViewById(id1);
			if (messageView != null) {
				messageView.setTextColor(darkText);
			}
			int titleId = getResources().getIdentifier("alertTitle", "id", "android");
			if (titleId != 0) {
				TextView titleView = dialog.findViewById(titleId);
				if (titleView != null) {
					titleView.setTextColor(darkText);
				}
			}
			FontUtil.applyFont(this, dialog.getWindow().getDecorView());
		} catch (Exception e) {
			// игнорируем, если структура диалога отличается на конкретном устройстве
		}
	}

	private void overrideFonts(final android.content.Context context, final View v) {
		
		try {
			 Typeface  
			 typeace = Typeface.createFromAsset(getAssets(), fontName);; 
			 if ((v instanceof ViewGroup)) { 
				  ViewGroup vg = (ViewGroup) v;
				  for (int i = 0;
				  i < vg.getChildCount();
				  i++) {
					   View child = vg.getChildAt(i);
					   overrideFonts(context, child);
					  } 
				 } 
			 else { 
				  if ((v instanceof TextView)) { 
					   ((TextView) v).setTypeface(typeace); 
					  } 
				  else { 
					   if ((v instanceof EditText )) { 
						    ((EditText) v).setTypeface(typeace); 
						   } 
					   else { 
						    if ((v instanceof Button)) { 
							     ((Button) v).setTypeface(typeace);
							    } 
						   } 
					  } 
				 } 
		} 
		catch(Exception e){ 
		};
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
