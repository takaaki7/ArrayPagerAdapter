ArrayPagerAdapter
=================

Small and simple library that has custom ViewPager adapters which are able to change data dynamically.
![demo](https://raw.githubusercontent.com/takaaki7/ArrayPagerAdapter/master/art/demo_record.gif)

Sample application is available on the Play Store.

[![Get it on Google Play](http://www.android.com/images/brand/get_it_on_play_logo_large.png)](https://play.google.com/store/apps/details?id=com.demo.arraypargeradapter&referrer=utm_source%3Dgithub)

# Usage

This library includes 3 useful adapters like general ViewPager's : `FragmentStatePagerAdapter`, `FragmentPagerAdapter`, and `PagerAdapter`.

ArrayPagerAdapters contain items passed from outside internally. You can change data dynamically by calling adapter's methods like `add(T item)`, `remove(int position)`.  
## 1 ArrayFragmentStatePagerAdapter

Implemented based on `android.support.v4.app.FragmentStatePagerAdapter`.

Like `FragmentStatePagerAdapter`, it is recommended to use this adapter if there are large number of pages or need to save fragments' and items' state.
This adapter handles saving and restoring of fragments' and items' state.

Subclasses only need to implement `getFragment(T item, int position)` and return a fragment that display the page.

Here is a simple example implementation of ArrayFragmentStatePagerAdapter.
```
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pager);
    MyStatePagerAdapter adapter = new MyStatePagerAdapter(getSupportFragmentManager(), new String[]{"1", "2", "3"});
    ((ViewPager)findViewById(R.id.view_pager)).setAdapter(adapter);
}

class MyStatePagerAdapter extends ArrayFragmentStatePagerAdapter<String> {

    public MyStatePagerAdapter(FragmentManager fm, String[] datas) {
        super(fm, datas);
    }

    @Override
    public Fragment getFragment(String item, int position) {
        return MyFragment.newInstance(item);
    }
}
```

__Constraints:__ Item class (generic type `T`) must be implementation of `Serializable` or `Parcelable` because the adapter automatically save and restore items state.
For example, above example use `String` that is implementation of `Serializable`.

## 2 ArrayFragmentPagerAdapter

Implemented based on `android.support.v4.app.FragmentPagerAdapter`.

Compared to `ArrayFragmentStatePagerAdapter`, you can use this adapter if there is a few number of pages and no need to save states (or implement by yourself).

Usage is almost the same as `ArrayFragmentStatePagerAdapter`, and item class does not need to implement `Serializable` or `Parcelable`.
```
class MyFragmentPagerAdapter extends ArrayFragmentPagerAdapter<String> {

    public MyFragmentPagerAdapter(FragmentManager fm, String[] datas) {
        super(fm, datas);
    }

    @Override
    public Fragment getFragment(String item, int position) {
        return MyFragment.newInstance(item);
    }
}
```

## 3 ArrayViewPagerAdapter

If you want to use View to create page, you can use this adapter.
Subclass of `ArrayViewPagerAdapter` just need to implement `getView()` and return a view of the page.
 
```
class MyPagerAdapter extends ArrayViewPagerAdapter<String> {

    public MyPagerAdapter(String[] data) {
        super(data);
    }

    @Override
    public View getView(LayoutInflater inflater, ViewGroup container, String item, int position) {
        View v = inflater.inflate(R.layout.item_page, container, false);
        ((TextView) v.findViewById(R.id.item_txt)).setText(item);
        return v;
    }
}
```

## Dynamic item changing
All of these adapters are subclass of `ArrayPagerAdapter` and have following methods to support dynamic data changing.

* `void add(T item)`
* `void addAll(T... items)`
* `void addAll(List<T> items)`
* `void remove(int position)`
* `void clear()`

You don't need to call `notifyDataSetChanged()` since it is called internally when you call above methods.

# Sample

Sample usage is available in demo directory.

# Download

```
compile 'com.nakama.arraypageradapter:arraypageradapter:0.1.1'
```



License
========

    Copyright (C) 2015 Takaaki Nakama (http://blog.charmas.pl)

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

	     http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.