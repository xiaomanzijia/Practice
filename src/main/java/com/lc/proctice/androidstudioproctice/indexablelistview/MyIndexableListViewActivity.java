package com.lc.proctice.androidstudioproctice.indexablelistview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SectionIndexer;

import com.lc.proctice.androidstudioproctice.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by licheng on 29/10/15.
 */
public class MyIndexableListViewActivity extends Activity {
    private MyIndexableListView listView;
    private ArrayList<String> contentArray;
    private MyIndexableAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phoneindex);
        contentArray = new ArrayList<String>();
        contentArray.add("Diary of a Wimpy Kid 6: Cabin Fever");
        contentArray.add("Steve Jobs");
        contentArray.add("Inheritance (The Inheritance Cycle)");
        contentArray.add("11/22/63: A Novel");
        contentArray.add("The Hunger Games");
        contentArray.add("The LEGO Ideas Book");
        contentArray.add("Explosive Eighteen: A Stephanie Plum Novel");
        contentArray.add("Catching Fire (The Second Book of the Hunger Games)");
        contentArray.add("Elder Scrolls V: Skyrim: Prima Official Game Guide");
        contentArray.add("Death Comes to Pemberley");
        contentArray.add("Diary of a Wimpy Kid 6: Cabin Fever");
        contentArray.add("Steve Jobs");
        contentArray.add("Inheritance (The Inheritance Cycle)");
        contentArray.add("11/22/63: A Novel");
        contentArray.add("The Hunger Games");
        contentArray.add("The LEGO Ideas Book");
        contentArray.add("Explosive Eighteen: A Stephanie Plum Novel");
        contentArray.add("Catching Fire (The Second Book of the Hunger Games)");
        contentArray.add("Elder Scrolls V: Skyrim: Prima Official Game Guide");
        contentArray.add("做作");
        contentArray.add("wokao");
        Collections.sort(contentArray);
        adapter = new MyIndexableAdapter(this,android.R.layout.simple_list_item_1,contentArray);
        listView = (MyIndexableListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setFastScrollEnabled(true); // 设置快速滑动
    }

    private class MyIndexableAdapter extends ArrayAdapter implements SectionIndexer{
        private String mSections="#ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        public MyIndexableAdapter(Context context, int textViewResourceId, List<String> objects) {
            super(context, textViewResourceId, objects);
        }

        @Override
        public Object[] getSections() {
            String[] sections = new String[mSections.length()];
            for(int i = 0;i < mSections.length();i++){
                sections[i]= String.valueOf(mSections.charAt(i));
            }
            return sections;
        }

        @Override
        public int getPositionForSection(int section) {
            for (int i = section; i >= 0; i--) {
                for (int j = 0; j < getCount(); j++) {
                    System.out.println(getCount());
                    if (i == 0) { // #
                        // For numeric section 数字
                        for (int k = 0; k <= 9; k++) {// 1...9
                            // 字符串第一个字符与1~9之间的数字进行匹配
                            if (StringMatcher.match(
                                    String.valueOf(getItem(j).toString().charAt(0)),
                                    String.valueOf(k)))
                                return j;
                        }
                    } else { // A~Z
                        if (StringMatcher.match(
                                String.valueOf(getItem(j).toString().charAt(0)),
                                String.valueOf(mSections.charAt(i))))
                            return j;
                    }
                }
            }
            return 0;
        }

        @Override
        public int getSectionForPosition(int position) {
            return 0;
        }
    }
}
