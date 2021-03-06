package sanchez.sanchez.sergio.bullkeeper.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import java.util.ArrayList;
import java.util.List;

import sanchez.sanchez.sergio.bullkeeper.R;

/**
 * Support Recycler View Adapter
 * @param <T>
 */
public abstract class SupportRecyclerViewAdapter<T>
            extends RecyclerView.Adapter {

    /**
     * View Types
     */
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_ITEM_DEFAULT = 2;
    private static final int TYPE_FOOTER = 3;


    protected Context context;
    protected LayoutInflater inflater;
    protected List<T> data;
    protected String highlightText;

    protected boolean hasHeader = false;
    protected boolean hasFooter = false;

    private int minItemsCount;

    private OnSupportRecyclerViewListener<T> listener;

    /**
     *
     * @param context
     * @param data
     */
    public SupportRecyclerViewAdapter(final Context context, final ArrayList<T> data, final int minItemsCount){
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
        this.minItemsCount = minItemsCount;
    }

    /**
     *
     * @param context
     * @param data
     */
    public SupportRecyclerViewAdapter(final Context context, final ArrayList<T> data){
        this(context, data, data.size() > 0 ? data.size() - 1 : 0);
    }

    /**
     * Is Has Header
     * @return
     */
    public boolean isHasHeader() {
        return hasHeader;
    }

    /**
     * Set Has Header
     * @param hasHeader
     */
    public void setHasHeader(boolean hasHeader) {
        this.hasHeader = hasHeader;
    }

    /**
     * Is Has footer
     * @return
     */
    public boolean isHasFooter() {
        return hasFooter;
    }

    /**
     * set has footer
     * @param hasFooter
     */
    public void setHasFooter(boolean hasFooter) {
        this.hasFooter = hasFooter;
    }

    /**
     * Get Item
     * @param position
     * @return
     */
    public T getItem(int position){ return data.get(position);}

    /**
     * Set data
     * @param data
     */
    public void setData(List<T> data){
        this.data = data;
    }

    /**
     * Get data
     * @return
     */
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

    /**
     * Is Position Header
     * @param position
     * @return
     */
    private boolean isPositionHeader(int position) {
        return hasHeader && position == 0;
    }


    /**
     * Is Position Footer
     * @param position
     * @return
     */
    private boolean isPositionFooter(int position) {
        return hasFooter && position > data.size();
    }

    /**
     * Is Default Item
     * @param position
     * @return
     */
    private boolean isDefaultItem(int position) {

        if (hasHeader)
            position -= 1;

        return position >= data.size() && position <= minItemsCount;

    }


    /**
     * Get Item by adapter position
     * @return
     */
    public T getItemByAdapterPosition(int position) {
        return data.get(position);
    }


    /**
     * Get Item View Type
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {

        int viewType;

        if (isPositionHeader(position)) {
            viewType = TYPE_HEADER;
        } else if (isPositionFooter(position)) {
            viewType = TYPE_FOOTER;
        } else  if(isDefaultItem(position)) {
            viewType = TYPE_ITEM_DEFAULT;
        } else {
            viewType = TYPE_ITEM;
        }

        return viewType;
    }

    /**
     * Get Item Count
     * @return
     */
    @Override
    public int getItemCount() {


        // 4 (0-3)
        // 6
        // item count -> 6
        // has header -> 7

        int itemCount = Math.max(data.size(), minItemsCount);

        if (hasHeader && hasFooter) {
            itemCount = itemCount + 2;
        } else if(hasHeader || hasFooter) {
            itemCount = itemCount + 1;
        }

        return itemCount;
    }

    /**
     * Get Item id
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * On Create View Holder
     * @param viewGroup
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        RecyclerView.ViewHolder viewHolder;

        if (viewType == TYPE_ITEM) {
            viewHolder = onCreateItemViewHolder(viewGroup);
        } else if (viewType == TYPE_HEADER) {
            viewHolder = onCreateHeaderViewHolder(viewGroup);
        } else if (viewType == TYPE_FOOTER) {
            viewHolder = onCreateFooterViewHolder(viewGroup);
        } else if( viewType == TYPE_ITEM_DEFAULT) {
            viewHolder = onCreateItemDefaultViewHolder(viewGroup);
        } else {
            throw new RuntimeException("Type of view not supported");
        }

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof SupportItemViewHolder) {

            if (hasHeader) position -= 1;

            ((SupportItemViewHolder)holder).bind(data.get(position));

        }

    }

    /**
     * On Create Item View Holder
     * @param viewGroup
     */
    protected abstract RecyclerView.ViewHolder onCreateItemViewHolder(final ViewGroup viewGroup);


    /**
     * On Create Item View Holder
     * @param viewGroup
     */
    protected RecyclerView.ViewHolder onCreateHeaderViewHolder(final ViewGroup viewGroup){
        throw new UnsupportedOperationException("Header not implemented");
    }

    /**
     * On Create Item View Holder
     * @param viewGroup
     */
    protected RecyclerView.ViewHolder onCreateFooterViewHolder(final ViewGroup viewGroup){
        throw new UnsupportedOperationException("Footer not implemented");
    }

    /**
     * On Create Item Default View Holder
     * @param viewGroup
     */
    protected RecyclerView.ViewHolder onCreateItemDefaultViewHolder(final ViewGroup viewGroup) {
        throw new UnsupportedOperationException("Default Item not implemented");
    };

    /**
     * Remove Item
     * @param position
     */
    public void removeItem(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * Restore Item
     * @param item
     * @param position
     */
    public void restoreItem(final T item, int position) {
        data.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }

    /**
     * Remove All
     */
    public void removeAll(){
        notifyItemRangeRemoved(0, data.size());
    }

    public void setOnSupportRecyclerViewListener(OnSupportRecyclerViewListener<T> listener){
        this.listener = listener;
    }

    public interface OnSupportRecyclerViewListener<T> {
        void onHeaderClick();
        void onItemClick(T item);
        void onFooterClick();
    }


    /**
     *  View Holders Types
     */

    /**
     * Support Item View Holder
     * @param <T>
     */
    public abstract class SupportItemViewHolder<T> extends RecyclerView.ViewHolder{

        public SupportItemViewHolder(View itemView) {
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

    /**
     * Support Item Swiped View Holder
     * @param <T>
     */
    public abstract class SupportItemSwipedViewHolder<T> extends SupportItemViewHolder<T> {

        private ViewGroup viewBackground, viewForeground;

        public SupportItemSwipedViewHolder(View itemView) {
            super(itemView);
            this.viewBackground = itemView.findViewById(R.id.item_background);
            this.viewForeground = itemView.findViewById(R.id.item_foreground);
        }

        public ViewGroup getViewBackground() {
            return viewBackground;
        }

        public ViewGroup getViewForeground() {
            return viewForeground;
        }
    }

    /**
     * Support Header View Holder
     */
    public abstract class SupportHeaderViewHolder extends RecyclerView.ViewHolder {

        public SupportHeaderViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null)
                        listener.onHeaderClick();
                }
            });

        }

    }

    /**
     * Support Footer View Holder
     */
    public class SupportFooterViewHolder extends RecyclerView.ViewHolder {

        private ProgressBar progressBar;

        public SupportFooterViewHolder(View itemView) {
            super(itemView);

            this.progressBar = itemView.findViewById(R.id.progressBar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null)
                        listener.onFooterClick();
                }
            });

        }
    }

}
