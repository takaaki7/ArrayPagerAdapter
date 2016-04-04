package com.demo.arraypargeradapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nakama.arraypageradapter.ArrayFragmentPagerAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nakama on 2016/04/02.
 */
public class FragmentPagerAdapterActivity extends AppCompatActivity {
    @Bind(R.id.control_view)
    ControlView controlView;
    @Bind(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        ButterKnife.bind(this);
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        final MyStatePagerAdapterStateSerializable adapter = new MyStatePagerAdapterStateSerializable(getSupportFragmentManager(), list);

        viewPager.setAdapter(adapter);
        controlView.setAdapter(adapter);
    }

    private class MyStatePagerAdapterStateSerializable extends ArrayFragmentPagerAdapter<String> {

        public MyStatePagerAdapterStateSerializable(FragmentManager fm, ArrayList<String> datas) {
            super(fm, datas);
        }

        @Override
        public Fragment getFragment(String item, int position) {
            return MyFragment.newInstance(item);
        }

    }

    public static class MyFragment extends Fragment {
        static MyFragment newInstance(String text) {
            Bundle bundle = new Bundle();
            bundle.putString("data", text);
            MyFragment f = new MyFragment();
            f.setArguments(bundle);
            return f;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            TextView textView = new TextView(getActivity());
            textView.setText(getArguments().getString("data"));
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(24);
            textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return textView;
        }
    }
}
