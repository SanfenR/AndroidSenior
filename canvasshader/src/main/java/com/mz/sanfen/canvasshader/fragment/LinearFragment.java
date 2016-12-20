package com.mz.sanfen.canvasshader.fragment;


import android.graphics.Shader;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.mz.sanfen.canvasshader.R;
import com.mz.sanfen.canvasshader.custom.LinearView;

/**
 * A simple {@link Fragment} subclass.
 */
public class LinearFragment extends Fragment {


    public static LinearFragment newInstance() {
        
        Bundle args = new Bundle();
        
        LinearFragment fragment = new LinearFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    public LinearFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_linear, container, false);
    }


}
