package com.bijov1apps.gurtamtest.fragments.detailArticles

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bijov1apps.domain.models.artilces.Articles
import com.bijov1apps.domain.utils.DateTimeUtils
import com.bijov1apps.gurtamtest.R
import com.bijov1apps.gurtamtest.common.extensions.fromHtml
import com.bijov1apps.gurtamtest.common.viewBinding
import com.bijov1apps.gurtamtest.databinding.DetailArticleFragmentBinding
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailArticleFragment : Fragment(R.layout.detail_article_fragment) {

    private val binding by viewBinding(DetailArticleFragmentBinding::bind)

    private val navArgs by navArgs<DetailArticleFragmentArgs>()

    private val viewModel: DetailArticleViewModel by viewModel {
        parametersOf(navArgs.url)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        with(viewModel) {
            viewStateLiveData.observe(viewLifecycleOwner) { state ->
                updateData(state.article!!)
            }
        }
    }

    private fun updateData(item: Articles) {
        with(binding) {
            tvTitle.text = item.title
            tvContent.fromHtml(item.content.orEmpty())
            val formattedDate = DateTimeUtils.formatPublishedDate(item.publishedAt)
            tvDate.text = getString(R.string.article_from, formattedDate)
            tvOriginalSource.text = getString(R.string.original_source, item.url)
            Glide.with(root)
                .load(item.urlToImage)
                .into(imgArticle)
        }
    }
}