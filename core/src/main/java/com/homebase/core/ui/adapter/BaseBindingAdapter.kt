package com.homebase.core.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseBindingAdapter<T : ViewDataBinding> : RecyclerView.Adapter<BindingViewHolder<T>>() {

    abstract fun onCreateBindingViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): T

    abstract fun onBindViewStateHolder(binding: T, position: Int, viewType: Int)

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<T> {
        return BindingViewHolder.create(onCreateBindingViewHolder(LayoutInflater.from(parent.context), parent, viewType))
    }

    final override fun onBindViewHolder(holder: BindingViewHolder<T>, position: Int) {
        onBindViewStateHolder(holder.binding, position, getItemViewType(position))
    }

}

class BindingViewHolder<T : ViewDataBinding> private constructor(val binding: T) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun <T : ViewDataBinding> create(parent: ViewGroup, @LayoutRes layoutRes: Int): BindingViewHolder<T> {
            val binding = DataBindingUtil.inflate<T>(LayoutInflater.from(parent.context), layoutRes, parent, false)
            return BindingViewHolder(binding)
        }

        fun <T : ViewDataBinding> create(viewDataBinding: T): BindingViewHolder<T> {
            return BindingViewHolder(viewDataBinding)
        }
    }
}