package com.farmcast.ddata;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class data{
    public String cod;
    public int cnt;

    @SerializedName("list")
    public List<instance_data> instance_data_list;
}
