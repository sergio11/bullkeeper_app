package sanchez.sanchez.sergio.masom_app.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import java.util.List;

/**
 * Recycler View Base Adapter
 * @param <T>
 * @param <E>
 */
public abstract class SupportRecyclerViewAdapter<T,
        E extends SupportRecyclerViewAdapter.SupportViewHolder>
            extends RecyclerView.Adapter<E> {

    protected Context context;
    protected LayoutInflater inflater;
    protected List<T> data;
    protected String highlightText;

    private  OnItemClickListener<T> listener;

    /**
     *
     * @param context
     * @param data
     */
    public SupportRecyclerViewAdapter(final Context context, final List<T> data){
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * On Bind View holder
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull E holder, int position) {
        holder.bind(data.get(position));
    }

    public T getItem(int position){ return data.get(position);}

    public void setData(List<T> data){
        this.data = data;
    }

    public List<T> getData(){
        return this.data;
    }

    /**
     * Set Highlight Text
     * @param highlightText
     */
    public void setHighlightText(final String highlightText) {
        this.highlightText = highlightText.toLowerCase();
    }

    /**
     * Has Highlight Text
     * @return
     */
    public boolean hasHighlightText(){
        return highlightText != null && highlightText.length() > 0 ? true : false;
    }

    /**
     * Get Spannable String
     * @param text
     * @return
     */
    protected Spannable getSpannableString(final String text){
        String lowerText = text.toLowerCase();
        Spannable spannable = new SpannableString(lowerText);
        int startIndex = lowerText.indexOf(highlightText);
        if (startIndex >= 0){
            int stopIndex = startIndex + highlightText.length();
            spannable.setSpan(new ForegroundColorSpan(Color.YELLOW), startIndex, stopIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannable;
    }

    public void setOnItemClickListener(OnItemClickListener<T> listener){
        this.listener = listener;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(T item);
    }

    /**
     * Base View Holder
     * @param <T>
     */
    public abstract class SupportViewHolder<T> extends RecyclerView.ViewHolder{

        public SupportViewHolder(View itemView) {
            super(itemView);
        }

        public void bind(T element){
            if(listener != null){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        listener.onItemClick(data.get(getLayoutPosition()));
                    }
                });
            }
        }
    }

}
