package ch.fp.fp_kks;
//banane

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Background for using int ids and String text in every class
 * <p>
 * Created by Patrick on 24.08.2017.
 */
public class Background extends Service {

    static int ids = 1;
    static Boolean text2 = false;
    static Boolean kategroy = false;

    static int correct = 0, wrong = 0;

    /**
     * @param intent;
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * @param intent;
     * @param flags;
     * @param startId;
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    /**
     *
     */
    @Override
    public void onDestroy() {
        Toast.makeText(this, "on Destroy call", Toast.LENGTH_SHORT).show();
    }
}
