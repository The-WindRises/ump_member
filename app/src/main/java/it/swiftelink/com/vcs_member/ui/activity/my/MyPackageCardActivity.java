package it.swiftelink.com.vcs_member.ui.activity.my;

import android.app.Activity;
import android.content.Intent;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
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
import it.swiftelink.com.vcs_member.ui.fragment.card.MyCardListFragment;

public class MyPackageCardActivity extends BaseActivity<BaseContract.Presenter> {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tb_card_status)
    TabLayout tbMyCardStatus;
    @BindView(R.id.vp_card)
    ViewPager vpCard;
    private List<Fragment> fragmentList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_card;
    }

    public static void show(Activity activity) {
        activity.startActivity(new Intent(activity, MyPackageCardActivity.class));
    }

    private Integer[] list_Title ={ R.string.tab_all_card,R.string.tab_useing_card,R.string.tab_used_card};

    @Override
    protected void initWidget() {
        super.initWidget();
        tvTitle.setText(getResources().getString(R.string.my_card_title_str));
        fragmentList = new ArrayList<>();
        fragmentList = new ArrayList<>();
        fragmentList.add(MyCardListFragment.newInstance(Common.CommonStr.ALL_MINE_CARD_TYPE));
        fragmentList.add(MyCardListFragment.newInstance(Common.CommonStr.USE_MINE_CARD_TYPE));
        fragmentList.add(MyCardListFragment.newInstance(Common.CommonStr.LAPSE_MINE_CARD_TYPE));

        vpCard.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), MyPackageCardActivity.this, fragmentList, list_Title));
        tbMyCardStatus.setupWithViewPager(vpCard);

    }


    @OnClick(R.id.btn_back)
    public void onViewClicked() {
        finish();
    }
}
