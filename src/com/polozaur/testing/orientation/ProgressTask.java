package com.polozaur.testing.orientation;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.Toast;

/**
 * Task doing the heavy lifting
 */
class ProgressTask extends AsyncTask<Void, Void, Void> {
	
	private MainActivity context;
	
	ProgressTask (MainActivity context) {
		setContext(context);
	}
	
	@Override
	protected void onPreExecute() {
		this.context.showDialog(MainActivity.PROGRESS_DIALOG);
	}
	
	public void setContext (MainActivity context) {
		this.context = context;
	}

	@Override
	protected Void doInBackground(Void... params) {
		//simulate some work being done here ... for 5 seconds
		SystemClock.sleep(5000);
		return null;
	}
	
	@Override
	protected void onPostExecute(Void result) {
	    this.context.removeDialog(MainActivity.PROGRESS_DIALOG);
		Toast.makeText(this.context,
				"Task completed", Toast.LENGTH_SHORT).show();
	}

}
