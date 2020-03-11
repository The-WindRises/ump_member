package it.swiftelink.com.vcs_member.ui.fragment.main;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.BaseFragment;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.ui.activity.WebViewActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class HospitalFragment extends BaseFragment {


    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    String selectLanguage;

    public HospitalFragment() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hospital;
    }

    @Override
    protected void initWidget(View view) {
        super.initWidget(view);
        tvTitle.setText(getString(R.string.clinic));
        btnBack.setVisibility(View.GONE);
        //获取当前系统语言
       selectLanguage =  App.getSPUtils().getString(Common.SPApi.SELECT_LANGUAGE);
    }


    @OnClick({R.id.tv_enter_hk, R.id.tv_enter_ch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_enter_hk:
                if (selectLanguage.equals(Common.CommonStr.LANGUAGE1)) {
                    WebViewActivity.show(getActivity(),"https://www2.ump.com.hk/locations.php?id=30&lang_id=3",getString(R.string.clinic_hk));
                } else if (selectLanguage.equals(Common.CommonStr.LANGUAGE2)) {
                    WebViewActivity.show(getActivity(),"https://www2.ump.com.hk/locations.php?id=30&lang_id=1",getString(R.string.clinic_hk));
                } else if (selectLanguage.equals(Common.CommonStr.LANGUAGE3)) {
                    WebViewActivity.show(getActivity(),"https://www2.ump.com.hk/locations.php?id=30&lang_id=2",getString(R.string.clinic_hk));
                }
                break;
            case R.id.tv_enter_ch:
                if (selectLanguage.equals(Common.CommonStr.LANGUAGE1)) {
                    WebViewActivity.show(getActivity(),"https://www2.ump.com.hk/locations.php?id=31&lang_id=3",getString(R.string.label_mainland_china));
                } else if (selectLanguage.equals(Common.CommonStr.LANGUAGE2)) {
                    WebViewActivity.show(getActivity(),"https://www2.ump.com.hk/locations.php?id=31&lang_id=1",getString(R.string.label_mainland_china));
                } else if (selectLanguage.equals(Common.CommonStr.LANGUAGE3)) {
                    WebViewActivity.show(getActivity(),"https://www2.ump.com.hk/locations.php?id=31&lang_id=2",getString(R.string.label_mainland_china));
                }
                break;
        }
    }
}
