package io.github.ryanhoo.music.ui.search;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import butterknife.BindView;
import io.github.ryanhoo.music.R;
import io.github.ryanhoo.music.nodeAPI.NodeInterface;
import io.github.ryanhoo.music.ui.base.BaseFragment;

public class SearchFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_search, container, false);
        final SearchView searchView = (SearchView) view.findViewById(R.id.search_bar);
        final TextView textView = (TextView) view.findViewById(R.id.search_result);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                NodeInterface nodeInterface = NodeInterface.getInstance(view.getContext().getApplicationContext());
                final String s_ = s;
                new AsyncTask<Void,Void,String>() {
                    @Override
                    protected String doInBackground(Void... params) {
                        String nodeResponse="";
                        try {
                            URL localNodeServer = new URL("http://localhost:3000"
                                    + s_);
                            URLConnection urlConnection = localNodeServer.openConnection();
                            urlConnection.addRequestProperty("Cookie", "MUSIC_U=25bfb3af047026bfc193df9e6adb5f9d67e1da515f8135b3a29b1b4642105ba8d0836841826eba49db0bf06058486ead8bafcdfe5ad2b092; Expires=Fri, 23-Nov-2018 00:53:42 GMT; Path=/; HttpOnly");
                            urlConnection.addRequestProperty("Cookie", "__remember_me=true; Expires=Fri, 23-Nov-2018 00:53:42 GMT; Path=/; HttpOnly");
                            urlConnection.addRequestProperty("Cookie", "__csrf=162dd902f99fb1af7bcbdaf833775c5d; Expires=Fri, 23-Nov-2018 00:53:52 GMT; Path=/");
                            urlConnection.connect();
                            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(urlConnection.getInputStream()));
                            String inputLine;
                            while ((inputLine = in.readLine()) != null)
                                nodeResponse=nodeResponse+inputLine;
                            in.close();
                        } catch (Exception ex) {
                            nodeResponse=ex.toString();
                        }
                        return nodeResponse;
                    }
                    @Override
                    protected void onPostExecute(String result) {
                        textView.setText(result);
                    }
                }.execute();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return view;
    }
}
