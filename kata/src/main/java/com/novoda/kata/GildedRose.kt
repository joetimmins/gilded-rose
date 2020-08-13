package com.novoda.kata

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

class GildedRose {
    companion object {

        private val gildedRose = GildedRose()

        /**
         * @param args
         */
        @JvmStatic
        fun main(args: Array<String>) {

            println("OMGHAI!")

            val items = listOf(
                Item("+5 Dexterity Vest", 10, 20),
                Item("Aged Brie", 2, 0),
                Item("Elixir of the Mongoose", 5, 7),
                Item("Sulfuras, Hand of Ragnaros", 0, 80),
                Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                Item("Conjured Mana Cake", 3, 6)
            )

            gildedRose.inventory
                .subscribe { println(it) }

            gildedRose.updateInventoryy(items)
        }
    }

    private val _inventory = PublishSubject.create<Item>()
    val inventory = _inventory as Observable<Item>

    fun updateInventoryy(item: Item) = updateInventoryy(listOf(item))

    fun updateInventoryy(items: List<Item>) {
        val newItems = updateInventory(items)
        newItems.forEach { _inventory.onNext(it) }
    }

    private fun updateInventory(items: List<Item>): List<Item> {
        return items.map { item ->
            val isNotLegendary = "Sulfuras, Hand of Ragnaros" != item.name
            val isBrie = "Aged Brie" == item.name
            val isBackstagePass = "Backstage passes to a TAFKAL80ETC concert" == item.name
            val isConjured = item.name.startsWith("Conjured")

            if (isNotLegendary) {
                val itemWithUpdatedSellByDate = item.copy(sellIn = item.sellIn - 1)
                val itemWithUpdatedQuality = when {
                    isBrie -> itemWithUpdatedSellByDate.updateQuality(1)
                    isBackstagePass -> when {
                        itemWithUpdatedSellByDate.sellIn < 0 -> itemWithUpdatedSellByDate.copy(quality = 0)
                        itemWithUpdatedSellByDate.sellIn < 5 -> itemWithUpdatedSellByDate.updateQuality(3)
                        itemWithUpdatedSellByDate.sellIn < 10 -> itemWithUpdatedSellByDate.updateQuality(2)
                        else -> itemWithUpdatedSellByDate.updateQuality(1)
                    }
                    else -> handleConjured(isConjured, itemWithUpdatedSellByDate)
                }

                val itemWithFinalQualityUpdate =
                    if (itemWithUpdatedQuality.sellIn < 0) {
                        when {
                            isBrie -> itemWithUpdatedQuality.updateQuality(1)
                            else -> handleConjured(isConjured, itemWithUpdatedQuality)
                        }
                    } else itemWithUpdatedQuality

                itemWithFinalQualityUpdate
            } else item
        }
    }

    private fun handleConjured(isConjured: Boolean, item: Item) =
        if (isConjured) item.updateQuality(-2)
        else item.updateQuality(-1)

    private fun Item.updateQuality(amountToModify: Int): Item {
        val result = quality + amountToModify
        val updatedQuality = maxOf(0, minOf(50, result))
        return copy(quality = updatedQuality)
    }
}
