package com.example.tundepeter.test.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;

import com.example.tundepeter.test.activity.ProfileActivity;
import com.example.tundepeter.test.model.Person;

public class NavigationService {
    public void navigateToProfileActivity(ActionBarActivity activity, Person person) {
        Intent intent = new Intent(activity, ProfileActivity.class);
        intent.putExtra(ProfileActivity.EXTRA_IMAGE_URL, person.getLargePictureUrl());
        activity.startActivity(intent);
    }
}
