package com.touchin.data.repository.offers

import com.touchin.data.api.OffersApi
import com.touchin.data.repository.common.PreferencesStorage
import com.touchin.domain.repository.offers.OfferDomain
import com.touchin.domain.repository.offers.OffersRepository
import io.reactivex.processors.BehaviorProcessor

class OffersRepositoryImpl(
    private val api: OffersApi,
    private val preferencesStorage: PreferencesStorage
) : OffersRepository {

    private val favoriteOffersSetChanges: BehaviorProcessor<Set<String>> = BehaviorProcessor.create()

    override var favoriteOffers: Set<String>
        get() = preferencesStorage.favoriteOffersSet
        set(value) {
            preferencesStorage.favoriteOffersSet = value
            favoriteOffersSetChanges.onNext(value)
        }

    override fun addOfferToFav(offerID: String) {
        val changedSet = preferencesStorage.favoriteOffersSet.toMutableSet().plus(offerID)
        preferencesStorage.favoriteOffersSet = changedSet
    }

    override fun removeOfferFromFav(offerID: String) {
        val changedSet = preferencesStorage.favoriteOffersSet.toMutableSet().minus(offerID)
        preferencesStorage.favoriteOffersSet = changedSet
    }

    override suspend fun getOfferList(): List<OfferDomain> = api.getOfferList().map { it.toDomain() }
    override suspend fun getFavoriteOffersList(): List<OfferDomain> = api.getOfferList().map { it.toDomain() }.filter {
        preferencesStorage.favoriteOffersSet.contains(it.id)
    }
}
