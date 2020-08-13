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
        items.forEach { item ->
            val isNotLegendary = "Sulfuras, Hand of Ragnaros" != item.name
            val isBrie = "Aged Brie" == item.name
            val isBackstagePass = "Backstage passes to a TAFKAL80ETC concert" == item.name
            val isConjured = item.name.startsWith("Conjured")

            if (isNotLegendary) {
                item.sellIn = item.sellIn - 1
                when {
                    isBrie -> item.updateQuality(1)
                    isBackstagePass -> when {
                        item.sellIn < 0 -> item.quality = 0
                        item.sellIn < 5 -> item.updateQuality(3)
                        item.sellIn < 10 -> item.updateQuality(2)
                        else -> item.updateQuality(1)
                    }
                    else -> handleConjured(isConjured, item)
                }


                if (item.sellIn < 0) {
                    when {
                        isBrie -> item.updateQuality(1)
                        else -> handleConjured(isConjured, item)
                    }
                }
            }
        }
        return items
    }

    private fun handleConjured(
        isConjured: Boolean,
        item: Item
    ) {
        if (isConjured) {
            item.updateQuality(-2)
        } else {
            item.updateQuality(-1)
        }
    }

    private fun Item.updateQuality(amountToModify: Int) {
        val result = quality + amountToModify
        quality = maxOf(0, minOf(50, result))
    }
}
