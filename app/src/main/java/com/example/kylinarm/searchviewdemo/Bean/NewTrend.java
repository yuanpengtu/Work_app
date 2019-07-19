package com.example.kylinarm.searchviewdemo.Bean;

public class NewTrend {
    private String title;
    private double money;
    private String unit;
    private String percentage;
    private int up_flag;
    private int first_flag;
    public NewTrend(String title,double money,String unit,String percentage,int up_flag,int first_flag){
        this.title=title;
        this.money=money;
        this.unit=unit;
        this.percentage=percentage;
        this.up_flag=up_flag;
        this.first_flag=first_flag;
    }
    public String getTitle(){
        return title;
    }
    public String getUnit(){
        return unit;
    }
    public String getPercentage(){
        return percentage;
    }
    public double getMoney(){
        return money;
    }
    public int getUp_flag(){
        return up_flag;
    }
    public int getFirst_flag(){
        return first_flag;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public void setFirst_flag(int first_flag) {
        this.first_flag = first_flag;
    }
    public void setMoney(double money) {
        this.money = money;
    }
    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }
    public void setUp_flag(int up_flag) {
        this.up_flag = up_flag;
    }
}
