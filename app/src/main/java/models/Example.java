package models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class Example {

    @Expose
    private ArrayList<Result> result;

    public ArrayList<Result> getResult() {
        return this.result;
    }

    public void setResult(ArrayList<Result> result) {
        this.result = result;
    }
}
