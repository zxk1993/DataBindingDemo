package zxk.demo.databindingdemo.main;

/**
 * Created by zxk on 2018/8/2.
 * Description:
 */
public class ItemType {
    private String name;
    private String action;

    public ItemType(String name, String action) {
        this.name = name;
        this.action = action;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAction() {
        return action == null ? "" : action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
