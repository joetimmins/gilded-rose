package com.novoda.kata

import java.util.*

object GildedRose {

    private var items: MutableList<Item>? = null

    /**
     * @param args
     */
    @JvmStatic
    fun main(args: Array<String>) {

        println("OMGHAI!")

        items = ArrayList()
        items!!.add(Item("+5 Dexterity Vest", 10, 20))
        items!!.add(Item("Aged Brie", 2, 0))
        items!!.add(Item("Elixir of the Mongoose", 5, 7))
        items!!.add(Item("Sulfuras, Hand of Ragnaros", 0, 80))
        items!!.add(Item("Backstage passes to a TAFKAL80ETC concert", 15, 20))
        items!!.add(Item("Conjured Mana Cake", 3, 6))

        items = updateQuality(items)
        print(items)
    }

    fun updateQuality(item: Item) = updateQuality(mutableListOf(item))

    fun updateQuality(items: MutableList<Item>?): MutableList<Item>? {
        for (i in items!!.indices) {
            if ("Aged Brie" != items!![i].name && "Backstage passes to a TAFKAL80ETC concert" != items!![i].name) {
                if (items!![i].quality > 0) {
                    if ("Sulfuras, Hand of Ragnaros" != items!![i].name) {
                        items!![i].quality = items!![i].quality - 1
                    }
                }
            } else {
                if (items!![i].quality < 50) {
                    items!![i].quality = items!![i].quality + 1

                    if ("Backstage passes to a TAFKAL80ETC concert" == items!![i].name) {
                        if (items!![i].sellIn < 11) {
                            if (items!![i].quality < 50) {
                                items!![i].quality = items!![i].quality + 1
                            }
                        }

                        if (items!![i].sellIn < 6) {
                            if (items!![i].quality < 50) {
                                items!![i].quality = items!![i].quality + 1
                            }
                        }
                    }
                }
            }

            if ("Sulfuras, Hand of Ragnaros" != items!![i].name) {
                items!![i].sellIn = items!![i].sellIn - 1
            }

            if (items!![i].sellIn < 0) {
                if ("Aged Brie" != items!![i].name) {
                    if ("Backstage passes to a TAFKAL80ETC concert" != items!![i].name) {
                        if (items!![i].quality > 0) {
                            if ("Sulfuras, Hand of Ragnaros" != items!![i].name) {
                                items!![i].quality = items!![i].quality - 1
                            }
                        }
                    } else {
                        items!![i].quality = items!![i].quality - items!![i].quality
                    }
                } else {
                    if (items!![i].quality < 50) {
                        items!![i].quality = items!![i].quality + 1
                    }
                }
            }
        }
        return items
    }

}
