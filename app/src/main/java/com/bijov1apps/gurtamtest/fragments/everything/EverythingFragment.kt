package com.bijov1apps.gurtamtest.fragments.everything

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import com.bijov1apps.domain.models.artilces.Articles
import com.bijov1apps.gurtamtest.R
import com.bijov1apps.gurtamtest.common.rv.ViewHolderFactoryFacade
import com.bijov1apps.gurtamtest.common.viewBinding
import com.bijov1apps.gurtamtest.databinding.ListFragmentBinding
import com.bijov1apps.gurtamtest.fragments.articles.rv.ArticlesViewHolder
import com.bijov1apps.gurtamtest.fragments.articles.rv.toRvItem
import com.bijov1apps.gurtamtest.fragments.everything.rv.NewsAdapter
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class EverythingFragment : Fragment(R.layout.list_fragment) {

    private val binding by viewBinding(ListFragmentBinding::bind)

    private val viewModel by viewModel<EverythingViewModel>()

    private val rvAdapter by lazy(LazyThreadSafetyMode.NONE) {
        NewsAdapter(
            ViewHolderFactoryFacade(
                arrayOf(
                    ArticlesViewHolder.Factory()
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
        }
    }
}
