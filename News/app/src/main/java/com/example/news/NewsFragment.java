package com.example.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NewsFragment extends Fragment {
    String data;
    private TextView tv;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView (@Nullable LayoutInflater inflater, @Nullable ViewGroup container , @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.list_item,container,false);
        tv = view.findViewById(R.id.text_response);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        onAttach(getActivity());
        Bundle bundle = getArguments();
        data = bundle.getString("name","top");
        tv.setText(data);
    }
}
