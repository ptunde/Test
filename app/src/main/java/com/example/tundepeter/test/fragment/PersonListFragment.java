package com.example.tundepeter.test.fragment;

import android.app.Activity;
import android.app.ListFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tundepeter.test.R;
import com.example.tundepeter.test.adapter.ListAdapter;
import com.example.tundepeter.test.json.JSONLoader;
import com.example.tundepeter.test.model.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonListFragment extends ListFragment {
    private static final String JSON_URL = "https://s3-us-west-2.amazonaws.com/wirestorm/assets/response.json";
    private OnPersonSelectedListener mListener;
    private ArrayAdapter adapter;

    public PersonListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_person_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        adapter = new ListAdapter(getActivity(), R.id.list_item, new ArrayList());

        ListView listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        new AsyncTask<Void, Void, List>() {
            @Override
            protected List doInBackground(Void... params) {
                JSONLoader jsonLoader = new JSONLoader();
                List list = null;
                try {
                    list = jsonLoader.getJsonFromUrl(JSON_URL);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return list;
            }

            @Override
            protected void onPostExecute(List list) {
                adapter.addAll(list);
            }
        }.execute();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnPersonSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnPersonSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnPersonSelectedListener {
        public void onPersonSelected(Person person);
    }
}
