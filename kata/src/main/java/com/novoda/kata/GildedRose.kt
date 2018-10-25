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

        items = updateQuality(items)
        print(items)
    }

    fun updateQuality(item: Item) = updateQuality(mutableListOf(item))

    fun updateQuality(items: MutableList<Item>): MutableList<Item> {
        for (i in items.indices) {
            val currentItem = items[i]
            if ("Aged Brie" != currentItem.name && "Backstage passes to a TAFKAL80ETC concert" != currentItem.name) {
                if (currentItem.quality > 0) {
                    if ("Sulfuras, Hand of Ragnaros" != currentItem.name) {
                        currentItem.quality = currentItem.quality - 1
                    }
                    if (currentItem.name.startsWith("Conjured")) {
                        currentItem.quality = currentItem.quality - 1
                    }
                }
            } else {
                if (currentItem.quality < 50) {
                    currentItem.quality = currentItem.quality + 1

                    if ("Backstage passes to a TAFKAL80ETC concert" == currentItem.name) {
                        if (currentItem.sellIn < 11) {
                            if (currentItem.quality < 50) {
                                currentItem.quality = currentItem.quality + 1
                            }
                        }

                        if (currentItem.sellIn < 6) {
                            if (currentItem.quality < 50) {
                                currentItem.quality = currentItem.quality + 1
                            }
                        }
                    }
                }
            }

            if ("Sulfuras, Hand of Ragnaros" != currentItem.name) {
                currentItem.sellIn = currentItem.sellIn - 1
            }

            if (currentItem.sellIn < 0) {
                if ("Aged Brie" != currentItem.name) {
                    if ("Backstage passes to a TAFKAL80ETC concert" != currentItem.name) {
                        if (currentItem.quality > 0) {
                            if ("Sulfuras, Hand of Ragnaros" != currentItem.name) {
                                currentItem.quality = currentItem.quality - 1
                            }
                            if (currentItem.name.startsWith("Conjured")) {
                                currentItem.quality = currentItem.quality - 1
                            }
                        }
                    } else {
                        currentItem.quality = currentItem.quality - currentItem.quality
                    }
                } else {
                    if (currentItem.quality < 50) {
                        currentItem.quality = currentItem.quality + 1
                    }
                }
            }
            if (currentItem.quality < 0) {
                currentItem.quality = 0
            }
        }
        return items
    }

}
