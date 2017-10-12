package com.knz21.githubrepositorylist.view.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.knz21.githubrepositorylist.R
import com.knz21.githubrepositorylist.databinding.RepositoryItemBinding
import com.knz21.githubrepositorylist.domain.entity.GitHubRepository

class RepositoryListAdapter : BaseAdapter() {
    private var repositories = listOf<GitHubRepository>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View =
            getBinding(convertView, parent).apply { setValues(getItem(position)) }.let { it.root.apply { tag = it } }

    private fun getBinding(convertView: View?, parent: ViewGroup): RepositoryItemBinding =
            convertView?.run { tag as RepositoryItemBinding }
                    ?: RepositoryItemBinding.bind(View.inflate(parent.context, R.layout.repository_item, null))

    private fun RepositoryItemBinding.setValues(repository: GitHubRepository) {
        repositoryId.text = repository.id
        repositoryName.text = repository.name
    }

    override fun getItem(position: Int): GitHubRepository = repositories[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = repositories.size

    fun update(newRepositories: List<GitHubRepository>) {
        repositories = newRepositories
        notifyDataSetChanged()
    }
}