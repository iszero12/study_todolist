package com.iszero.android.study_todolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val todo = arrayListOf<String>()
        val recyclerView = findViewById<RecyclerView>(R.id.list)
        recyclerView.layoutManager = LinearLayoutManager(this)  // 레이아웃 매니저 설정
        val adapter = TodoAdapter(todo)
        recyclerView.adapter = adapter
        val btn = findViewById<Button>(R.id.addBtn)
        val editText = findViewById<EditText>(R.id.editTxt)
        btn.setOnClickListener {
            todo.add(editText.text.toString())
            adapter.notifyDataSetChanged()
        }
    }
}

class TodoAdapter(private val itemList: List<String>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTextView: TextView = itemView.findViewById(R.id.item_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.todo_layout, parent, false)
        return TodoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.itemTextView.text = itemList[position]
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
