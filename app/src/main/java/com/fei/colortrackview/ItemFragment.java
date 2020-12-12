package com.fei.colortrackview;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @ClassName: ItemFragment
 * @Description: java类作用描述
 * @Author: Fei
 * @CreateDate: 2020-12-12 10:06
 * @UpdateUser: 更新者
 * @UpdateDate: 2020-12-12 10:06
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ItemFragment extends Fragment {

    private static final String ARG_TEXT = "text";
    private String text;

    public static ItemFragment newInstance(String text) {
        ItemFragment fragment = new ItemFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TEXT, text);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        text = getArguments().getString(ARG_TEXT);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView tv = view.findViewById(R.id.text);
        tv.setText(text);
    }
}
