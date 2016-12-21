package com.mz.sanfen.canvasshader.fragment;


import android.graphics.Shader;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.mz.sanfen.canvasshader.R;
import com.mz.sanfen.canvasshader.custom.BitmapView;
import com.mz.sanfen.canvasshader.custom.LinearView;
import com.mz.sanfen.canvasshader.custom.RadialView;
import com.mz.sanfen.canvasshader.custom.SweepView;

/**
 * A simple {@link Fragment} subclass.
 */
public class BitmapFragment extends Fragment {

    public BitmapView bv;
    public LinearView lv;
    public RadialView rv;
    public SweepView sv;

    public RadioGroup rg_config, rg_show;

    public static BitmapFragment newInstance() {

        Bundle args = new Bundle();

        BitmapFragment fragment = new BitmapFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public BitmapFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bitmap, container, false);
        bv = (BitmapView) view.findViewById(R.id.bv);
        lv = (LinearView) view.findViewById(R.id.lv);
        rv = (RadialView) view.findViewById(R.id.rv);
        sv = (SweepView) view.findViewById(R.id.sv);

        rg_config = (RadioGroup) view.findViewById(R.id.rg_config);
        rg_show = (RadioGroup) view.findViewById(R.id.rg_show);
        initView(view);
        return view;
    }

    private void initView(View view) {
        rg_config.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_clamp:
                        bv.setTileMode(Shader.TileMode.CLAMP);
                        lv.setTileMode(Shader.TileMode.CLAMP);
                        rv.setTileMode(Shader.TileMode.CLAMP);
                        break;
                    case R.id.rb_repeat:
                        bv.setTileMode(Shader.TileMode.REPEAT);
                        lv.setTileMode(Shader.TileMode.REPEAT);
                        rv.setTileMode(Shader.TileMode.REPEAT);
                        break;
                    case R.id.rb_mirror:
                        bv.setTileMode(Shader.TileMode.MIRROR);
                        lv.setTileMode(Shader.TileMode.MIRROR);
                        rv.setTileMode(Shader.TileMode.MIRROR);
                        break;
                }
            }
        });
        rg_show.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_bitmap:
                        show(bv, lv, rv, sv);
                        break;
                    case R.id.rb_linear:
                        show(lv, bv, rv, sv);
                        break;
                    case R.id.rb_Radial:
                        show(rv, lv, bv, sv);
                        break;
                    case R.id.rb_sweep:
                        show(sv, rv, lv, bv);
                }
            }
        });
    }


    public void show(View... views){
        views[0].setVisibility(View.VISIBLE);
        for(int i = 1; i < views.length; i++){
            views[i].setVisibility(View.GONE);
        }
    }

}
