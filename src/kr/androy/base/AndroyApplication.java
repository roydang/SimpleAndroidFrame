package kr.androy.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kr.androy.base.config.worker.Worker;
import android.app.Application;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

public class AndroyApplication extends Application {
	
	private static AndroyApplication _internalInstance;
	public static String BACKGROUND_HANDLER_THREAD = "AndroyBackgroundHandlerThread";
	
	private ExecutorService workExecutor;
	private Handler uiHandler;
	private Handler backgroundHandler;
	private HandlerThread backgroundHandlerThread;
	
	@Override
	public void onCreate() {
		_internalInstance = this;
		
		//http://code.google.com/p/android/issues/detail?id=20915
		try {
			Class.forName("android.os.AsyncTask");
		} catch (ClassNotFoundException e) {
		}
		
		if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.FROYO) {
			System.setProperty("http.keepAlive", "false");
		}
		super.onCreate();
		
		init();
	}
	private void init() {
		workExecutor = Executors.newCachedThreadPool();
		uiHandler = new Handler(Looper.getMainLooper());
		
		backgroundHandlerThread = new HandlerThread(BACKGROUND_HANDLER_THREAD);
		backgroundHandlerThread.start();
		
		backgroundHandler = new Handler(backgroundHandlerThread.getLooper());
	}
	public static AndroyApplication getApplication() {
		return _internalInstance;
	}
	public static Handler getUiHandler() {
		return getApplication().uiHandler;
	}
	public static Handler getBackgroundHandler() {
		return getApplication().backgroundHandler;
	}
	public void addWorker(Worker worker) {
		if (worker == null) {
			return;
		}
		workExecutor.execute((Runnable)worker);
	}

	public void addWorker(Runnable runnable) {
		if (runnable == null) {
			return;
		}
		workExecutor.execute(runnable);
	}

}
