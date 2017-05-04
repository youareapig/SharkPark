package com.weiye.lead;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.weiye.zl.MainActivity;
import com.weiye.zl.R;


/**
 * Created by Administrator on 2016/12/15 0015.
 */
public class Five extends Fragment {
    private ImageView lead_go;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lead_5, container, false);
        lead_go = (ImageView) view.findViewById(R.id.lead_go);
        lead_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }
}
