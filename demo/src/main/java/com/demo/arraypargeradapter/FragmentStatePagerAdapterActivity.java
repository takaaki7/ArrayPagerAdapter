package com.demo.arraypargeradapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nakama.arraypageradapter.ArrayFragmentStatePagerAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

import static butterknife.ButterKnife.findById;

/**
 * Created by nakama on 2016/03/31.
 */
public class FragmentStatePagerAdapterActivity extends AppCompatActivity {

    @Bind(R.id.control_view)
    ControlView controlView;
    @Bind(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        ButterKnife.bind(this);
        ArrayList<Dog> dogs = new ArrayList<>();
        ControlView.DogItemCreator creator = new ControlView.DogItemCreator();
        for (int i = 0; i < 4; i++) {
            dogs.add(creator.createItem());
        }
        MyStatePagerAdapter adapter = new MyStatePagerAdapter(getSupportFragmentManager(), dogs);
        viewPager.setAdapter(adapter);
        controlView.setAdapter(adapter);
        controlView.setItemCreator(creator);
    }

    private class MyStatePagerAdapter extends ArrayFragmentStatePagerAdapter<Dog> {

        public MyStatePagerAdapter(FragmentManager fm, ArrayList<Dog> dogs) {
            super(fm, dogs);
        }

        @Override
        public Fragment getFragment(Dog item, int position) {
            return MyFragment.newInstance(item);
        }
    }


    public static class MyFragment extends Fragment {
        static MyFragment newInstance(Dog dog) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("data", dog);
            MyFragment f = new MyFragment();
            f.setArguments(bundle);
            return f;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            super.onCreateView(inflater, container, savedInstanceState);
            View v = inflater.inflate(R.layout.item_dog_page, container, false);
            Dog dog = getArguments().getParcelable("data");
            ((TextView) findById(v, R.id.name_txt)).setText(dog.getName());
            ((TextView) findById(v, R.id.favorite_txt)).setText(dog.getFavoriteFood());
            ((TextView) findById(v, R.id.age_txt)).setText(String.valueOf(dog.getAge()));
            return v;
        }
    }

}
