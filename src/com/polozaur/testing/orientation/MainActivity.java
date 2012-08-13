package com.polozaur.testing.orientation;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

/**
 * Class used for keeping track of progress dialog on orientation change, this does not save the 
 * async task state ( in this case the async is static )
 */
public class MainActivity extends Activity {
	
	private static ProgressTask pTask;
	static final int PROGRESS_DIALOG = 1;
	private static final String TAG = MainActivity.class.getSimpleName();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		if(pTask == null) {
			Log.e(TAG, "NEW TASK REQUIRED");
			pTask = new ProgressTask(this);
			pTask.execute();
		}
		else {
			if (pTask.getStatus() == Status.RUNNING) {
				Log.e(TAG, "TASK RUNNING");
				pTask.setContext(this);
			}
			else {
				Log.e(TAG, "TASK FINISHED");
			}
		}
	}
	
	//using this method Android OS keeps track of dialogs created and handles orientation
	//changes automatically
	@Override
	protected Dialog onCreateDialog(int id) {
		if(id == 1) {
			return ProgressDialog.show(this, 
					"Please wait", "Computing ...", true, true);
		}
		return super.onCreateDialog(id);
	}
	
	@Override
	public Object onRetainNonConfigurationInstance() {
		pTask.setContext(null);
		return super.onRetainNonConfigurationInstance();
	}

}
