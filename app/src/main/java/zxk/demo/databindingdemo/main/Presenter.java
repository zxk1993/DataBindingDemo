package zxk.demo.databindingdemo.main;

import android.content.Context;
import android.content.Intent;

/**
 * Created by zxk on 2018/8/2.
 * Description:
 */
public class Presenter {

    public void clickItem(Context context,ItemType itemType){
        Intent intent = new Intent(itemType.getAction());
        if(intent.resolveActivity(context.getPackageManager())!=null){
            context.startActivity(intent);
        }
    }
}
