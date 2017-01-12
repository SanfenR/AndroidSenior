package com.mz.sanfen.lazyfragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class OtherFragment extends Fragment {

    private static final String TAG = "OneFragment";

    FrameLayout frameLayout;

    View contentView;

    TextView textView;




    public OtherFragment() {
        // Required empty public constructor
    }

    public static OtherFragment newInstance() {
        Bundle args = new Bundle();

        OtherFragment fragment = new OtherFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_other, container, false);
        frameLayout = (FrameLayout) inflate.findViewById(R.id.one_frame);
        Log.e(TAG, "onCreateView: " + frameLayout );
        return inflate;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e(TAG, "setUserVisibleHint: " + isVisibleToUser );
        if (isVisibleToUser) {
            if (contentView == null ) {
                contentView = LayoutInflater.from(getActivity()).inflate(R.layout.content_other, frameLayout, true);
                textView = (TextView) contentView.findViewById(R.id.one_text);
                textView.setText("load other fragment");
            }
        }
    }
}
