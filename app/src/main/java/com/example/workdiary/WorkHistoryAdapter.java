package com.example.workdiary;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workdiary.databinding.ItemWorkendBinding;
import com.example.workdiary.databinding.ItemWorkhstartBinding;

import org.jetbrains.annotations.NotNull;

public class WorkHistoryAdapter extends ListAdapter<WorkHistoryModel, RecyclerView.ViewHolder> {

    protected WorkHistoryAdapter(@NonNull @NotNull DiffUtil.ItemCallback<WorkHistoryModel> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ViewType.START_TIME:
                ItemWorkhstartBinding startBinding =
                        ItemWorkhstartBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new StartWorkViewHolder(startBinding);

            case ViewType.END_TIME:
                    ItemWorkendBinding endBinding =
                            ItemWorkendBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                    return new EndWorkViewHolder(endBinding);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        WorkHistoryModel historyData = getItem(position);

        switch (holder.getItemViewType()){
            case ViewType.START_TIME:
                holder = (StartWorkViewHolder) holder;
                ((StartWorkViewHolder) holder).binding.setVariable(BR.data, historyData);
        }
    }

    public class StartWorkViewHolder extends RecyclerView.ViewHolder {
        public ItemWorkhstartBinding binding;
        public StartWorkViewHolder(
                ItemWorkhstartBinding binding
        ) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public class EndWorkViewHolder extends RecyclerView.ViewHolder {
        public ItemWorkendBinding binding;

        public EndWorkViewHolder(
                ItemWorkendBinding binding
        ) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getTag();
    }

    public class WorkHistoryDiffUtil extends DiffUtil.ItemCallback<String> {
        @Override
        public boolean areItemsTheSame(@NonNull @NotNull String oldItem, @NonNull @NotNull String newItem) {
            return oldItem.hashCode() == newItem.hashCode();
        }

        @Override
        public boolean areContentsTheSame(@NonNull @NotNull String oldItem, @NonNull @NotNull String newItem) {
            return oldItem.equals(newItem);
        }
    }

    public class ViewType {
        public static final int START_TIME = 0;
        public static final int END_TIME = 1;
    }
}


