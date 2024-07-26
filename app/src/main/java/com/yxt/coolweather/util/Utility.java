package com.yxt.coolweather.util;

import android.text.TextUtils;

import com.yxt.coolweather.db.City;
import com.yxt.coolweather.db.County;
import com.yxt.coolweather.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
    /**
     *
     * 解析和处理服务器返回的省级数据
     */

    public static boolean handleProvinceResponse(String response){
        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i=0; i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(jsonObject.getString("name"));
                    province.setProvinceCode(jsonObject.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return  false;
    }


    /**
     * 解析和处理服务器返回的市级数据
     *
     */
    public static boolean handleCityResponse(String reponse,int ProvinceId){
        if (!TextUtils.isEmpty(reponse)){
            try {
                JSONArray jsonArray = new JSONArray(reponse);
                for (int i=0; i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    City city = new City();
                    city.setCityName(jsonObject.getString("name"));
                    city.setCityCode(jsonObject.getInt("id"));
                    city.setProvinceId(ProvinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的县级数据
     *
     */

    public static boolean handleCountyResponse(String reponse,int CityId){
        if (!TextUtils.isEmpty(reponse)){
            try {
                JSONArray jsonArray = new JSONArray(reponse);
                for (int i=0; i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(jsonObject.getString("name"));
                    county.setWeatherId(jsonObject.getString("weather_id"));
                    county.setCityId(CityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
