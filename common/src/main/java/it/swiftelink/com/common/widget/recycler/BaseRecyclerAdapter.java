package it.swiftelink.com.common.widget.recycler;

import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.qiujuer.genius.ui.widget.Loading;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import it.swiftelink.com.common.R;
import it.swiftelink.com.common.app.Application;

/**
 * Created by Administrator on 2018/7/10.
 */

public abstract class BaseRecyclerAdapter<Data>
        extends RecyclerView.Adapter<BaseRecyclerAdapter.ViewHolder<Data>>
        implements View.OnClickListener, View.OnLongClickListener, AdapterCallback<Data> {

    private List<Data> mDataList =new ArrayList<>();
    private AdapterListener mListener;

    protected int footLayout = R.layout.layout_foot;

    public int singleSelectedId;

    // 当前加载状态，默认为加载完成
    private static int loadState = 2;
    // 正在加载
    public static final int LOADING = 1;
    // 加载完成
    public static final int LOADING_COMPLETE = 2;
    // 加载到底
    public static final int LOADING_END = 3;
    // 当前Adapter 类型
    private  int adapterType;
    // 普通
    public static final int TYPE_NORMAL = 1;
    // 具有上拉刷新功能
    public static final int TYPE_LOADING = 2;


    public BaseRecyclerAdapter() {
        this(null);
    }

    public BaseRecyclerAdapter( AdapterListener listener) {
        mListener = listener;
        adapterType = TYPE_NORMAL;
    }


    /**
     * @param position
     * @return 返回对应的xml文件的id
     */
    @Override
    public int getItemViewType(int position) {
        return getViewType(position);
    }

    @LayoutRes
    public abstract int getViewType(int pos);

    @Override
    public ViewHolder<Data> onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View rootView = inflater.inflate(viewType, parent, false);
        ViewHolder<Data> viewHolder = createViewHolder(rootView, viewType);

        rootView.setOnClickListener(this);
        rootView.setOnLongClickListener(this);
        //将view和holder进行绑定
        rootView.setTag(R.id.tag_recycler_holder, viewHolder);

        viewHolder.unbinder = ButterKnife.bind(viewHolder, rootView);
        //绑定callBack
        viewHolder.mCallback = this;

        return viewHolder;
    }

    /**
     * 子类必须实现的创建viewHolder的方法
     */
    protected abstract ViewHolder<Data> createViewHolder(View root, int viewType);

    /**
     * 绑定数据
     */
    @Override
    public void onBindViewHolder(ViewHolder<Data> holder, int position) {
        //绑定数据
        holder.bind(position, mDataList,adapterType);
    }

    @Override
    public int getItemCount() {
        if (adapterType == TYPE_LOADING) {
            return mDataList.size() + 1;
        } else {
            return mDataList.size();
        }

    }

    /**
     * 返回整个集合
     */
    public List<Data> getItems() {
        return mDataList;
    }

    /**
     * 返回整个集合
     */
    public Data getItem(int pos) {
        if(mDataList.size()>0&&mDataList.size()>pos){
            return mDataList.get(pos);
        }else{
            return null;
        }

    }

    /**
     * 插入一条数据
     */
    public void addData(Data data) {
        mDataList.add(data);
        notifyItemInserted(mDataList.size() - 1);

    }

    /**
     * 插入一堆数据
     */
    public void addData(Data... dataList) {

        if (dataList != null && dataList.length > 0) {
            Collections.addAll(mDataList, dataList);
            notifyItemRangeInserted(mDataList.size(), dataList.length);
        }
    }

    /**
     * 插入一个数据集合
     */
    public void addData(List<Data> dataList) {
        if (dataList != null && dataList.size() > 0) {
            mDataList.addAll(dataList);
            notifyItemRangeInserted(mDataList.size(), dataList.size());
        }
    }

    /**
     * 数据清空
     */
    public void clear() {
        mDataList.clear();
        notifyDataSetChanged();
    }

    /**
     * 数据替换
     */
    public void replice(List<Data> dataList) {
        mDataList.clear();
        if (dataList != null && dataList.size() > 0) {
            mDataList.addAll(dataList);
            notifyDataSetChanged();
        }
    }


    /**
     * 点击
     */
    @Override
    public void onClick(View v) {

        ViewHolder<Data> viewHolder = (ViewHolder<Data>) v.getTag();

        if (mListener != null) {
            int position = viewHolder.getAdapterPosition();
            if(position<0){
                return;
            }
            mListener.onItemClick(viewHolder, mDataList.get(position));
        }
    }

    /**
     * 长按
     */
    @Override
    public boolean onLongClick(View v) {
        ViewHolder<Data> viewHolder = (ViewHolder<Data>) v.getTag();

        if (mListener != null) {
            int position = viewHolder.getAdapterPosition();
            mListener.onItemLongClick(viewHolder, mDataList.get(position));
            return true;
        }
        return false;
    }

    public void singleChoose(int position) {
        singleSelectedId = position;
        notifyDataSetChanged();
    }

    /**
     * 设置监听器
     */
    public void setItemListener(AdapterListener<Data> listener) {
        this.mListener = listener;
    }

    /**
     * 设置Adapter 具有上拉加载的功能
     */
    public void setLoadType() {
        adapterType = TYPE_LOADING;
    }

    /**
     * 设置上拉加载状态
     *
     * @param loadState 0.正在加载 1.加载完成 2.加载到底
     */
    public void setLoadState(int loadState) {
        this.loadState = loadState;
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    // 如果当前是footer的位置，那么该item占据2个单元格，正常情况下占据1个单元格
                    return getItemViewType(position) == footLayout ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    /**
     * item的监听器
     *
     * @param <Data> 泛型
     */
    public interface AdapterListener<Data> {
        void onItemClick(ViewHolder<Data> holder, Data data);

        void onItemLongClick(ViewHolder<Data> holder, Data data);
    }

    private static final String TAG = "BaseRecyclerAdapter";
    /**
     * 自定义的viewHolder
     *
     * @param <Data>
     */
    public static abstract class ViewHolder<Data> extends RecyclerView.ViewHolder {
        private Unbinder unbinder;
        protected Data mData;
        protected int mPosition;
        private AdapterCallback<Data> mCallback;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        //用于绑定数据的触发
        void bind(int position, List<Data> dataList ,int adapterType) {
            this.mPosition = position;
            if (adapterType == TYPE_LOADING) {
                    if (position < dataList.size()) {
                        this.mData = dataList.get(position);
                        onBind(mData);
                    } else {
                        onBind((Data) new Object());
                    }

            } else {
                this.mData = dataList.get(position);
                onBind(mData);
            }

        }

        protected abstract void onBind(Data data);

        //holder对自己对应的data进行更新
        public void update(Data data) {
            if (this.mCallback != null) {
                mCallback.onUpdate(data, this);
            }
        }
    }

    protected class FootViewHolder extends ViewHolder {
        private Loading loadingFoot;
        private TextView tvLoad;


        public FootViewHolder(View itemView) {
            super(itemView);

            loadingFoot = itemView.findViewById(R.id.loading_foot);
            tvLoad = itemView.findViewById(R.id.tv_load);
        }

        @Override
        protected void onBind(Object o) {
            switch (loadState) {
                case LOADING: // 正在加载
                    loadingFoot.setVisibility(View.VISIBLE);
                    tvLoad.setVisibility(View.VISIBLE);
                    tvLoad.setText(Application.getInstance().getString(R.string.data_loadding));
                    break;

                case LOADING_COMPLETE: // 加载完成
                    loadingFoot.setVisibility(View.GONE);
                    tvLoad.setVisibility(View.GONE);
                    break;

                case LOADING_END: // 加载到底
                    loadingFoot.setVisibility(View.GONE);
                    tvLoad.setVisibility(View.VISIBLE);
                    tvLoad.setText(Application.getInstance().getString(R.string.label_has_not_data));
                    break;

                default:
                    break;
            }
        }
    }
}
