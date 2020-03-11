package it.swiftelink.com.vcs_member.ui.activity.my;

import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.BaseActivity;
import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.model.message.MessageListResModel;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.utils.DateTimeUtils;

public class MessageDetailActivity extends BaseActivity<BaseContract.Presenter> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_msg_content)
    TextView tvMsgContent;
    @BindView(R.id.tv_msg_date)
    TextView tvMsgDate;

    public static void show(Activity activity, MessageListResModel.DataBean.DataListBean dataBean) {

        Intent intent = new Intent(activity, MessageDetailActivity.class);
        intent.putExtra("msgContent", dataBean);

        activity.startActivity(intent);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message_detail;
    }

    @Override
    protected void initData() {
        super.initData();

        Intent intent = getIntent();
        if (intent != null) {
            MessageListResModel.DataBean.DataListBean msgContent = (MessageListResModel.DataBean.DataListBean) intent.getSerializableExtra("msgContent");

            if (msgContent != null) {
                tvTitle.setText(msgContent.getTitle());
                String content = msgContent.getContent();
                String s = content.replace("\\n", "\n");
                tvMsgContent.setText(s);
                tvMsgDate.setText(DateTimeUtils.getDateToString(msgContent.getCreationDate() + ""));
            }
        }
    }

    @OnClick(R.id.btn_back)
    public void onViewClicked() {
        finish();
    }

}
