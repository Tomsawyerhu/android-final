package com.example.tiktok.utils;

import android.content.Context;
import android.view.View;

abstract class ViewLoader implements DataLoader {
    private View view;
    private Context context;

    public void setView(View view){
        this.view=view;
    }
    public View getView(){
        return this.view;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
