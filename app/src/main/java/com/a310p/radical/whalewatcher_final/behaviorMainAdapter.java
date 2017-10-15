package com.a310p.radical.whalewatcher_final;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by bowenz on 1/10/17.
 */

public class behaviorMainAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] itemname;
    private final Integer[] imgid;

    public behaviorMainAdapter(Activity context, String[] itemname, Integer[] imgid) {
        super(context, R.layout.list_selection, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_selection, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.whale_behavior_name);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.whale_behavior_pic);
        txtTitle.setText(itemname[position]);
        imageView.setImageResource(imgid[position]);
        return rowView;

    };
}
