package br.alicefranco.countriesapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

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


//        Bitmap icon;
//        View itemView = viewHolder.itemView;
//        Integer thresholdPos = - (itemView.getRight() - itemView.getLeft()) + ((itemView.getRight() - itemView.getLeft())/4)*3;
//        Integer thresholdIm =  (itemView.getRight() - itemView.getLeft()) - ((itemView.getRight() - itemView.getLeft())/4);
//        float height = (float) itemView.getBottom() - (float) itemView.getTop();
//        float width = height / 3;
//        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
//            if(isCurrentlyActive) {
//                if (dX < 0) {
//                    p.setColor(context.getResources().getColor(R.color.colorMainApp));
//                    RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
//                    c.drawRect(background, p);
//                    icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.delete_icon);
//                    RectF icon_dest = new RectF((float) itemView.getRight() - 2 * width, (float) itemView.getTop() + width, (float) itemView.getRight() - width, (float) itemView.getBottom() - width);
//                    c.drawBitmap(icon, null, icon_dest, p);
//                }
//                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//            }
//            else{
//                if (dX > thresholdPos) {
//                    p.setColor(context.getResources().getColor(R.color.colorMainApp));
//                    RectF background = new RectF((float) thresholdIm, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
//                    c.drawRect(background, p);
//                    icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.delete_icon);
//                    RectF icon_dest = new RectF((float) itemView.getRight() - 2 * width, (float) itemView.getTop() + width, (float) itemView.getRight() - width, (float) itemView.getBottom() - width);
//                    c.drawBitmap(icon, null, icon_dest, p);
//                }
//                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//            }
//        }
//        else super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        /*float translationX = dX;
        View itemView = viewHolder.itemView;
        float height = (float)itemView.getBottom() - (float)itemView.getTop();
        Bitmap icon;
        float width = height / 3;
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE && dX <= 0) // Swiping Left
        {
            translationX = -Math.min(-dX, height);
            p.setColor(context.getResources().getColor(R.color.colorMainApp));
            RectF background = new RectF((float)itemView.getRight() + dX, (float)itemView.getTop(), (float)itemView.getRight(), (float)itemView.getBottom());
            c.drawRect(background, p);

            icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.delete_icon);
            RectF icon_dest = new RectF((float) itemView.getRight() - 2 * width, (float) itemView.getTop() + width, (float) itemView.getRight() - width, (float) itemView.getBottom() - width);
            c.drawBitmap(icon, null, icon_dest, p);

            //viewHolder.ItemView.TranslationX = translationX;
        }
        /*else if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE && dX > 0) // Swiping Right
        {
            translationX = Math.min(dX, height * 2);
            p.setColor(context.getResources().getColor(R.color.colorMainApp));
            RectF background = new RectF((float)itemView.getRight() + translationX, (float)itemView.getTop(), (float)itemView.getRight(), (float)itemView.getBottom());
            c.drawRect(background, p);

            icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.delete_icon);
            RectF icon_dest = new RectF((float) itemView.getRight() - 2 * width, (float) itemView.getTop() + width, (float) itemView.getRight() - width, (float) itemView.getBottom() - width);
            c.drawBitmap(icon, null, icon_dest, p);

        }

        super.onChildDraw(c, recyclerView, viewHolder, translationX, dY, actionState, isCurrentlyActive);*/

       /*try {

            Bitmap icon;
            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                View itemView = viewHolder.itemView;
                float height = (float) itemView.getBottom() - (float) itemView.getTop();
                float width = height / 5;
                viewHolder.itemView.setTranslationX(dX / 5);

                p.setColor(Color.parseColor("#D32F2F"));
                RectF background = new RectF((float) itemView.getRight() + dX / 5, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
                c.drawRect(background, p);
                icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.delete_icon);
                RectF icon_dest = new RectF((float) itemView.getRight() - 2 * width, (float) itemView.getTop() + width, (float) itemView.getRight() - width, (float) itemView.getBottom() - width);
                c.drawBitmap(icon, null, icon_dest, p);
            } else {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }
}
