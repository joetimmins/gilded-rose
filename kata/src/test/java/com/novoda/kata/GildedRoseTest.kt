package com.novoda.kata

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class GildedRoseTest {

    @Test
    fun testUpdateQuantityWithNormalItem() {
        val input = mutableListOf(Item("+5 Dexterity Vest", 10, 20))
        val expectedOutput = mutableListOf(Item("+5 Dexterity Vest", 9, 19))
        val actualOutput = GildedRose.updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithAgedBrie() {
        val input = mutableListOf(Item(name = "Aged Brie", sellIn = 10, quality = 15))
        val expectedOutput = mutableListOf(Item(name = "Aged Brie", sellIn = 9, quality = 16))
        val actualOutput = GildedRose.updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithAgedBrieAtMaxQuality() {
        val input = Item(name = "Aged Brie", sellIn = 10, quality = 50)
        val expectedOutput = mutableListOf(Item(name = "Aged Brie", sellIn = 9, quality = 50))
        val actualOutput = GildedRose.updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithAgedBrieOnSellDate() {
        val input = Item(name = "Aged Brie", sellIn = 0, quality = 15)
        val expectedOutput = mutableListOf(Item(name = "Aged Brie", sellIn = -1, quality = 17))
        val actualOutput = GildedRose.updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithAgedBrieOnSellDateNearMaxQuality() {
        val input = Item(name = "Aged Brie", sellIn = 0, quality = 49)
        val expectedOutput = mutableListOf(Item(name = "Aged Brie", sellIn = -1, quality = 50))
        val actualOutput = GildedRose.updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithAgedBrieOnSellDateAtMaxQuality() {
        val input = Item(name = "Aged Brie", sellIn = 0, quality = 50)
        val expectedOutput = mutableListOf(Item(name = "Aged Brie", sellIn = -1, quality = 50))
        val actualOutput = GildedRose.updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithAgedBrieAfterSellDate() {
        val input = Item(name = "Aged Brie", sellIn = -10, quality = 15)
        val expectedOutput = mutableListOf(Item(name = "Aged Brie", sellIn = -11, quality = 17))
        val actualOutput = GildedRose.updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithAgedBrieAfterSellDateNearMaxQuality() {
        val input = Item(name = "Aged Brie", sellIn = -10, quality = 49)
        val expectedOutput = mutableListOf(Item(name = "Aged Brie", sellIn = -11, quality = 50))
        val actualOutput = GildedRose.updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithAgedBrieAfterSellDateAtMaxQuality() {
        val input = Item(name = "Aged Brie", sellIn = -10, quality = 50)
        val expectedOutput = mutableListOf(Item(name = "Aged Brie", sellIn = -11, quality = 50))
        val actualOutput = GildedRose.updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }
    // MARK = Sulfuras

    @Test
    fun testUpdateQuantityWithSulfuras() {
        val input = mutableListOf(Item(name = "Sulfuras, Hand of Ragnaros", sellIn = 10, quality = 80))
        val expectedOutput = mutableListOf(Item(name = "Sulfuras, Hand of Ragnaros", sellIn = 10, quality = 80))
        val actualOutput = GildedRose.updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithSulfurasOnSellDate() {
        val input = mutableListOf(Item(name = "Sulfuras, Hand of Ragnaros", sellIn = 0, quality = 80))
        val expectedOutput = mutableListOf(Item(name = "Sulfuras, Hand of Ragnaros", sellIn = 0, quality = 80))
        val actualOutput = GildedRose.updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithSulfurasAfterSellDate() {
        val input = mutableListOf(Item(name = "Sulfuras, Hand of Ragnaros", sellIn = -10, quality = 80))
        val expectedOutput = mutableListOf(Item(name = "Sulfuras, Hand of Ragnaros", sellIn = -10, quality = 80))
        val actualOutput = GildedRose.updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    // MARK = Backstage Passes

    @Test
    fun testUpdateQuantityWithPassesLongBeforeSellDate() {
        val input = mutableListOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 11, quality = 5))
        val expectedOutput =
            mutableListOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 10, quality = 6))
        val actualOutput = GildedRose.updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithPassesLongBeforeSellDateAtMaxQuality() {
        val input = mutableListOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 11, quality = 50))
        val expectedOutput =
            mutableListOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 10, quality = 50))
        val actualOutput = GildedRose.updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithPassesBeforeSellDateUpperBound() {
        val input = mutableListOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 10, quality = 5))
        val expectedOutput =
            mutableListOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 9, quality = 7))
        val actualOutput = GildedRose.updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithPassesBeforeSellDateLowerBound() {
        val input = mutableListOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 6, quality = 5))
        val expectedOutput =
            mutableListOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 5, quality = 7))
        val actualOutput = GildedRose.updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithPassesJustBeforeSellDateUpperBound() {
        val input = mutableListOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 5, quality = 5))
        val expectedOutput =
            mutableListOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 4, quality = 8))
        val actualOutput = GildedRose.updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithPassesJustBeforeSellDateLowerBound() {
        val input = mutableListOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 1, quality = 5))
        val expectedOutput =
            mutableListOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 0, quality = 8))
        val actualOutput = GildedRose.updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithPassesOnSellDate() {
        val input = mutableListOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 0, quality = 5))
        val expectedOutput =
            mutableListOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = -1, quality = 0))
        val actualOutput = GildedRose.updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithPassesAfterSellDate() {
        val input = mutableListOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = -1, quality = 5))
        val expectedOutput =
            mutableListOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = -2, quality = 0))
        val actualOutput = GildedRose.updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    // MARK = Conjured Items

    @Test
    fun testUpdateQuantityWithConjuredItem() {
        val input = mutableListOf(Item(name = "Conjured Mana Cake", sellIn = 5, quality = 5))
        val expectedOutput = mutableListOf(Item(name = "Conjured Mana Cake", sellIn = 4, quality = 3))
        val actualOutput = GildedRose.updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithConjuredItemAtLowQuality() {
        val input = mutableListOf(Item(name = "Conjured Mana Cake", sellIn = 5, quality = 1))
        val expectedOutput = mutableListOf(Item(name = "Conjured Mana Cake", sellIn = 4, quality = 0))
        val actualOutput = GildedRose.updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithConjuredItemOnSellDate() {
        val input = mutableListOf(Item(name = "Conjured Mana Cake", sellIn = 0, quality = 5))
        val expectedOutput = mutableListOf(Item(name = "Conjured Mana Cake", sellIn = -1, quality = 1))
        val actualOutput = GildedRose.updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithConjuredItemAfterSellDate() {
        val input = mutableListOf(Item(name = "Conjured Mana Cake", sellIn = -1, quality = 5))
        val expectedOutput = mutableListOf(Item(name = "Conjured Mana Cake", sellIn = -2, quality = 1))
        val actualOutput = GildedRose.updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }
}
