package it.swiftelink.com.vcs_member.ui.activity.order;

import android.app.Activity;
import android.content.Intent;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.BaseActivity;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.ui.fragment.MyPagerAdapter;
import it.swiftelink.com.vcs_member.ui.fragment.order.SetMealOrderListFragment;
import it.swiftelink.com.vcs_member.ui.fragment.recipe.RecipeOrderListFragment;

public class OrderListActivity extends BaseActivity<BaseContract.Presenter> {


    @BindView(R.id.tv_tv_order_top1)
    TextView tvTvOrderTop1;
    @BindView(R.id.tv_tv_order_top2)
    TextView tvTvOrderTop2;
    @BindView(R.id.tb_order_list1)
    TabLayout tbOrderList1;
    @BindView(R.id.tb_order_list2)
    TabLayout tbOrderList2;
    @BindView(R.id.vp_order_list1)
    ViewPager vpOrderList1;
    @BindView(R.id.ll_order_list1)
    LinearLayout llOrderList1;
    @BindView(R.id.vp_order_list2)
    ViewPager vpOrderList2;
    @BindView(R.id.ll_order_list2)
    LinearLayout llOrderList2;

    private final int selectType1 = 0;
    private final int selectType2 = 1;

    private Integer[] type1Titles = {R.string.tab_order_type1_to_pay, R.string.tab_order_type1_finish,
            R.string.tab_order_type1_all};
    private Integer[] type2Titles = {R.string.tab_order_type2_to_pay, R.string.tab_order_type2_to_send,
            R.string.tab_order_type2_to_received, R.string.tab_order_type2_finish, R.string.tab_order_type2_all};


    private List<Fragment> type1Frags = new ArrayList<>();

    private List<Fragment> type2Frags = new ArrayList<>();


    public static void show(Activity activity, String type) {

        Intent intent = new Intent(activity, OrderListActivity.class);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_list;
    }


    @Override
    protected void initWidget() {
        super.initWidget();

        initFragments();

        tbOrderList1.addTab(tbOrderList1.newTab().setText(type1Titles[0]), 0);
        tbOrderList1.addTab(tbOrderList1.newTab().setText(type1Titles[1]), 1);
        tbOrderList1.addTab(tbOrderList1.newTab().setText(type1Titles[2]), 2);


        tbOrderList2.addTab(tbOrderList2.newTab().setText(type2Titles[0]), 0);
        tbOrderList2.addTab(tbOrderList2.newTab().setText(type2Titles[1]), 1);
        tbOrderList2.addTab(tbOrderList2.newTab().setText(type2Titles[2]), 2);
        tbOrderList2.addTab(tbOrderList2.newTab().setText(type2Titles[3]), 3);
        tbOrderList2.addTab(tbOrderList2.newTab().setText(type2Titles[4]), 4);


        MyPagerAdapter pagerAdapter1 = new MyPagerAdapter(getSupportFragmentManager(), this, type1Frags, type1Titles);
        vpOrderList1.setAdapter(pagerAdapter1);
        tbOrderList1.setupWithViewPager(vpOrderList1);

        MyPagerAdapter pagerAdapter2 = new MyPagerAdapter(getSupportFragmentManager(), this, type2Frags, type2Titles);
        vpOrderList2.setAdapter(pagerAdapter2);
        tbOrderList2.setupWithViewPager(vpOrderList2);

        selected(selectType1);


    }

    @Override
    protected void initData() {
        super.initData();

        Intent intent = getIntent();

        if (intent != null) {
            String type = intent.getStringExtra("type");

            if (Common.CommonStr.SET_MEAL_TYPE1.equals(type)) {
                vpOrderList1.setCurrentItem(0);
            }

            if (Common.CommonStr.SET_MEAL_TYPE2.equals(type)) {
                vpOrderList1.setCurrentItem(1);
            }

            if (Common.CommonStr.SET_MEAL_TYPE3.equals(type)) {
                vpOrderList1.setCurrentItem(2);
            }
        }
    }

    private void initFragments() {

        type1Frags.add(SetMealOrderListFragment.newInstance(Common.CommonStr.SET_MEAL_TYPE1));
        type1Frags.add(SetMealOrderListFragment.newInstance(Common.CommonStr.SET_MEAL_TYPE2));
        type1Frags.add(SetMealOrderListFragment.newInstance(Common.CommonStr.SET_MEAL_TYPE3));

        type2Frags.add(RecipeOrderListFragment.newInstance(Common.CommonStr.RECIPE_ORDER_TYPE1));
        type2Frags.add(RecipeOrderListFragment.newInstance(Common.CommonStr.RECIPE_ORDER_TYPE2));
        type2Frags.add(RecipeOrderListFragment.newInstance(Common.CommonStr.RECIPE_ORDER_TYPE3));
        type2Frags.add(RecipeOrderListFragment.newInstance(Common.CommonStr.RECIPE_ORDER_TYPE4));
        type2Frags.add(RecipeOrderListFragment.newInstance(Common.CommonStr.RECIPE_ORDER_TYPE5));


    }


    @OnClick({R.id.btn_back, R.id.tv_tv_order_top1, R.id.tv_tv_order_top2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_tv_order_top1:
                selected(selectType1);
                break;
            case R.id.tv_tv_order_top2:
                selected(selectType2);
                break;
        }
    }

    private void selected(int selectType) {
        tvTvOrderTop1.setSelected(false);
        tvTvOrderTop2.setSelected(false);

        switch (selectType) {
            case selectType1:
                tvTvOrderTop1.setSelected(true);
                switchOrderType1();
                break;
            case selectType2:
                tvTvOrderTop2.setSelected(true);
                switchOrderType2();
                break;
        }
    }

    private void switchOrderType1() {
        llOrderList2.setVisibility(View.GONE);
        llOrderList1.setVisibility(View.VISIBLE);


    }

    private void switchOrderType2() {
        llOrderList1.setVisibility(View.GONE);
        llOrderList2.setVisibility(View.VISIBLE);


    }


}
