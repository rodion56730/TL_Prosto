package com.touchin.domain.repository.offers

interface OffersRepository {
    var favoriteOffers: Set<String>

    fun addOfferToFav(offerID : String)
    fun removeOfferFromFav(offerID: String)
    suspend fun getOfferList(): List<OfferDomain>
    suspend fun getFavoriteOffersList(): List<OfferDomain>
}
