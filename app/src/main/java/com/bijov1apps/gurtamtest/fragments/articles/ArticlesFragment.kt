package com.bijov1apps.gurtamtest.fragments.articles

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bijov1apps.gurtamtest.R
import com.bijov1apps.gurtamtest.common.ActivityBinder
import com.bijov1apps.gurtamtest.common.rv.ViewHolderFactoryFacade
import com.bijov1apps.gurtamtest.common.viewBinding
import com.bijov1apps.gurtamtest.databinding.ListFragmentBinding
import com.bijov1apps.gurtamtest.fragments.articles.rv.ArticlesAdapter
import com.bijov1apps.gurtamtest.fragments.articles.rv.ArticlesViewHolder
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ArticlesFragment : Fragment(R.layout.list_fragment) {

    private val binding by viewBinding(ListFragmentBinding::bind)
    private val navArgs by navArgs<ArticlesFragmentArgs>()
    private val viewModel: ArticlesViewModel by viewModel {
        parametersOf(navArgs.sourceId)
    }

    private val rvAdapter by lazy(LazyThreadSafetyMode.NONE) {
        ArticlesAdapter(
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
            viewStateLiveData.observe(viewLifecycleOwner) { state ->
                rvAdapter.submitList(state.articles)
                updateHeader(state.header)
            }
            viewActionLiveData.observe(viewLifecycleOwner) { action ->
                when(action){
//                    is ArticlesViewAction.DetailArticles ->
                }
            }
        }
    }

    private fun updateHeader(header: String) {
        val currentActivity = (activity as ActivityBinder)
        currentActivity.toolbar(isVisible = true, isShowIcon = true, title = header)
    }
}