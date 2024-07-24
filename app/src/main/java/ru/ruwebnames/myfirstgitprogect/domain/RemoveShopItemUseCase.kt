package ru.ruwebnames.myfirstgitprogect.domain

class RemoveShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun deleteShopItem(shopItem: ShopItem) {
        shopListRepository.removeShopItem(shopItem)
    }
}