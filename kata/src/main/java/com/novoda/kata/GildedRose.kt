package com.novoda.kata

import java.util.*

object GildedRose {

    private var items: MutableList<Item> = Collections.emptyList()

    /**
     * @param args
     */
    @JvmStatic
    fun main(args: Array<String>) {

        println("OMGHAI!")

        items = ArrayList()
        items.add(Item("+5 Dexterity Vest", 10, 20))
        items.add(Item("Aged Brie", 2, 0))
        items.add(Item("Elixir of the Mongoose", 5, 7))
        items.add(Item("Sulfuras, Hand of Ragnaros", 0, 80))
        items.add(Item("Backstage passes to a TAFKAL80ETC concert", 15, 20))
        items.add(Item("Conjured Mana Cake", 3, 6))

        items = updateInventory(items)
        print(items)
    }

    fun updateInventory(item: Item) = updateInventory(mutableListOf(item))

    fun updateInventory(items: MutableList<Item>): MutableList<Item> {
        items.forEach { item ->
            val isNotLegendary = "Sulfuras, Hand of Ragnaros" != item.name
            val isBrie = "Aged Brie" == item.name
            val isBackstagePass = "Backstage passes to a TAFKAL80ETC concert" == item.name
            val isConjured = item.name.startsWith("Conjured")

            if (isNotLegendary) {
                item.sellIn = item.sellIn - 1
            }

            when {
                isBrie -> item.updateQuality(1)
                isBackstagePass -> when {
                    item.sellIn < 0 -> item.quality = 0
                    item.sellIn < 5 -> item.updateQuality(3)
                    item.sellIn < 10 -> item.updateQuality(2)
                    else -> item.updateQuality(1)
                }
                else -> handleConjuredAndLegendaryCases(isConjured, item, isNotLegendary)
            }


            if (item.sellIn < 0) {
                when {
                    isBrie -> item.updateQuality(1)
                    else -> handleConjuredAndLegendaryCases(isConjured, item, isNotLegendary)
                }
            }
        }
        return items
    }

    private fun handleConjuredAndLegendaryCases(
        isConjured: Boolean,
        item: Item,
        isNotLegendary: Boolean
    ) {
        if (isConjured) {
            item.updateQuality(-2)
        } else if (isNotLegendary) {
            item.updateQuality(-1)
        }
    }

    private fun Item.updateQuality(amountToModify: Int) {
        val result = quality + amountToModify
        quality = maxOf(0, minOf(50, result))
    }

}
