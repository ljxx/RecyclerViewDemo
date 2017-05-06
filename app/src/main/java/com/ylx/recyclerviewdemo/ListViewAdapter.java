package com.ylx.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * ========================================
 * <p/>
 * 版 权：蓝吉星讯 版权所有 （C） 2017
 * <p/>
 * 作 者：yanglixiang
 * <p/>
 * 版 本：1.0
 * <p/>
 * 创建日期：2017/5/5  下午4:53
 * <p/>
 * 描 述：
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.MyViewHolder> {

    public static final int TYPE_HEADER = 0; //说明是带有Header的
    public static final int TYPE_FOOTER = 1; //说明是带有Footer的
    public static final int TYPE_NORMAL = 2; //说明是不带有Header和Footer的

    private View mHeaderView;
    private View mFooterView;

    public void setHeaderView(View headerView){
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public View getHeaderView(){
        return mHeaderView;
    }

    public void setFooterView(View footerView){
        mFooterView = footerView;
        notifyItemInserted(getItemCount() - 1);
    }

    //返回View中Item的个数，这个时候，总的个数应该是ListView中Item的个数加上HeaderView和FooterView

    private List<String> mListData;
    private Context mContext;

    public ListViewAdapter(Context mContext,List<String> mListData){
        this.mContext = mContext;
        this.mListData = mListData;
    }

    //创建View，如果是HeaderView或者是FooterView，直接在Holder中返回
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mHeaderView != null && viewType == TYPE_HEADER){
            return new MyViewHolder(mHeaderView);
        }
        if(mFooterView != null && viewType == TYPE_FOOTER){
            return new MyViewHolder(mFooterView);
        }
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_view_item,parent,false));
        return myViewHolder;
    }

    //绑定View，这里是根据返回的这个position的类型，从而进行绑定的，   HeaderView和FooterView, 就不同绑定了
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        if(getItemViewType(position) == TYPE_NORMAL){
            if(holder instanceof MyViewHolder){
                holder.mTV.setText(mListData.get(position -1));
                //这里加载数据的时候要注意，是从position-1开始，因为position==0已经被header占用了
                if(mOnItemClickListener != null){
                    /**
                     * 点击事件
                     */
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int pos = holder.getLayoutPosition();
                            mOnItemClickListener.onItemClick(holder.itemView,position - 1);
                        }
                    });

                    /**
                     * 长按事件
                     */
                    holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            int pos = holder.getLayoutPosition();
                            mOnItemClickListener.onItemLongClick(holder.itemView,position - 1);
                            return false;
                        }
                    });
                }
                return;
            }
            return;
        } else if(getItemViewType(position) == TYPE_HEADER){
            return;
        } else {
            return;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(mHeaderView == null && mFooterView == null){
            return TYPE_NORMAL;
        }
        if(position == 0){
            //第一个item应该加载Header
            return TYPE_HEADER;
        }
        if(position == getItemCount() - 1){
            //最后一个，应该加载Footer
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }

    //返回View中Item的个数，这个时候，总的个数应该是ListView中Item的个数加上HeaderView和FooterView
    @Override
    public int getItemCount() {
        if(mHeaderView == null && mFooterView == null){
            return mListData.size();
        } else if(mHeaderView == null && mFooterView != null){
            return mListData.size() + 1;
        } else if(mHeaderView != null && mFooterView == null){
            return mListData.size() + 1;
        } else {
            return mListData.size() + 2;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mTV;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTV = (TextView) itemView.findViewById(R.id.mContent);

            //如果是headerview或者是footerview,直接返回
            if(itemView == mHeaderView){
                return;
            }

            if(itemView == mFooterView){
                return;
            }

        }
    }

    /**
     * 监听回调事件
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int positin);
        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }
}
