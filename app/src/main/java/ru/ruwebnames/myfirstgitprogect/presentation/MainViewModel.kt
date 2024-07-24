package ru.ruwebnames.myfirstgitprogect.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.ruwebnames.myfirstgitprogect.data.ShopListRepositoryImpl
import ru.ruwebnames.myfirstgitprogect.domain.DeleteShopItemUseCase
import ru.ruwebnames.myfirstgitprogect.domain.EditShopItemUseCase
import ru.ruwebnames.myfirstgitprogect.domain.GetShopListUseCase
import ru.ruwebnames.myfirstgitprogect.domain.ShopItem

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)

    val shopList = MutableLiveData<List<ShopItem>>()

    fun getShopList() {
        val list = getShopListUseCase.getShopList()
        shopList.value = list
    }

//    fun editShopItem(shopItem: ShopItem) {
//        val oldItem = getShopItem
//    }

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
        getShopList()
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
        getShopList()
    }
}