package sanchez.sanchez.sergio.masom_app.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import java.util.List;
import sanchez.sanchez.sergio.masom_app.R;

/**
 * Support Progressive Load Adapter
 * @param <T>
 * @param <E>
 */
public abstract class SupportProgressiveLoadAdapter<T, E extends SupportRecyclerViewAdapter.SupportViewHolder>
        extends SupportRecyclerViewAdapter<T, SupportRecyclerViewAdapter.SupportViewHolder>{

    private final int VIEW_TYPE_ITEM = 1;
    private final int VIEW_TYPE_PROGRESSBAR = 0;
    private boolean isFooterEnabled = false;

    /**
     *
     * @param context
     * @param data
     */
    public SupportProgressiveLoadAdapter(final Context context, final List<T> data) {
        super(context, data);
    }

    /**
     * Get Item Count
     * @return
     */
    @Override
    public int getItemCount() {
        return  (isFooterEnabled) ? data.size() + 1 : data.size();
    }

    /**
     * Get Item View Type
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return (isFooterEnabled && position >= data.size() )
                ? VIEW_TYPE_PROGRESSBAR : VIEW_TYPE_ITEM;
    }

    /**
     * On create view holder
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public SupportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SupportViewHolder vh;

        if (viewType == VIEW_TYPE_ITEM) {
            vh = getViewHolderItem(parent);
        }else{
            final View view = inflater.inflate(R.layout.progress_item, parent, false);
            vh = new SupportProgressiveLoadViewHolder(view);
        }

        return vh;
    }

    @Override
    public void onBindViewHolder(SupportViewHolder holder, int position) {
        if (holder.getClass() != SupportProgressiveLoadViewHolder.class){
            holder.bind(data.get(position));
        }
    }

    /**
     * Enable or disable footer (Default is true)
     *
     * @param isEnabled boolean to turn on or off footer.
     */
    public void enableFooter(boolean isEnabled){
        this.isFooterEnabled = isEnabled;
    }

    /**
     * Get View Holder Item
     * @param parent
     * @return
     */
    protected abstract SupportRecyclerViewAdapter.SupportViewHolder
            getViewHolderItem(ViewGroup parent);

    /**
     * Support Progressive Load ViewHolder
     */
    public class SupportProgressiveLoadViewHolder extends SupportRecyclerViewAdapter<T,
            SupportRecyclerViewAdapter.SupportViewHolder>.SupportViewHolder<T> {

        public ProgressBar progressBar;

        SupportProgressiveLoadViewHolder(View v) {
            super(v);
            progressBar =  v.findViewById(R.id.progressBar);
        }
    }
}
