package com.example.anvita.quiz;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by anvita on 10/9/16.
 */

public class AdditionFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Log.d("view","reach add frag");
        return inflater.inflate(R.layout.fragment_addition, container, false);
    }

}
