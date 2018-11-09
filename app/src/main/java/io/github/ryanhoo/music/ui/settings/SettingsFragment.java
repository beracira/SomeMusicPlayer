package io.github.ryanhoo.music.ui.settings;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.ryanhoo.music.R;
import io.github.ryanhoo.music.ui.base.BaseFragment;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 9/1/16
 * Time: 9:59 PM
 * Desc: SettingsFragment
 */
public class SettingsFragment extends BaseFragment {

    @BindView(R.id.button_weibo_login)
    Button buttonWeibo;

    @OnClick(R.id.button_weibo_login)
    public void weiboLogin() {
        Log.d(this.getClass().toString(), "login button");
        Intent intent = new Intent(getActivity(), WeiboLoginActivity.class);
        startActivityForResult(intent, 1);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Toast.makeText(getActivity(), R.string.mp_fragment_setting_login_successful, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getActivity(), R.string.mp_fragment_setting_login_failed, Toast.LENGTH_LONG).show();
        }
    }


}
