package it.carusopi.stargazers.list.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import it.carusopi.stargazers.R
import it.carusopi.stargazers.data.model.Stargazer
import it.carusopi.stargazers.data.model.StargazersPage
import kotlinx.android.synthetic.main.recycler_item_loader.view.*
import kotlinx.android.synthetic.main.recycler_item_stargazers_list.view.*


/**
 * Created by carusopi on 30/10/2017.
 */
class  StargazersListAdapter (var context: Context):
        RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val stargazersList: MutableList<Stargazer> = mutableListOf()
    private var hasNextPage = false

    companion object {
        val VIEW_TYPE_LOADING = 0
        val VIEW_TYPE_ITEM = 1
    }

    override fun getItemCount(): Int = if (hasNextPage) stargazersList.size + 1 else stargazersList.size

    override fun getItemViewType(position: Int): Int = if (position < (stargazersList.size)) VIEW_TYPE_ITEM else VIEW_TYPE_LOADING

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        when(holder) {
            is StargazerViewHolder -> holder.bind(stargazersList[position])
            is LoaderViewHolder -> holder.progress.isIndeterminate = true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_ITEM) {
            val view = LayoutInflater.from(context).inflate(R.layout.recycler_item_stargazers_list, parent, false)
            return StargazerViewHolder(view)
        }
        else {
            val view = LayoutInflater.from(context).inflate(R.layout.recycler_item_loader, parent, false)
            return LoaderViewHolder(view)
        }
    }

    fun addStargazers(stargazersPage: StargazersPage){
        stargazersList.addAll(stargazersPage.stargazersList)
        hasNextPage = stargazersPage.hasNextPage
        notifyDataSetChanged()
    }

    class StargazerViewHolder(view: View): RecyclerView.ViewHolder(view){

        val user: TextView = itemView.username
        val avatar: SimpleDraweeView = itemView.imgAvatar

        fun bind(item: Stargazer) {
            user.text = item.login
            avatar.setImageURI(item.avatarUrl)
        }
    }

    class LoaderViewHolder(view: View): RecyclerView.ViewHolder(view){
        val progress: ProgressBar = itemView.progressBar

    }
}