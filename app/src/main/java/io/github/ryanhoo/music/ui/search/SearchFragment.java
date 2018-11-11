package io.github.ryanhoo.music.ui.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import io.github.ryanhoo.music.R;
import io.github.ryanhoo.music.nodeAPI.RequestAPI;
import io.github.ryanhoo.music.ui.base.BaseFragment;

public class SearchFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_search, container, false);
        final SearchView searchView = view.findViewById(R.id.search_bar);
        final TextView textView = view.findViewById(R.id.search_result);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                RequestAPI.getInstance().query(s);
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
