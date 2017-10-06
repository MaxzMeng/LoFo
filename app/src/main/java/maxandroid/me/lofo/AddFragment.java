package maxandroid.me.lofo;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by MXZ on 2017/10/6.
 */

public class AddFragment extends Fragment {
    private TextView mEtTitle;
    private TextView mEtDescribe;
    private TextView mEtPhone;
    private Button mBtnAdd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mEtTitle = view.findViewById(R.id.edit_title);
        mEtDescribe = view.findViewById(R.id.edit_describe);
        mEtPhone = view.findViewById(R.id.edit_phone);
        mBtnAdd = view.findViewById(R.id.btn_add);
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = mEtTitle.getText().toString();
                String describe = mEtDescribe.getText().toString();
                String phone = mEtPhone.getText().toString();
                if (title.equals("") || describe.equals("") || phone.equals("")) {
                    Toast.makeText(getActivity(), "是不是有没有填写的项呢？", Toast.LENGTH_SHORT).show();
                } else {
                    Lost lost = new Lost();
                    lost.setTitle(title);
                    lost.setDescribe(describe);
                    lost.setPhone(phone);
                    lost.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e == null) {
                                Toast.makeText(getActivity(), "添加成功", Toast.LENGTH_SHORT).show();
                                getFragmentManager().beginTransaction().replace(R.id.fl_container, new LostFragment()).commitAllowingStateLoss();
//                                getFragmentManager().popBackStack();
                            } else {
                                Toast.makeText(getActivity(), "出现了未知错误，请稍后重试", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
