package maxandroid.me.lofo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import maxandroid.me.lofo.Lost;
import maxandroid.me.lofo.R;


/**
 * Created by MXZ on 2017/10/5.
 */

public class BaseAdapter extends XRecyclerView.Adapter<BaseAdapter.BaseViewHolder> {
    private Context mContext;
    private List list;

    public BaseAdapter(Context mContext, List list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        Object obj = list.get(position);
        if (obj instanceof Lost) {
            Lost lost = (Lost) obj;
            holder.title.setText(lost.getTitle());
            holder.describe.setText(lost.getDescribe());
            holder.phone.setText(lost.getPhone());
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class BaseViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView describe;
        private TextView phone;

        public BaseViewHolder(final View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            describe = (TextView) itemView.findViewById(R.id.tv_describe);
            phone = (TextView) itemView.findViewById(R.id.tv_phone);
        }
    }
}
