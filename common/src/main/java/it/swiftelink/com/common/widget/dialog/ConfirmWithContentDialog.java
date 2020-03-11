package it.swiftelink.com.common.widget.dialog;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;

import it.swiftelink.com.common.R;
import it.swiftelink.com.common.widget.nicedialog.BaseNiceDialog;
import it.swiftelink.com.common.widget.nicedialog.ViewHolder;


/**
 * Created by Administrator on 2018/11/22.
 */

public class ConfirmWithContentDialog extends BaseNiceDialog {
    private String title;
    private String content;
    private ConfirmSelect mConfirmSelect;
    private String cancel;
    private String confirm;
    private boolean isShowCancel;


    public static ConfirmWithContentDialog newInstance(String title, String content,String cancelStr, String confirmStr, boolean isShowCancel) {
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("content", content);
        bundle.putString("cancel", cancelStr);
        bundle.putString("confirm", confirmStr);
        bundle.putBoolean("isShowCancel", isShowCancel);
        ConfirmWithContentDialog dialog = new ConfirmWithContentDialog();
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        title = bundle.getString("title");
        content = bundle.getString("content");
        cancel = bundle.getString("cancel");
        confirm = bundle.getString("confirm");
        isShowCancel = bundle.getBoolean("isShowCancel", true);
    }

    public ConfirmWithContentDialog setConfirmSelect(ConfirmSelect confirmSelect) {
        this.mConfirmSelect = confirmSelect;
        return this;
    }

    @Override
    public int intLayoutId() {
        return R.layout.confirm_with_content_dialog_layout;
    }

    @Override
    public void convertView(ViewHolder holder, final BaseNiceDialog dialog) {

        if (title != null) {
            holder.setText(R.id.title, title);
            holder.setText(R.id.cancel, cancel);
            holder.setText(R.id.content, content);
            holder.setText(R.id.ok, confirm);
            View tvCancel = holder.getView(R.id.cancel);
            if (isShowCancel) {
                tvCancel.setVisibility(View.VISIBLE);
            } else {
                tvCancel.setVisibility(View.GONE);
            }
        }


        holder.setOnClickListener(R.id.cancel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mConfirmSelect != null) {
                    mConfirmSelect.onClickCancel();
                }
                dismiss();
            }
        });

        holder.setOnClickListener(R.id.ok, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mConfirmSelect != null) {
                    mConfirmSelect.onClickQuery();
                }
                dismiss();
            }
        });
    }


    public interface ConfirmSelect {
        void onClickCancel();

        void onClickQuery();
    }


}
