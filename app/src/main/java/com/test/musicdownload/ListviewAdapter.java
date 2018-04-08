package com.test.musicdownload;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.test.musicdownload.javabean.BaseMp3Info;

import java.util.List;

public class ListviewAdapter extends BaseAdapter {
    private List<BaseMp3Info> list;
    private Context context;

    public ListviewAdapter(List<BaseMp3Info> list, Context context) {
        this.list = list;
        this.context=context;
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        if (convertView==null){
            view= LayoutInflater.from(context).inflate(R.layout.list_item,null);
            viewHolder=new ViewHolder();
            viewHolder.musicTitle=view.findViewById(R.id.textTitle);
            viewHolder.musicArtist=view.findViewById(R.id.textArtist);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.musicTitle.setText(list.get(position).getTitle());
        viewHolder.musicArtist.setText(list.get(position).getArtist());
        return view;
    }
    class ViewHolder{
        TextView musicTitle;
        TextView musicArtist;
    }
}
