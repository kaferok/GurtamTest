package com.bijov1apps.gurtamtest.fragments.sources

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bijov1apps.gurtamtest.R
import com.bijov1apps.gurtamtest.common.rv.ViewHolderFactoryFacade
import com.bijov1apps.gurtamtest.common.viewBinding
import com.bijov1apps.gurtamtest.databinding.ListFragmentBinding
import com.bijov1apps.gurtamtest.fragments.sources.rv.SourcesAdapter
import com.bijov1apps.gurtamtest.fragments.sources.rv.SourcesItem
import com.bijov1apps.gurtamtest.fragments.sources.rv.SourcesViewHolder
import org.koin.androidx.viewmodel.ext.android.viewModel

class SourcesFragment : Fragment(R.layout.list_fragment) {

    private val binding by viewBinding(ListFragmentBinding::bind)

    private val viewModel: SourcesViewModel by viewModel()

    private val rvAdapter by lazy(LazyThreadSafetyMode.NONE) {
        SourcesAdapter(
            ViewHolderFactoryFacade(
                arrayOf(
                    SourcesViewHolder.Factory(viewModel::onItemClicked)
                )
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvSources.adapter = rvAdapter
        observeViewModel()
    }

    private fun observeViewModel() {
        with(viewModel) {
            viewStateLiveData.observe(viewLifecycleOwner) { state ->
                updateRv(state.sources)
            }
            viewActionLiveData.observe(viewLifecycleOwner) { action ->
                when (action) {
//                    is SourcesViewAction.OpenArticles -> navigate()
                }
            }
        }
    }

    private fun updateRv(items: List<SourcesItem>) {
        rvAdapter.submitList(items)
    }
}