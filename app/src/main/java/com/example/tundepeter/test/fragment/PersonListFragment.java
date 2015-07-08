package com.example.tundepeter.test.fragment;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.tundepeter.test.R;
import com.example.tundepeter.test.adapter.ListAdapter;
import com.example.tundepeter.test.json.JSONLoader;
import com.example.tundepeter.test.model.Person;
import com.example.tundepeter.test.utils.ConnectivityService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonListFragment extends Fragment implements AdapterView.OnItemClickListener  {
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
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (ConnectivityService.isNetworkConnection(getActivity())) {
            new LoaderAsynctask().execute();
        } else {
            // show toast if no network connection
            Toast.makeText(getActivity(), getResources().getText(R.string.no_network_connection), Toast.LENGTH_LONG).show();
        }
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mListener.onPersonSelected((Person) adapter.getItem(position));
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

    private class LoaderAsynctask extends AsyncTask<Void, Void, List> {

        @Override
        protected void onPreExecute() {
            adapter.clear();
            ProgressBar progressBar = (ProgressBar) getActivity().findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);
        }

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
            if (list == null) {
                Toast.makeText(getActivity(), getResources().getText(R.string.json_not_parsed), Toast.LENGTH_LONG).show();
                return;
            }
            adapter.addAll(list);
            getActivity().findViewById(R.id.progressBar).setVisibility(View.GONE);
        }
    }
}
