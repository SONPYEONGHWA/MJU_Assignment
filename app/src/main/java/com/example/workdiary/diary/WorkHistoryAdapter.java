package com.example.workdiary.diary;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workdiary.BR;
import com.example.workdiary.databinding.ItemWorkendBinding;
import com.example.workdiary.databinding.ItemWorkhstartBinding;
import com.example.workdiary.util.DateUtil;
import com.example.workdiary.work.WorkHistoryModel;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public class WorkHistoryAdapter extends ListAdapter<WorkHistoryModel, RecyclerView.ViewHolder> {
    @Inject public DateUtil dateUtil;
    private final String userName;

    public WorkHistoryAdapter(
            @NonNull @NotNull DiffUtil.ItemCallback<WorkHistoryModel> diffCallback,
            String userName) {
        super(diffCallback);
        this.userName = userName;
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

        if (holder instanceof StartWorkViewHolder) {
            ((StartWorkViewHolder) holder).binding.setVariable(BR.data, historyData);
            ((StartWorkViewHolder) holder).binding.tvName.setText(userName);
        } else {
            ((EndWorkViewHolder) holder).binding.setVariable(BR.data, historyData);
            ((EndWorkViewHolder) holder).binding.tvName.setText(userName);
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

    public static class WorkHistoryDiffUtil extends DiffUtil.ItemCallback<WorkHistoryModel> {
        @Override
        public boolean areItemsTheSame(@NonNull @NotNull WorkHistoryModel oldItem, @NonNull @NotNull WorkHistoryModel newItem) {
            return oldItem.hashCode() == newItem.hashCode();
        }

        @Override
        public boolean areContentsTheSame(@NonNull @NotNull WorkHistoryModel oldItem, @NonNull @NotNull WorkHistoryModel newItem) {
            return oldItem.getIndex() == newItem.getIndex();
        }
    }

    public class ViewType {
        public static final int START_TIME = 0;
        public static final int END_TIME = 1;
    }
}


