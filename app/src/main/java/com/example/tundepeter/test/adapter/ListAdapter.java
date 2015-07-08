package com.example.tundepeter.test.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tundepeter.test.R;
import com.example.tundepeter.test.model.Person;
import com.example.tundepeter.test.utils.ImageLoaderProvider;

import java.util.List;

public class ListAdapter extends ArrayAdapter {
    private List<Person> list;

    public ListAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        list = objects;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Person getItem(int location) {
        return list.get(location);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Person person = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.nameView);
            viewHolder.description = (TextView) convertView.findViewById(R.id.descriptionView);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.name.setText(person.getName());
        viewHolder.description.setText(person.getDescription());

        ImageLoaderProvider.getInstance(getContext()).getLoader().displayImage(Uri.parse(person.getSmallPictureUrl()).toString(), viewHolder.imageView);

        // Return the completed view to render on screen
        return convertView;
    }

    // View lookup cache
    private static class ViewHolder {
        TextView name;
        TextView description;
        ImageView imageView;
    }
}

