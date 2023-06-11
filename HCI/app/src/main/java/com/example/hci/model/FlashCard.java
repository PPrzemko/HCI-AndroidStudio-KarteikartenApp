package com.example.hci.model;

public class FlashCard {

    private String front, back;

    public FlashCard(){
        this.front="";
        this.back="";
    }

    public FlashCard(String front, String back){
        this.front=front;
        this.back=back;
    }

    public void setFront(String front){
        this.front=front;
    }

    public void setBack(String back){
        this.back=back;
    }

    public String getFront() {
        return front;
    }

    public String getBack() {
        return back;
    }

}
