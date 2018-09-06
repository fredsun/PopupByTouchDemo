package me.fredsun.popupbytouchdemo;

import android.graphics.drawable.ColorDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;

import org.xml.sax.helpers.ParserAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PopupRecycleHolder.ItemActionListener {

    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> data = new ArrayList<>();
        data.add("1111");
        data.add("2222");
        data.add("3333");
        data.add("4444");
        data.add("5555");
        PopupRecycleAdapter adapter = new PopupRecycleAdapter(data, this);
        RecyclerView recyclerview = findViewById(R.id.recycler_popup);
        recyclerview.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setHasFixedSize(true);
    }

    private void showPopupWindow(View anchorView, int x, int y) {
        View contentView = initPopupWindowContentView();
        mPopupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        setAnimate();
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        // 设置好参数之后再show
        int windowPos[] = PopupWindowUtil.calculatePopWindowPos(anchorView, contentView, x, y);
        mPopupWindow.showAtLocation(anchorView, Gravity.TOP | Gravity.START, windowPos[0], windowPos[1]);
    }

    private void setAnimate() {
        mPopupWindow.setAnimationStyle(R.style.md_popup_window_anim_style);
    }

    private View initPopupWindowContentView() {
        // popup window 布局
        int layoutId = R.layout.popup_basket_battle_select;   // 布局ID
        View contentView = LayoutInflater.from(this).inflate(layoutId, null);
        contentView.findViewById(R.id.tv_item_pop_basket_battle_mark).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                markItem(userGameId, userName);
                Toast.makeText(MainActivity.this, "clickMark", Toast.LENGTH_SHORT).show();
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
            }
        });
        contentView.findViewById(R.id.tv_item_pop_basket_battle_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                BasketBattleDialogFragment basketBattleDialogFragment = BasketBattleDialogFragment.newInstance(userGameId, userName, BasketBattleTestActivity.this);
//                basketBattleDialogFragment.show(getSupportFragmentManager(), "confirmDelete");
                Toast.makeText(MainActivity.this, "clickDelete", Toast.LENGTH_SHORT).show();
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
            }
        });
        return contentView;
    }

    @Override
    public void onFinishedItemLongClick(int adapterPosition, ConstraintLayout constraintContainer, int x, int y) {
        showPopupWindow(constraintContainer, x,y);
    }
}
