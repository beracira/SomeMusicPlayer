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
import io.github.ryanhoo.music.nodeAPI.RequestAPI;
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
                new RequestAPI().execute(s);
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
