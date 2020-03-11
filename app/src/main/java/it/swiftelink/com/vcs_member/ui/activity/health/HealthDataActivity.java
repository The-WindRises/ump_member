package it.swiftelink.com.vcs_member.ui.activity.health;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.BaseActivity;
import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.vcs_member.R;

public class HealthDataActivity extends BaseActivity<BaseContract.Presenter> {


    @BindView(R.id.tv_title)
    TextView tvTitle;

    public static void show(Activity activity) {
        activity.startActivity(new Intent(activity, HealthDataActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_health_data;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        tvTitle.setText(getText(R.string.label_health_data));
    }

    @OnClick({R.id.btn_back, R.id.ll_health2, R.id.ll_health3, R.id.ll_health4, R.id.ll_health1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.ll_health1:
                VitalSignsActivity.show(this);
                break;
            case R.id.ll_health2:
                EditHealthExaminationActivity.show(this);
                break;
            case R.id.ll_health3:
                EditCheckoutActivity.show(this);
                break;
            case R.id.ll_health4:
                EditImageDataActivity.show(this);
                break;
        }
    }
}
