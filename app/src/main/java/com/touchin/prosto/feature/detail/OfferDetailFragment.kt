package com.touchin.prosto.feature.detail

import androidx.navigation.fragment.navArgs
import com.anadolstudio.core.viewbinding.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.touchin.prosto.R
import com.touchin.prosto.base.bottom.BaseContentBottom
import com.touchin.prosto.base.fragment.BaseContentFragment
import com.touchin.prosto.databinding.FragmentOfferDetailBinding
import com.touchin.prosto.di.viewmodel.assistedViewModel
import com.touchin.prosto.util.GradientDrawable

@Suppress("TooManyFunctions")
class OfferDetailFragment : BaseContentBottom<OfferDetailState, OfferDetailViewModel, OfferDetailController>(
    R.layout.fragment_offer_detail
) {

    private val binding by viewBinding { FragmentOfferDetailBinding.bind(it) }
    protected val args: OfferDetailFragmentArgs by navArgs()

    override fun createViewModelLazy() = assistedViewModel {
        getSharedComponent()
            .offerDetailViewModelFactory()
            .create(args.offer)
    }

    override fun render(state: OfferDetailState, controller: OfferDetailController) {
//        binding.headerView.favoriteButton.setOnClickListener { controller.onFavoriteChecked() }
        binding.headerView.initView(state.offer) { controller.onFavoriteChecked() }
        binding.mainInfo.initView(state.offer)
        binding.offerName.text = state.offer.name
        binding.longDesc.text = state.offer.longDescription
        val gradient = GradientDrawable(
            firstColor = state.offer.backgroundFirstColor,
            secondColor = state.offer.backgroundSecondColor,
        )
        binding.infoContainer.background = gradient
        binding.topLine.background = gradient
        binding.topLine.clipToOutline
        if (!state.offer.isActive) {
            binding.mainInfo.alpha = 0.5F
            val inactiveSnackbar = Snackbar.make(binding.topLine.rootView, "Эта акция больше неактивна", Snackbar.LENGTH_LONG)
            inactiveSnackbar.show()
        }

    }
}
