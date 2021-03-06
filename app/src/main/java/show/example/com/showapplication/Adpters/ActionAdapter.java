package show.example.com.showapplication.Adpters;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import show.example.com.showapplication.Entities.Action;
import show.example.com.showapplication.R;

/**
 * Created by Arye on 25/06/2017.
 */

public class ActionAdapter extends  RecyclerView.Adapter<ActionAdapter.MyViewHolder>{

    private Context mContext;
    private List<Action> actionList;

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


    public ActionAdapter(Context mContext, List<Action> actionList) {
        this.mContext = mContext;
        this.actionList = actionList;
    }

    @Override
    public ActionAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sh_card, parent, false);

        return new ActionAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ActionAdapter.MyViewHolder holder, int position) {
        final Action action = actionList.get(position);
        holder.title.setText(action.getActType());
        holder.count.setText(action.getActState());

        // loading action cover using Glide library
        Glide.with(mContext).load(action.getThumbnail()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow , action );
            }
        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view , Action action) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        popup.getMenu().add(" Type: " + action.getActType());
        popup.getMenu().add(" Description: "+ action.getActDescription());
        popup.getMenu().add(" Price: " + String.valueOf(action.getActPrice())+ " $");
        popup.getMenu().add(" Start: "+ action.getActStart());
        popup.getMenu().add(" End: "+ action.getActEnd());
        popup.getMenu().add(" State: " + action.getActState());

        popup.show();
    }





    @Override
    public int getItemCount() {
        return actionList.size();
    }
}

