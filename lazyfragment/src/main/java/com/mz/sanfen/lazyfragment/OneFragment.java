package com.mz.sanfen.lazyfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends Fragment {

    FrameLayout frameLayout;

    View contentView;

    TextView textView;

    public OneFragment() {
        // Required empty public constructor
    }

    public static OneFragment newInstance() {
        Bundle args = new Bundle();

        OneFragment fragment = new OneFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_one, container, false);
        frameLayout = (FrameLayout) inflate.findViewById(R.id.one_frame);
        return inflate;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (contentView == null) {
                contentView = LayoutInflater.from(getActivity()).inflate(R.layout.content_one, frameLayout, false);
                textView = (TextView) contentView.findViewById(R.id.one_text);
                textView.setText("load one fragment");
            }
        }
    }


}
