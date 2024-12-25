package com.example.moviejetpackcompose.ui.theme.ui.theme.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.moviejetpackcompose.model.netWork.ResponseState
import com.example.moviejetpackcompose.model.pojo.MovieDataFav
import com.example.moviejetpackcompose.model.pojo.RepoMovieImp
import com.example.moviejetpackcompose.model.pojo.TrendingPojo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlin.math.log

class HomeViewModel(private val repo: RepoMovieImp):ViewModel() {
private val _state= MutableStateFlow<ResponseState<TrendingPojo>>(ResponseState.Loading)
    val state=_state.asStateFlow()
    fun getTrending(){
    viewModelScope.launch(Dispatchers.IO){
        repo.getTrending()?.catch { error->_state.value= ResponseState.Error(error) }
            ?.collect{data->_state.value= ResponseState.Success(data)}
    }
    }
    private val _topRated=MutableStateFlow<ResponseState<TrendingPojo>>(ResponseState.Loading)
    val topRated=_topRated.asStateFlow()
    fun getTopRated(){
        viewModelScope.launch(Dispatchers.IO){
            repo.getTopRating()?.catch { error->_topRated.value= ResponseState.Error(error) }
                ?.collect{data->_topRated.value= ResponseState.Success(data)}
        }
    }
    private val _popular=MutableStateFlow<ResponseState<TrendingPojo>>(ResponseState.Loading)
    val popular=_popular.asStateFlow()
    fun getPopularData(){
        viewModelScope.launch(Dispatchers.IO){
            repo.getPopular()?.catch {error->_popular.value= ResponseState.Error(error)  }
                ?.collect{data->_popular.value= ResponseState.Success(data)}
        }
    }
    private val _dicover=MutableStateFlow<ResponseState<TrendingPojo>>(ResponseState.Loading)
    val discover=_dicover.asStateFlow()
    fun getDiscoverData(){
        viewModelScope.launch(Dispatchers.Main){
            repo.getDiscover()?.catch { error->_dicover.value= ResponseState.Error(error) }
                ?.collect{data->_dicover.value= ResponseState.Success(data)}
        }
    }
    private val _search= MutableStateFlow<ResponseState<TrendingPojo>>(ResponseState.Loading)
    val search =_search.asStateFlow()
        fun getSearch(search:String){
            viewModelScope.launch(Dispatchers.Main){
                repo.getSearch(search)?.catch { error->_search.value= ResponseState.Error(error) }
                    ?.collect{data->_search.value= ResponseState.Success(data)}
            }
        }
    private val _fav=MutableStateFlow<ResponseState<List<MovieDataFav>>>(ResponseState.Loading)
    val fav=_fav.asStateFlow()
    fun getFAv(){
        viewModelScope.launch(Dispatchers.IO){
            repo.getAllFavMovie()?.catch { error-> _fav.value=ResponseState.Error(error) }
                ?.collect{data->
                    if (data.isEmpty()) {
                        _fav.value = ResponseState.Success(emptyList()) // التعامل مع الحالة الفارغة
                    } else {
                        _fav.value = ResponseState.Success(data)
                    }
                    Log.d("favourite", "getFAv: ${data.getOrNull(0)?.overView ?: "No data"}")
                }

        }
    }


    fun insertMovieInFav(movie: MovieDataFav){
        viewModelScope.launch(Dispatchers.IO){
            repo.insertMovieToDav(movie)

        }
    }
    fun deletefromDB(movie: MovieDataFav){



        viewModelScope.launch (Dispatchers.IO){
            try {


            repo.deleteMovieFromFav(movie)
            getFAv()
        }catch (e:Exception){
                Log.e("DeleteError", "Error deleting movie: $e")
        }
        }
    }



}