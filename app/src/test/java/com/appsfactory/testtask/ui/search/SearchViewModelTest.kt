package com.appsfactory.testtask.ui.search

import androidx.compose.ui.text.input.TextFieldValue
import com.appsfactory.testtask.BaseTest
import com.appsfactory.testtask.data.repository.ArtistsRepository
import com.appsfactory.testtask.domain.artist.ArtistsInteractor
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
internal class SearchViewModelTest : BaseTest() {

    @Mock
    private lateinit var artistsRepository: ArtistsRepository

    private lateinit var viewModel: SearchViewModel

    @Before
    fun setup() {
        viewModel = SearchViewModel(artistsInteractor = ArtistsInteractor(artistsRepository))
    }

    @Test
    fun `Test init loading correctly`() {
        assert(
            viewModel.searchState.text.isEmpty()
        )

        viewModel.onSearchChanged(TextFieldValue("123"))
        viewModel.onStartSearchClicked()

        // TODO continue
    }
}