package ru.ruwebnames.myfirstgitprogect.presentation

import androidx.lifecycle.ViewModel
import ru.ruwebnames.myfirstgitprogect.data.ShopListRepositoryImpl
import ru.ruwebnames.myfirstgitprogect.domain.AddShopItemUseCase
import ru.ruwebnames.myfirstgitprogect.domain.EditShopItemUseCase
import ru.ruwebnames.myfirstgitprogect.domain.GetShopItemUseCase
import ru.ruwebnames.myfirstgitprogect.domain.ShopItem

class ShopItemViewModel: ViewModel() {

    val repository = ShopListRepositoryImpl

    val getShopItemUseCase = GetShopItemUseCase(repository)
    val addShopItemUseCase = AddShopItemUseCase(repository)
    val editShopItemUseCase = EditShopItemUseCase(repository)

    fun getShopItem(shopItemId: Int) {
        val item = getShopItemUseCase.getShopItem(shopItemId)
    }

    fun addShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValidate = validateInput(name, count)
        if (fieldsValidate) {
            val shopItem = ShopItem(name, count, true)
            addShopItemUseCase.addShopItem(shopItem)
        }
    }

    fun editShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValidate = validateInput(name, count)
        if (fieldsValidate) {
            val shopItem = ShopItem(name, count, true)
            editShopItemUseCase.editShopItem(shopItem)
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            // Todo show error input name
            result = false
        }
        if (count <= 0) {
            // Todo show error input count
            result = false
        }
        return result
    }
}