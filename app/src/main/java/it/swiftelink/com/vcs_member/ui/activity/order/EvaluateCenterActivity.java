package it.swiftelink.com.vcs_member.ui.activity.order;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.BaseActivity;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.event.MsgEvent;
import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.ui.fragment.MyPagerAdapter;
import it.swiftelink.com.vcs_member.ui.fragment.order.EvaluateFragment;

public class EvaluateCenterActivity extends BaseActivity<BaseContract.Presenter> {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tb_my_evaluate)
    TabLayout tbMyEvaluate;
    @BindView(R.id.vp_my_evaluate)
    ViewPager vpMyEvaluate;


    private Integer[] titles ={R.string.tab_my_evaluate1,R.string.tab_my_evaluate2,R.string.tab_my_evaluate3};

    private List<Fragment> fragments = new ArrayList<>();
    private EvaluateFragment fragment1;
    private EvaluateFragment fragment2;
    private EvaluateFragment fragment3;

    public static void show(Activity activity){
        activity.startActivity(new Intent(activity,EvaluateCenterActivity.class));
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_evaluate_center;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MsgEvent classifyMsgEvent) {
        switch (classifyMsgEvent.getType()) {
            case Common.EventbusType.EVALUATE_SUCCESS:
                fragment1.refreshData();
                fragment2.refreshData();
                fragment3.refreshData();
                break;
            default:
                break;
        }
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        tvTitle.setText(getString(R.string.label_evaluate_center));

        initFragment();
        vpMyEvaluate.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),this,fragments,titles));

        tbMyEvaluate.setupWithViewPager(vpMyEvaluate);

    }

    private void initFragment() {

        fragment1 = EvaluateFragment.newInstance(Common.CommonStr.EVALUATE_TYPE1);
        fragment2 = EvaluateFragment.newInstance(Common.CommonStr.EVALUATE_TYPE2);
        fragment3 = EvaluateFragment.newInstance(Common.CommonStr.EVALUATE_TYPE3);
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);

    }

    @Override
    protected void initData() {
        super.initData();
    }

    @OnClick(R.id.btn_back)
    public void onViewClicked() {
        finish();
    }
}
