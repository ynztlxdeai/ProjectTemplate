package com.luoxiang.weibo.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.lang.reflect.Field;

/**
 * projectName: 	    Silucae
 * packageName:	        com.jinzhi.silucae.util
 * className:	        InputMethodUtil
 * author:	            Luoxiang
 * time:	            2016/10/12	8:58
 * desc:	            用于防止内存泄漏 关于InputMethodManager
 */
    /*
                     _ooo0ooo_
                    o888888888o
                     88" . "88
                     (| -_- |)
                      0\ = /0
                   ___/`---'\___
                    .`\\| |//'.
                  /\\||| : |||//\
                / _|||| -:- ||||- \
                  | |\\\ - ///| |
               | \_| ''\---/'' |_/ |
                \ .-\__ '-' ___/-. /
             ___'. .' /--.--\ `. .'___
          ."" '< `.___\_<|>_/___.' >' "".
         | | : `- \`.;`\ _ /`;.`/ - ` : | |
           \ \ `_. \_ __\ /__ _/ .-` / /
    =====`-.____`.___ \_____/___.-`___.-'=====
                      `=---='
                 佛祖保佑 永无BUG
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    */
public class InputMethodUtil {
    public static void fixInputMethodManagerLeak(Context destContext) {
        try {
            if (destContext == null) {
                return;
            }

            InputMethodManager imm = (InputMethodManager) destContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm == null) {
                return;
            }

            String [] arr     = new String[]{"mCurRootView", "mServedView", "mNextServedView"};
            Field     f       = null;
            Object    obj_get = null;
            for (int i = 0;i < arr.length;i ++) {
                String param = arr[i];
                try{
                    f = imm.getClass().getDeclaredField(param);
                    if (f.isAccessible() == false) {
                        f.setAccessible(true);
                    } // author: sodino mail:sodino@qq.com
                    obj_get = f.get(imm);
                    if (obj_get != null && obj_get instanceof View) {
                        View v_get = (View) obj_get;
                        if (v_get.getContext() == destContext) { // 被InputMethodManager持有引用的context是想要目标销毁的
                            f.set(imm, null); // 置空，破坏掉path to gc节点
                        } else {
                            // 不是想要目标销毁的，即为又进了另一层界面了，不要处理，避免影响原逻辑,也就不用继续for循环了
                                Log.d( "InputMethodUtil", "fixInputMethodManagerLeak break, context is not suitable, get_context=" + v_get.getContext()+" dest_context=" + destContext);
                            break;
                        }
                    }
                }catch(Throwable t){
                    t.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
