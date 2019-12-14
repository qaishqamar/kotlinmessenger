package com.example.kotlinmessenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kotlinmessenger.RegisterLogin.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_new_messege.*
import kotlinx.android.synthetic.main.user_row_newmessege.view.*

class NewMessegeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_messege)
        supportActionBar?.title="Select New User"
      //  val adapter=GroupAdapter<GroupieViewHolder>()
       // adapter.add(UserItem())
       // recyvleview_newmessegge.adapter=adapter
        fetchUser()
    }
    fun fetchUser(){
     val ref= FirebaseDatabase.getInstance().getReference("/users")
        ref.addListenerForSingleValueEvent(object:ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                val adapter=GroupAdapter<GroupieViewHolder>()
                p0.children.forEach{
                    Log.d("newmessege",it.toString())
                    val user = it.getValue(User::class.java)
                    if (user!=null) {
                        adapter.add(UserItem(user))
                    }
                }
                //adapter.setOnItemClickListener{item,view->
                //    val intent=Intent(th)
               // }
                recyvleview_newmessegge.adapter=adapter
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

    }
}
class UserItem(val user: User):Item<GroupieViewHolder>(){
    override fun getLayout(): Int {
       return R.layout.user_row_newmessege
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
      viewHolder.itemView.username_row_newmessegw.text=user.username
      Picasso.get().load(user.profileImageUrl).into(viewHolder.itemView.imageView_newmessege)
    }
}

