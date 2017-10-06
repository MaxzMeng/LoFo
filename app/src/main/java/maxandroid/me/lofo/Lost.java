package maxandroid.me.lofo;

import cn.bmob.v3.BmobObject;

/**
 * Created by MXZ on 2017/10/5.
 */

public class Lost extends BmobObject {
    private String title;
    private String phone;
    private String describe;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
