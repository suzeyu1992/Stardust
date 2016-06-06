package com.szy.stardust.fm.home.myinfo;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.szy.stardust.R;

/**
 * author: suzeyu on 16/6/6 18:03
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description :
 */
public class HomePagerFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    public static HomePagerFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        HomePagerFragment pageFragment = new HomePagerFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page_fragment_myinfo, container, false);
        TextView textView = (TextView) view;
        //textView.setText("Fragment #" + mPage);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(" ");
        Drawable drawable = getActivity().getResources().getDrawable(R.mipmap.tab_home_find);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth()*2, drawable.getIntrinsicHeight()*2);
        ImageSpan imageSpan = new ImageSpan(drawable,ImageSpan.ALIGN_BOTTOM);
        spannableStringBuilder.setSpan(imageSpan,0,1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        spannableStringBuilder.append("我是追加文字");
        textView.setText(spannableStringBuilder);
        return view;    }
}
