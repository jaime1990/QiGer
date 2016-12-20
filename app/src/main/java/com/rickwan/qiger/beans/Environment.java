package com.rickwan.qiger.beans;

import java.util.List;

/**
 * author wanqiang
 * date 16/12/19
 * desc${TODO}
 */

public class Environment {

    public String msg;

    public List<EnvironmentData> result;


    public class EnvironmentData{

        public String aqi;
        public String city;
        public String district;
        public String no2;
        public String pm10;
        public String pm25;
        public String province;
        public String quality;
        public String so2;
        public String updateTime;

    }
}
