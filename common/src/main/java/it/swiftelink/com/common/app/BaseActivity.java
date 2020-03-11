package it.swiftelink.com.common.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.common.flyn.AndroidBug5497Workaround;
import it.swiftelink.com.common.flyn.Eyes;
import it.swiftelink.com.common.utils.LocalManageUtil;

/**
 * Created by Administrator on 2018/7/10.
 */

public abstract class BaseActivity<P extends BaseContract.Presenter> extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!this.isTaskRoot()) {
            Intent mainIntent = getIntent();
            String action = mainIntent.getAction();
            if (mainIntent.hasCategory(Intent.CATEGORY_LAUNCHER) && action.equals(Intent.ACTION_MAIN)) {
                finish();
                return;
            }
        }
        initWindows();
        if (initArgs(getIntent().getExtras())) {
            setContentView(getLayoutId());
            initWidget();
            initData();
        } else {
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    public void out() {
        finish();
    }


    protected void initWindows() {

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocalManageUtil.setLocal(newBase));
    }

    /**
     * 初始化相关参数
     *
     * @return
     */
    protected Boolean initArgs(Bundle bundle) {

        return true;
    }

    /**
     * 获取xml 的id
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化控件
     */
    protected void initWidget() {

        Eyes.translucentStatusBar(this, false);
        AndroidBug5497Workaround.assistActivity(this);
        ButterKnife.bind(this);
    }

    /**
     * 初始化数据
     */
    protected void initData() {
    }

    /**
     * 当点击导航栏上面的返回键
     *
     * @return
     */
    @Override
    public boolean onSupportNavigateUp() {

        out();
        return super.onSupportNavigateUp();
    }

    /**
     * 当返回键被点击
     */
    @Override
    public void onBackPressed() {
        @SuppressLint("RestrictedApi") List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null && fragments.size() > 0) {
            for (Fragment ft : fragments) {
                if (ft instanceof BaseFragment) {
                    if (((BaseFragment) ft).onBackPressd()) {
                        return;
                    }
                }
            }
        }
        super.onBackPressed();
        out();

    }


}
