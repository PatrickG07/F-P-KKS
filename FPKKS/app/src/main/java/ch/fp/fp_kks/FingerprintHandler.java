package ch.fp.fp_kks;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Toast;

/**
 * Created by garte on 26.02.2018.
 */

/**
 * FingerprintHandler for the Fingerprint interface
 */
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback{

    private Context context;

    MainActivity Main = new MainActivity();

    public FingerprintHandler(Context context) {
        this.context = context;
    }

    public void startAuthentication(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject) {
        CancellationSignal canCellationSignal = new CancellationSignal();
        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED)
            return;
        fingerprintManager.authenticate(cryptoObject,canCellationSignal,0,this,null);

    }

    /**
     * if the Fingerprint was Correct
     */
    @Override
    public void onAuthenticationFailed() {
        super.onAuthenticationFailed();
        Toast.makeText(context, "Fingerprint Authentification failed", Toast.LENGTH_SHORT).show();
    }

    /**
     * if the Fingerprint war Wrong
     *
     * @param result
     */
    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        super.onAuthenticationSucceeded(result);
        context.startActivities(new Intent[]{new Intent(context, EditActivity.class)});
    }
}
