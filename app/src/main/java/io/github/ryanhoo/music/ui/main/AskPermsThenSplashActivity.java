package io.github.ryanhoo.music.ui.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import io.github.ryanhoo.music.ui.base.BaseActivity;



// https://stackoverflow.com/questions/37845802/trouble-with-android-permissions-request-running-code-before-user-input

public class AskPermsThenSplashActivity extends BaseActivity {

    static final String[] _requestPermissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };


    @Override
    protected void onStart() {
        super.onStart();

        this.checkPermissions(0);   //start at zero, of course
    }

    private void checkPermissions(int permissionIndex) {
        if(permissionIndex >= _requestPermissions.length) {
            //i.e. we have requested all permissions, so show the splash screen
            this.showSplash();
        }
        else {
            this.askForPermission(_requestPermissions[permissionIndex], permissionIndex);
        }
    }

    private void askForPermission(String permission, int permissionIndex) {
        if (ContextCompat.checkSelfPermission(
                this,
                permission)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            //  if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {

            // Show an explanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.

            //} else {

            // No explanation needed, we can request the permission.

            ActivityCompat.requestPermissions(
                    this,
                    new String[]{permission},
                    permissionIndex //permissionIndex will become the requestCode on callback
            );
        }
        else {
            this.checkPermissions(permissionIndex+1); //check the next permission
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //Regardless of whether the permission was granted we carry on.
        //If perms have been denied then the app must cater for it
        switch (requestCode) {
            case 0: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    this.checkPermissions(requestCode+1); //check the next permission
                } else {
                    finishAndRemoveTask();
                }
            }
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    this.checkPermissions(requestCode+1); //check the next permission
                } else {
                    finishAndRemoveTask();
                }
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }

    }

    private void showSplash() {
        //(ta da)

        //once splashed, start the main activity
        this.startMainActivity();
    }

    private void startMainActivity() {
        Intent mainIntent = new Intent(
                this,
                MainActivity.class
        );
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        this.startActivity(mainIntent);

        this.finish();
    }
}
