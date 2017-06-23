package show.example.com.showapplication.Adpters;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

import com.bumptech.glide.Glide;

import show.example.com.showapplication.Entities.Business;
import show.example.com.showapplication.Fragments.BusinessFragment;
import show.example.com.showapplication.R;


/**
 * Created by Arye on 22/06/2017.
 */

public class BusinessAdapter extends  RecyclerView.Adapter<BusinessAdapter.MyViewHolder>{

    private Context mContext;
    private List<Business> businessList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }


    public BusinessAdapter(Context mContext, List<Business> businessList) {
        this.mContext = mContext;
        this.businessList = businessList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.business_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Business business = businessList.get(position);
        holder.title.setText(business.getBusiWebSite());
        holder.count.setText(business.getBusiCity());

        // loading business cover using Glide library
        Glide.with(mContext).load(business.getThumbnail()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showPopupMenu(holder.overflow);
            }
        });
    }



    @Override
    public int getItemCount() {
        return businessList.size();
    }
}

