package com.pharos.a2_NoteApp.ui.board;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pharos.a2_NoteApp.MainActivity;
import com.pharos.a2_NoteApp.OnItemClickListener;
import com.pharos.a2_NoteApp.R;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {
    private String[] titles = new String[]{"Coffee House", "Restaurant", "Pizzeria"};
    private String[] descriptions = new String[]{"A coffee house is a business that typically focuses on serving coffee and related products. It usually is thought of as a haven for people to sit and relax.",
            "A restaurant (sometimes known as a diner) is a place where cooked food is sold to the public, and where people sit down to eat it. It is also a place where people go to enjoy the time and to eat a meal.",
            "Restaurant that serves pizza"};
    private int[] images = {R.drawable.coffeeshop, R.drawable.restaraunt, R.drawable.pizzeria};
    private OnItemClickListener onItemClickListener;
    private MainActivity mainActivity;

    public BoardAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pager_board, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);


    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private Button btnStart;
        private TextView textTitle;
        private TextView textDescription;
        private ImageView imageView;
        private ImageButton imgBtnSkip;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textTitle = itemView.findViewById(R.id.textTitle);
            textDescription = itemView.findViewById(R.id.textDesc);
            btnStart = itemView.findViewById(R.id.btnStart);
            imageView = itemView.findViewById(R.id.imageView);
            imgBtnSkip = itemView.findViewById(R.id.imageSkip);

            btnStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(getAdapterPosition());
                }
            });
            imgBtnSkip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(getAdapterPosition());
                }
            });
        }

        public void bind(int position) {
            textTitle.setText(titles[position]);
            textDescription.setText(descriptions[position]);
            imageView.setImageResource(images[position]);
            btnStart.setVisibility(View.GONE);
            if (position == 2) {
                btnStart.setVisibility(View.VISIBLE);
            }

        }
    }
}
