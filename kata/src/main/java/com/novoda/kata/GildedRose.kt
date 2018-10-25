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
            val isNotBrie = "Aged Brie" != item.name
            val isNotBackstagePass = "Backstage passes to a TAFKAL80ETC concert" != item.name

            if (isNotBrie && isNotBackstagePass) {
                if (item.quality > 0) {
                    if (isNotLegendary) {
                        updateQuality(item, -1)
                    }
                    if (item.name.startsWith("Conjured")) {
                        updateQuality(item, -1)
                    }
                }
            } else {
                updateQuality(item, 1)

                if ("Backstage passes to a TAFKAL80ETC concert" == item.name) {
                    if (item.sellIn < 11) {
                        updateQuality(item, 1)
                    }

                    if (item.sellIn < 6) {
                        updateQuality(item, 1)
                    }
                }
            }

            if (isNotLegendary) {
                item.sellIn = item.sellIn - 1
            }

            if (item.sellIn < 0) {
                if (isNotBrie) {
                    if (isNotBackstagePass) {
                        if (isNotLegendary) {
                            updateQuality(item, -1)
                        }
                        if (item.name.startsWith("Conjured")) {
                            updateQuality(item, -1)
                        }
                    } else {
                        item.quality = 0
                    }
                } else {
                    updateQuality(item, 1)
                }
            }
        }
        return items
    }

    fun updateQuality(item: Item, amountToModify: Int) {
        val result = item.quality + amountToModify
        item.quality = maxOf(0, minOf(50, result))
    }

}
