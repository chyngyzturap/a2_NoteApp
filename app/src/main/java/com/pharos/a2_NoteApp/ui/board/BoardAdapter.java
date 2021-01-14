package com.pharos.a2_NoteApp.ui.board;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pharos.a2_NoteApp.OnItemClickListener;
import com.pharos.a2_NoteApp.R;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder>{
private String[] titles = new String[]{"fast", "free", "powerful"};
private OnItemClickListener onItemClickListener;

    public BoardAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pager_board,parent,false);
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

    public class ViewHolder extends RecyclerView.ViewHolder{
        private Button btnStart;
        private TextView textTitle;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        textTitle = itemView.findViewById(R.id.textTitle);
        btnStart = itemView.findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClick(getAdapterPosition());
            }
        });
    }

        public void bind(int position) {
        textTitle.setText(titles[position]);

        }
    }
}
