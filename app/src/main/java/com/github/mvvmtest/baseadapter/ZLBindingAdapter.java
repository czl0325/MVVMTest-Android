package com.github.mvvmtest.baseadapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.github.mvvmtest.R;

import java.util.List;

public class ZLBindingAdapter<T> extends RecyclerView.Adapter<ZLBindingAdapter.BindingHolder> {
    private List<T> items ;
    private int variableId;
    private int layoutId;

    public ZLBindingAdapter(List<T> items, int variableId, int layoutId) {
        this.items = items;
        this.variableId = variableId;
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public BindingHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), layoutId, viewGroup, false);
        return new BindingHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BindingHolder bindingHolder, int i) {
        bindingHolder.bindData(items.get(i));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class BindingHolder<T> extends RecyclerView.ViewHolder {
        ViewDataBinding binding;
        /**
         * @param binding   可以看作是这个hodler代表的布局的马甲，getRoot()方法会返回整个holder的最顶层的view
         * */
        public BindingHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindData(T item) {
            binding.setVariable(variableId ,item);
        }

    }
}
