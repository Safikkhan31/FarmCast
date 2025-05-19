package ddata;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class data{
    String cod;
    int cnt;

    @SerializedName("list")
    public List<instance_data> instance_data_list;
}
