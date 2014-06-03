package kr.androy.base;

import android.app.Application;

public class AndroyApplication extends Application {

	public static AndroyApplication _internalInstance;
	
	@Override
	public void onCreate() {
		super.onCreate();
		_internalInstance = this;
		
		//http://code.google.com/p/android/issues/detail?id=20915
		try {
			Class.forName("android.os.AsyncTask");
		} catch (ClassNotFoundException e) {
		}
		
		
	}

}
