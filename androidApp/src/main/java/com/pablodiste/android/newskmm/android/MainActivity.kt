package com.pablodiste.android.newskmm.android

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pablodiste.android.newskmm.Greeting
import com.pablodiste.android.newskmm.domain.Article
import com.pablodiste.android.newskmm.domain.NewsResponse
import com.pablodiste.android.newskmm.domain.Response

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { 
            Home()
        }
    }

    @Composable
    private fun Home(viewModel: NewsViewModel = viewModel()) {
        val news: State<Response<NewsResponse>> = viewModel.news.collectAsState()
        Log.d(TAG, "Recomposed")
        if (news.value is Response.Success) {
            val successResponse = news.value as Response.Success<NewsResponse>
            NewsList(successResponse.data.articles)
        }
    }

    @Composable
    private fun NewsList(news: List<Article>) {
        LazyColumn {
            items(news) {
                Row(modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp)) {
                    Text(text = it.title!!)
                }
            }
        }
    }


}
