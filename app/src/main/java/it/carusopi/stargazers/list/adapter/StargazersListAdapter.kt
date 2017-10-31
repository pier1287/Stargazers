package it.carusopi.stargazers.list.adapter

import android.content.Context
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView
import it.carusopi.stargazers.R
import it.carusopi.stargazers.data.model.Stargazer
import kotlinx.android.synthetic.main.item_recycler_stargazers_list.view.*


/**
 * Created by carusopi on 30/10/2017.
 */
class  StargazersListAdapter (var context: Context, var stargazersList: List<Stargazer>):
        RecyclerView.Adapter<StargazersListAdapter.StargazerViewHolder>(){


    override fun getItemCount(): Int = stargazersList.size

    override fun onBindViewHolder(holder: StargazersListAdapter.StargazerViewHolder?, position: Int) {
        holder?.bind(stargazersList.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): StargazersListAdapter.StargazerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_recycler_stargazers_list, parent, false)
        return StargazerViewHolder(view)
    }

    class StargazerViewHolder(view: View): RecyclerView.ViewHolder(view){

        val user: TextView = itemView.username
        val avatar: CircleImageView = itemView.imgAvatar

        fun bind(item: Stargazer) {
            user.text = item.login
            avatar.setImageURI(Uri.parse(item.avatarUrl))
        }
    }
}