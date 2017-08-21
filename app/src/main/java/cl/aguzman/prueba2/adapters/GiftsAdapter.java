package cl.aguzman.prueba2.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import cl.aguzman.prueba2.data.Queries;
import cl.aguzman.prueba2.models.Gift;

import static cl.aguzman.prueba2.R.color;
import static cl.aguzman.prueba2.R.drawable;
import static cl.aguzman.prueba2.R.id;
import static cl.aguzman.prueba2.R.layout;

public class GiftsAdapter extends RecyclerView.Adapter<GiftsAdapter.ViewHolder> {

    public List<Gift> listGifts = new Queries().gifts();
    private ClickGift clickListener;

    public GiftsAdapter(List<Gift> listGifts){
        this.listGifts = listGifts;
    }

    public GiftsAdapter(List<Gift> listGifts, ClickGift clickListener) {
        this.clickListener = clickListener;
        this.listGifts = listGifts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout.list_item_gift, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Gift gift = listGifts.get(position);
        holder.name.setText(gift.getName());
        holder.price.setText("$"+String.valueOf(gift.getPrice()));
        holder.priority.setText("Prioridad del regalo "+String.valueOf(gift.getPriority()));
        LinearLayout name = holder.row;

        int clickPosition = holder.getAdapterPosition();
        Long getGiftId = listGifts.get(clickPosition).getId();

        name.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                holder.row.setBackgroundResource(color.colorTextGray);

                int clickPosition = holder.getAdapterPosition();
                Long getGiftId = listGifts.get(clickPosition).getId();

                listGifts.remove(clickPosition);
                notifyItemRemoved(clickPosition);
                clickListener.removeGift(getGiftId);
                Log.d("DATA", String.valueOf(listGifts.size()));
                return true;
            }
        });

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.row.setBackgroundResource(color.colorTextGray);
                int clickPosition = holder.getAdapterPosition();
                Long getGiftId = listGifts.get(clickPosition).getId();
                clickListener.clickContent(getGiftId);
            }
        });

        Gift giftItem = Gift.findById(Gift.class, getGiftId);
        if(giftItem.isBought() == true){
            holder.row.setBackgroundResource(drawable.bg_green_underline_white);
        }

    }

    @Override
    public int getItemCount() {
        return listGifts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, price, priority;
        private LinearLayout row;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(id.nameTv);
            price = (TextView) itemView.findViewById(id.priceTv);
            priority = (TextView) itemView.findViewById(id.priorityTv);
            row = (LinearLayout) itemView;
        }
    }

}
