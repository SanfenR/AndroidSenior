package com.mz.sanfen.canvasshader.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mz.sanfen.canvasshader.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RadialFragment extends Fragment {


    public static RadialFragment newInstance() {
        
        Bundle args = new Bundle();
        
        RadialFragment fragment = new RadialFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    public RadialFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_radial, container, false);
    }

}
