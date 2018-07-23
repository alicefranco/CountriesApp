package br.alicefranco.countriesapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;



//
public class CustomSwipeCallback extends ItemTouchHelper.Callback {
    private RecyclerView recyclerView;
    private CountryAdapter adapter;
    private Context context;
    private Paint p = new Paint();

    public CustomSwipeCallback(Context context, RecyclerView recyclerView, CountryAdapter adapter){
        this.context = context;
        this.adapter = adapter;
        this.recyclerView = recyclerView;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(ItemTouchHelper.ACTION_STATE_IDLE, ItemTouchHelper.LEFT);
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return true;
    }


    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        adapter.remove(viewHolder.getAdapterPosition());
    }

    @Override
    public float getSwipeThreshold (RecyclerView.ViewHolder viewHolder){
        return .25f;
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        Bitmap icon;
        View itemView = viewHolder.itemView;
        Integer thresholdPos = - (itemView.getRight() - itemView.getLeft()) + ((itemView.getRight() - itemView.getLeft())/4)*3;
        Integer thresholdIm =  (itemView.getRight() - itemView.getLeft()) - ((itemView.getRight() - itemView.getLeft())/4);
        float height = (float) itemView.getBottom() - (float) itemView.getTop();
        float width = height / 3;
        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            if (dX < 0) {
                p.setColor(context.getResources().getColor(R.color.colorMainApp));
                RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
                c.drawRect(background, p);
                icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.delete_icon);
                RectF icon_dest = new RectF((float) itemView.getRight() - 2 * width, (float) itemView.getTop() + width, (float) itemView.getRight() - width, (float) itemView.getBottom() - width);
                c.drawBitmap(icon, null, icon_dest, p);
            }
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    }
}
