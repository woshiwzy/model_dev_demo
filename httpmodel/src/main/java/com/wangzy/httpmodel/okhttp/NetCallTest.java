package com.wangzy.httpmodel.okhttp;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wangzy on 2018/3/28.
 */


public class NetCallTest {




    public static void login(String userName,String password,final MyNetCallBackExtend myNetCallBackExtend) {


        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("email", userName);
            jsonObject.put("password", password);
            String path="login";

            HttpRequester.postJson("http://65.49.201.127:7000/list_servers",jsonObject.toString(),myNetCallBackExtend);

//            HttpRequester.postJson("test",jsonObject.toString(),new MyNetCallBackExtend<Server>(Server.class,isArray){
//
//
//                @Override
//                public void onResponseReslt(Result<Server> result) {
//
//                }
//
//                @Override
//                public void onResponseResultList(Result<List<Server>> result) {
//
//                }
//            });


        } catch (JSONException e) {
            e.printStackTrace();
        }





    }
}
