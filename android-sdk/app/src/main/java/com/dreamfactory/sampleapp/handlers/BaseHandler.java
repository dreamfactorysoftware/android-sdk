package com.dreamfactory.sampleapp.handlers;

import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import com.dreamfactory.sampleapp.models.ErrorMessage;

/**
 * Created by Murtic on 08/05/16.
 */
public abstract class BaseHandler extends HandlerThread {

    protected Handler mHandler = null;

    protected boolean autoRelease = true;

    public BaseHandler(String name) {
        super(name);

        start();

        mHandler = new ServiceHandler(getLooper());
    }

    public void close() {
        if(Build.VERSION.SDK_INT > 17) {
            mHandler.getLooper().quitSafely();
        } else {
            mHandler.getLooper().quit();
        }
    }

    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            msg.getCallback().run();

            if(autoRelease) {
                close();
            }
        }
    }

    protected interface BaseCallback {
        void onError(ErrorMessage.Error e);
    }

    public void setAutoRelease(boolean autoRelease) {
        this.autoRelease = autoRelease;
    }
}
