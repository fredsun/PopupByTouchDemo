package me.fredsun.popupbytouchdemo;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by fred on 2018/9/6.
 */

public class PopupRecycleHolder extends RecyclerView.ViewHolder {
    ConstraintLayout constraintContainer;
    int x;
    int y;

    public PopupRecycleHolder(View itemView) {
        super(itemView);
        constraintContainer = itemView.findViewById(R.id.container);
    }

    public void setData(final ItemActionListener listener) {
        constraintContainer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onFinishedItemLongClick(getAdapterPosition(), constraintContainer, x, y);
                return false;
            }
        });
        constraintContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                x = (int) event.getRawX();
                y = (int) event.getRawY();
                return false;
            }
        });
    }

    interface ItemActionListener{
        void onFinishedItemLongClick(int adapterPosition, ConstraintLayout constraintContainer, int x, int y);
    }
}
