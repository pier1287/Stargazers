package it.carusopi.stargazers.list.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import it.carusopi.stargazers.R
import it.carusopi.stargazers.data.model.Stargazer
import kotlinx.android.synthetic.main.recycler_item_stargazers_list.view.*


/**
 * Created by carusopi on 30/10/2017.
 */
class  StargazersListAdapter (var context: Context, var stargazersList: MutableList<Stargazer>):
        RecyclerView.Adapter<StargazersListAdapter.StargazerViewHolder>(){


    override fun getItemCount(): Int = stargazersList.size

    override fun onBindViewHolder(holder: StargazersListAdapter.StargazerViewHolder?, position: Int) {
        holder?.bind(stargazersList.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): StargazersListAdapter.StargazerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_item_stargazers_list, parent, false)
        return StargazerViewHolder(view)
    }

    fun addStargazers(stargazersToAdd: List<Stargazer>){
        stargazersList.addAll(stargazersToAdd)
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
}