package com.touchin.prosto.feature.detail

import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.navArgs
import com.anadolstudio.core.viewbinding.viewBinding
import com.touchin.prosto.R
import com.touchin.prosto.base.bottom.BaseContentBottom
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
        binding.headerView.initView(state.offer) {
            controller.onFavoriteChecked()
            setFragmentResult(
                "requestKey",
                bundleOf("extraKey" to state.offer)
            )
        }
        binding.mainInfo.initView(state.offer)
        binding.offerName.text = state.offer.name
        binding.longDesc.text = state.offer.longDescription
        binding.gradientBackground.background = GradientDrawable(
            firstColor = state.offer.backgroundFirstColor,
            secondColor = state.offer.backgroundSecondColor,
        )
        binding.mainInfo.alpha = if (!state.offer.isActive) 1.0F else 0.5F

    }
}
