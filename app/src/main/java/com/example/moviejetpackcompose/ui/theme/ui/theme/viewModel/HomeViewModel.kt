package com.example.moviejetpackcompose.ui.theme.ui.theme.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviejetpackcompose.model.ResponseState
import com.example.moviejetpackcompose.pojo.RepoMovieImp
import com.example.moviejetpackcompose.pojo.TrendingPojo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repo:RepoMovieImp):ViewModel() {
private val _state= MutableStateFlow<ResponseState<TrendingPojo>>(ResponseState.Loading)
    val state=_state.asStateFlow()
    fun getTrending(){
    viewModelScope.launch(Dispatchers.IO){
        repo.getTrending()?.catch { error->_state.value=ResponseState.Error(error) }
            ?.collect{data->_state.value=ResponseState.Success(data)}
    }
    }
    private val _topRated=MutableStateFlow<ResponseState<TrendingPojo>>(ResponseState.Loading)
    val topRated=_topRated.asStateFlow()
    fun getTopRated(){
        viewModelScope.launch(Dispatchers.IO){
            repo.getTopRating()?.catch { error->_topRated.value=ResponseState.Error(error) }
                ?.collect{data->_topRated.value=ResponseState.Success(data)}
        }
    }
    private val _popular=MutableStateFlow<ResponseState<TrendingPojo>>(ResponseState.Loading)
    val popular=_popular.asStateFlow()
    fun getPopularData(){
        viewModelScope.launch(Dispatchers.IO){
            repo.getPopular()?.catch {error->_popular.value=ResponseState.Error(error)  }
                ?.collect{data->_popular.value=ResponseState.Success(data)}
        }
    }
    private val _dicover=MutableStateFlow<ResponseState<TrendingPojo>>(ResponseState.Loading)
    val discover=_dicover.asStateFlow()
    fun getDiscoverData(){
        viewModelScope.launch(Dispatchers.Main){
            repo.getDiscover()?.catch { error->_dicover.value=ResponseState.Error(error) }
                ?.collect{data->_dicover.value=ResponseState.Success(data)}
        }
    }
}