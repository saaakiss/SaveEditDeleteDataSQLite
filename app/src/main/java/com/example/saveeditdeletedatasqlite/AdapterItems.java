package com.example.saveeditdeletedatasqlite;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sak on 10-Jul-17.
 */

public class AdapterItems extends RecyclerView.Adapter<AdapterItems.ItemsHolder> {

    private Context context;
    private List<String> stringList;
    private RecyclerClickListener  recyclerClickListener;

    public AdapterItems(Context context, List<String> stringList, RecyclerClickListener recyclerClickListener){
        this.context = context;
        this.stringList = stringList;
        this.recyclerClickListener = recyclerClickListener;
    }

    @Override
    public ItemsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_item,parent, false);
        return new ItemsHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemsHolder holder, int position) {
        holder.textView.setText(stringList.get(position));
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public class ItemsHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.textViewItem)
        TextView textView;

        public ItemsHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerClickListener.onItemClicked(stringList.get(getLayoutPosition()));
                }
            });
        }
    }



}
