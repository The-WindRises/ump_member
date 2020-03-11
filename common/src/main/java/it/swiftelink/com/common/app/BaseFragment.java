package it.swiftelink.com.common.app;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/7/10.
 */

public abstract class BaseFragment extends Fragment {

    protected View mRootView;
    private Unbinder mUnbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initArgs();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mRootView == null) {
            int layoutId = getLayoutId();
            //初始化跟布局，但不添加到container，当return的时候进行添加
            View rootView = inflater.inflate(layoutId, container, false);

            initWidget(rootView);
            mRootView = rootView;
        } else {

            if (mRootView.getParent() != null) {
                ((ViewGroup) mRootView.getParent()).removeView(mRootView);
            }
        }


        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }


    protected void initArgs() {

    }

    /**
     * 获取布局id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化控件
     */
    protected void initWidget(View view) {

        mUnbinder = ButterKnife.bind(this, view);
    }

    /**
     * 初始化数据
     */
    protected void initData() {

    }

    protected boolean onBackPressd() {
        return false;
    }

}
