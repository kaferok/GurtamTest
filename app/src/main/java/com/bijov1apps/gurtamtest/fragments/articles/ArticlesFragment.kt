package com.bijov1apps.gurtamtest.fragments.articles

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.paging.map
import com.bijov1apps.domain.models.artilces.Articles
import com.bijov1apps.gurtamtest.R
import com.bijov1apps.gurtamtest.common.ActivityBinder
import com.bijov1apps.gurtamtest.common.rv.ViewHolderFactoryFacade
import com.bijov1apps.gurtamtest.common.utils.navigate
import com.bijov1apps.gurtamtest.common.viewBinding
import com.bijov1apps.gurtamtest.databinding.ListFragmentBinding
import com.bijov1apps.gurtamtest.fragments.articles.rv.ArticlesViewHolder
import com.bijov1apps.gurtamtest.fragments.articles.rv.NewsAdapter
import com.bijov1apps.gurtamtest.fragments.articles.rv.toRvItem
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ArticlesFragment : Fragment(R.layout.list_fragment) {

    private val binding by viewBinding(ListFragmentBinding::bind)
    private val navArgs by navArgs<ArticlesFragmentArgs>()
    private val viewModel: ArticlesViewModel by viewModel {
        parametersOf(navArgs.sourceId)
    }

    private val rvAdapter by lazy(LazyThreadSafetyMode.NONE) {
        NewsAdapter(
            ViewHolderFactoryFacade(
                arrayOf(
                    ArticlesViewHolder.Factory(viewModel::onItemClicked)
                )
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = rvAdapter
        observeViewModel()
    }

    private fun observeViewModel() {
        with(viewModel) {
            lifecycleScope.launchWhenCreated {
                newsEverything.collectLatest {
                    rvAdapter.submitData(it.map(Articles::toRvItem))
                }
            }
            viewStateLiveData.observe(viewLifecycleOwner) { state ->
                updateHeader(state.header)
            }
            viewActionLiveData.observe(viewLifecycleOwner) { action ->
                when (action) {
                    is ArticlesViewAction.DetailArticles -> navigate(
                        ArticlesFragmentDirections.actionArticlesToDetailArticle(
                            action.url
                        )
                    )
                }
            }
        }
    }

    private fun updateHeader(header: String) {
        val currentActivity = (activity as ActivityBinder)
        currentActivity.toolbar(isVisible = true, isShowIcon = true, title = header)
    }
}