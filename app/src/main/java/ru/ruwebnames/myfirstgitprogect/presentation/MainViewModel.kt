package ru.ruwebnames.myfirstgitprogect.presentation

import androidx.lifecycle.ViewModel
import ru.ruwebnames.myfirstgitprogect.data.ShopListRepositoryImpl
import ru.ruwebnames.myfirstgitprogect.domain.DeleteShopItemUseCase
import ru.ruwebnames.myfirstgitprogect.domain.EditShopItemUseCase
import ru.ruwebnames.myfirstgitprogect.domain.GetShopListUseCase
import ru.ruwebnames.myfirstgitprogect.domain.ShopItem

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }
}
