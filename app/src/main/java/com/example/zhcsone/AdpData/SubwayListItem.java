package com.example.zhcsone.AdpData;

public class SubwayListItem {
    String tv1;
    String tv2;
    String tv3;
    String id;

    public SubwayListItem(String tv1, String tv2, String tv3, String id) {
        this.tv1 = tv1;
        this.tv2 = tv2;
        this.tv3 = tv3;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTv1() {
        return tv1;
    }

    public void setTv1(String tv1) {
        this.tv1 = tv1;
    }

    public String getTv2() {
        return tv2;
    }

    public void setTv2(String tv2) {
        this.tv2 = tv2;
    }

    public String getTv3() {
        return tv3;
    }

    public void setTv3(String tv3) {
        this.tv3 = tv3;
    }
}
