package sanchez.sanchez.sergio.masom_app.ui.adapter;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import sanchez.sanchez.sergio.masom_app.ui.adapter.impl.LastAlertsAdapter;

/**
 * Last Alerts Item Touch Helper
 */
public final class SupportItemTouchHelper extends ItemTouchHelper.SimpleCallback {

    private LastAlertsItemTouchHelperListener listener;

    /**
     * @param dragDirs
     * @param swipeDirs
     * @param listener
     */
    public SupportItemTouchHelper(int dragDirs, int swipeDirs,
                                  final LastAlertsItemTouchHelperListener listener) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
    }

    /**
     * On Move
     * @param recyclerView
     * @param viewHolder
     * @param target
     * @return
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {
        return true;
    }

    /**
     * On Selected Changed
     * @param viewHolder
     * @param actionState
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (viewHolder != null) {
            final View foregroundView = ((LastAlertsAdapter.LastAlertsViewHolder) viewHolder).getViewForeground();
            getDefaultUIUtil().onSelected(foregroundView);
        }
    }

    /**
     * On Child Draw over
     * @param c
     * @param recyclerView
     * @param viewHolder
     * @param dX
     * @param dY
     * @param actionState
     * @param isCurrentlyActive
     */
    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView,
                                RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                int actionState, boolean isCurrentlyActive) {
        final View foregroundView = ((LastAlertsAdapter.LastAlertsViewHolder) viewHolder).getViewForeground();
        getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY,
                actionState, isCurrentlyActive);
    }

    /**
     * Clear View
     * @param recyclerView
     * @param viewHolder
     */
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        final View foregroundView = ((LastAlertsAdapter.LastAlertsViewHolder) viewHolder).getViewForeground();
        getDefaultUIUtil().clearView(foregroundView);
    }

    /**
     * On Child Draw
     * @param c
     * @param recyclerView
     * @param viewHolder
     * @param dX
     * @param dY
     * @param actionState
     * @param isCurrentlyActive
     */
    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView,
                            RecyclerView.ViewHolder viewHolder, float dX, float dY,
                            int actionState, boolean isCurrentlyActive) {
        final View foregroundView = ((LastAlertsAdapter.LastAlertsViewHolder) viewHolder).getViewForeground();
        getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY,
                actionState, isCurrentlyActive);
    }

    /**
     * On Swiped
     * @param viewHolder
     * @param direction
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onSwiped(viewHolder, direction, viewHolder.getAdapterPosition());
    }

    /**
     * Convert To Absolute Direction
     * @param flags
     * @param layoutDirection
     * @return
     */
    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }


    /**
     * Last Alerts Item Touch Helper Listener
     */
    public interface LastAlertsItemTouchHelperListener {
        void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
    }
}
