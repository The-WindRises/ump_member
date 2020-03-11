package it.swiftelink.com.vcs_member.ui.activity.user;

import androidx.appcompat.widget.AppCompatTextView;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.BaseActivity;
import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.vcs_member.R;

public class AgreementActivity extends BaseActivity<BaseContract.Presenter> {


    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.agreementTv)
    AppCompatTextView agreementTv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_agreement;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");
        tvTitle.setText(title);
        agreementTv.setText(Html.fromHtml(content));
    }

    @OnClick(R.id.btn_back)
    public void onViewClicked() {
        out();
    }
}
