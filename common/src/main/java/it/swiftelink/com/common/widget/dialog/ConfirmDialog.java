package it.swiftelink.com.common.widget.dialog;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import it.swiftelink.com.common.R;
import it.swiftelink.com.common.widget.nicedialog.BaseNiceDialog;
import it.swiftelink.com.common.widget.nicedialog.ViewHolder;


/**
 * Created by Administrator on 2018/11/22.
 */

public class ConfirmDialog extends BaseNiceDialog {
    private String title;
    private ConfirmSelect mConfirmSelect;
    private String cancel;
    private String confirm;
    private boolean isShowCancel;
    private boolean isChangeColor;


    public static ConfirmDialog newInstance(String title, String cancelStr, String confirmStr, boolean isShowCancel) {
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("cancel", cancelStr);
        bundle.putString("confirm", confirmStr);
        bundle.putBoolean("isShowCancel", isShowCancel);
        ConfirmDialog dialog = new ConfirmDialog();
        dialog.setArguments(bundle);
        return dialog;
    }

    public static ConfirmDialog newInstanceWithColor(String title, String cancelStr, String confirmStr, boolean isShowCancel) {
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("cancel", cancelStr);
        bundle.putString("confirm", confirmStr);
        bundle.putBoolean("isShowCancel", isShowCancel);
        bundle.putBoolean("isChangeColor", true);
        ConfirmDialog dialog = new ConfirmDialog();
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
        cancel = bundle.getString("cancel");
        confirm = bundle.getString("confirm");
        isShowCancel = bundle.getBoolean("isShowCancel", true);
        isChangeColor = bundle.getBoolean("isChangeColor", false);
    }

    public ConfirmDialog setConfirmSelect(ConfirmSelect confirmSelect) {
        this.mConfirmSelect = confirmSelect;
        return this;
    }

    @Override
    public int intLayoutId() {
        return R.layout.confirm_dialog_layout;
    }

    @Override
    public void convertView(ViewHolder holder, final BaseNiceDialog dialog) {

        if (title != null) {
            holder.setText(R.id.title, title);
            holder.setText(R.id.cancel, cancel);
            holder.setText(R.id.ok, confirm);
            TextView tvCancel = holder.getView(R.id.cancel);
            TextView tvOk = holder.getView(R.id.ok);
            if (isShowCancel) {
                tvCancel.setVisibility(View.VISIBLE);
            } else {
                tvCancel.setVisibility(View.GONE);
            }
            if(isChangeColor){
                tvCancel.setTextColor(getResources().getColor(R.color.blue));
                tvOk.setTextColor(getResources().getColor(R.color.blue));
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
