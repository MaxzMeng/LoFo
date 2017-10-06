package maxandroid.me.lofo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.melnykov.fab.FloatingActionButton;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import maxandroid.me.lofo.Adapter.BaseAdapter;

/**
 * Created by MXZ on 2017/10/5.
 */

public class LostFragment extends Fragment {
    private List<Lost> losts;
    private XRecyclerView xRecyclerView;
    private FloatingActionButton floatingActionButton;
    private BaseAdapter baseAdapter;
    private AddFragment addFragment;
    private int current = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_lost, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        xRecyclerView = view.findViewById(R.id.xrv_lost);
        floatingActionButton = view.findViewById(R.id.fab);
        floatingActionButton.attachToRecyclerView(xRecyclerView);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addFragment == null) {
                    addFragment = new AddFragment();
                }
                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fl_container, addFragment).commitAllowingStateLoss();
            }
        });
        queryTasks();

        xRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
//                refreshTasks();
                queryTasks();
//                Toast.makeText(getActivity(), "刷新成功", Toast.LENGTH_SHORT).show();
                xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                addTasks();
//                Toast.makeText(getActivity(), "success", Toast.LENGTH_SHORT).show();
                xRecyclerView.loadMoreComplete();
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    private void refreshTasks() {
        BmobQuery<Lost> query = new BmobQuery<Lost>();
        query.setLimit(10);
//        current += 10;
        query.order("-createdAt");// 按照时间降序
        query.findObjects(new FindListener<Lost>() {
            @Override
            public void done(List<Lost> list, BmobException e) {
                if (e == null) {
                    if (list.get(0).equals(losts.get(0))) {
                        Toast.makeText(getActivity(), "已经是最新的数据了", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

    private void queryTasks() {
        current = 0;
        BmobQuery<Lost> query = new BmobQuery<Lost>();
        query.setLimit(10);
        current += 10;
        query.order("-createdAt");// 按照时间降序
        query.findObjects(new FindListener<Lost>() {
            @Override
            public void done(List<Lost> list, BmobException e) {
                if (e == null) {
                    losts = list;
                    baseAdapter = new BaseAdapter(getActivity(), losts);
                    xRecyclerView.setAdapter(baseAdapter);
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

    private void addTasks() {
        BmobQuery<Lost> query = new BmobQuery<Lost>();
        query.setSkip(current);
        query.setLimit(10);
        current += 10;
        query.order("-createdAt");// 按照时间降序
        query.findObjects(new FindListener<Lost>() {
            @Override
            public void done(List<Lost> list, BmobException e) {
                if (e == null) {
                    for (int i = 0; i < list.size(); i++) {
                        losts.add(list.get(i));
                    }
//                    xRecyclerView.setAdapter(new BaseAdapter(getActivity(), losts));
                    baseAdapter.notifyDataSetChanged();
                } else {
                    e.printStackTrace();
                }
            }
        });
    }
}
