package it.swiftelink.com.vcs_member.weight.exrecyclerview;

import android.view.ViewGroup;

/**
 * Created by liyu20 on 2018/1/8.
 */

public interface ExtendedHolderFactory {
    ExtendedHolder getHolder(ExtendedRecyclerViewHelper helper, ViewGroup parent, int viewType);
}
