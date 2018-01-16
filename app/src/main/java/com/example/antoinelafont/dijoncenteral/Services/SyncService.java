package com.example.antoinelafont.dijoncenteral.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.antoinelafont.dijoncenteral.Adapter.SyncAdapter;

/**
 * Created by Antoine LAFONT on 18/12/2017.
 */

public class SyncService extends Service {

    private static SyncAdapter syncAdapter = null;
    private static final Object syncAdapterLock = new Object();

    public void onCreate() {
        synchronized (syncAdapterLock) {
            if(syncAdapter == null) {
                syncAdapter = new SyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return syncAdapter.getSyncAdapterBinder();
    }
}
