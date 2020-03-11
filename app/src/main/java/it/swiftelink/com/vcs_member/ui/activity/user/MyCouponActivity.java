package it.swiftelink.com.vcs_member.ui.activity.user;

import android.app.Activity;
import android.content.Intent;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.text.TextUtils;
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
import it.swiftelink.com.vcs_member.ui.fragment.coupon.CouponFragment;

public class MyCouponActivity extends BaseActivity<BaseContract.Presenter> {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tb_my_coupon)
    TabLayout tbMyCoupon;
    @BindView(R.id.vp_coupon)
    ViewPager vpCoupon;


    private List<Fragment> fragmentList;
    private Integer[] list_Title ={R.string.tab_my_coupon1,R.string.tab_my_coupon2,R.string.tab_my_coupon3};
    private String orderAmount;

    public static void show(Activity activity ,String orderAmount ) {
        Intent intent = new Intent(activity, MyCouponActivity.class);

       if(!TextUtils.isEmpty(orderAmount)){
           intent.putExtra("orderAmount",orderAmount);
           activity.startActivityForResult(intent,Common.RequstCode.SELECT_COUPON);
       }else{
           activity.startActivity(intent);
       }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_coupon;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        tvTitle.setText(getString(R.string.label_coupon));

        Intent intent = getIntent();

        if(intent!=null){
            orderAmount = intent.getStringExtra("orderAmount");
        }

        fragmentList = new ArrayList<>();
        fragmentList.add(CouponFragment.newInstance(Common.CommonStr.COUPON_TYPE1,orderAmount));
        fragmentList.add(CouponFragment.newInstance(Common.CommonStr.COUPON_TYPE2,""));
        fragmentList.add(CouponFragment.newInstance(Common.CommonStr.COUPON_TYPE3,""));

        vpCoupon.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), MyCouponActivity.this, fragmentList, list_Title));
        tbMyCoupon.setupWithViewPager(vpCoupon);


    }

    @OnClick(R.id.btn_back)
    public void onViewClicked() {
        finish();
    }
}
