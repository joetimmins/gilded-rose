package com.novoda.kata

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class GildedRoseTest {

    private fun createGildedRose() = GildedRose()

    @Test
    fun testUpdateQuantityWithNormalItem() {
        val input = listOf(Item("+5 Dexterity Vest", 10, 20))
        val expectedOutput = listOf(Item("+5 Dexterity Vest", 9, 19))

        val gildedRose = createGildedRose()
        val testObserver = gildedRose.inventory.test()
        gildedRose.updateInventoryy(input)

        testObserver
            .assertValueSequence(expectedOutput)
            .assertNotComplete()
    }

    @Test
    fun testUpdateQuantityWithAgedBrie() {
        val input = listOf(Item(name = "Aged Brie", sellIn = 10, quality = 15))
        val expectedOutput = listOf(Item(name = "Aged Brie", sellIn = 9, quality = 16))

        val actualOutput = createGildedRose().updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithAgedBrieAtMaxQuality() {
        val input = Item(name = "Aged Brie", sellIn = 10, quality = 50)
        val expectedOutput = listOf(Item(name = "Aged Brie", sellIn = 9, quality = 50))

        val actualOutput = createGildedRose().updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithAgedBrieOnSellDate() {
        val input = Item(name = "Aged Brie", sellIn = 0, quality = 15)
        val expectedOutput = listOf(Item(name = "Aged Brie", sellIn = -1, quality = 17))

        val actualOutput = createGildedRose().updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithAgedBrieOnSellDateNearMaxQuality() {
        val input = Item(name = "Aged Brie", sellIn = 0, quality = 49)
        val expectedOutput = listOf(Item(name = "Aged Brie", sellIn = -1, quality = 50))

        val actualOutput = createGildedRose().updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithAgedBrieOnSellDateAtMaxQuality() {
        val input = Item(name = "Aged Brie", sellIn = 0, quality = 50)
        val expectedOutput = listOf(Item(name = "Aged Brie", sellIn = -1, quality = 50))

        val actualOutput = createGildedRose().updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithAgedBrieAfterSellDate() {
        val input = Item(name = "Aged Brie", sellIn = -10, quality = 15)
        val expectedOutput = listOf(Item(name = "Aged Brie", sellIn = -11, quality = 17))

        val actualOutput = createGildedRose().updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithAgedBrieAfterSellDateNearMaxQuality() {
        val input = Item(name = "Aged Brie", sellIn = -10, quality = 49)
        val expectedOutput = listOf(Item(name = "Aged Brie", sellIn = -11, quality = 50))

        val actualOutput = createGildedRose().updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithAgedBrieAfterSellDateAtMaxQuality() {
        val input = Item(name = "Aged Brie", sellIn = -10, quality = 50)
        val expectedOutput = listOf(Item(name = "Aged Brie", sellIn = -11, quality = 50))

        val actualOutput = createGildedRose().updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }
    // MARK = Sulfuras

    @Test
    fun testUpdateQuantityWithSulfuras() {
        val input = listOf(Item(name = "Sulfuras, Hand of Ragnaros", sellIn = 10, quality = 80))
        val expectedOutput = listOf(Item(name = "Sulfuras, Hand of Ragnaros", sellIn = 10, quality = 80))

        val actualOutput = createGildedRose().updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithSulfurasOnSellDate() {
        val input = listOf(Item(name = "Sulfuras, Hand of Ragnaros", sellIn = 0, quality = 80))
        val expectedOutput = listOf(Item(name = "Sulfuras, Hand of Ragnaros", sellIn = 0, quality = 80))

        val actualOutput = createGildedRose().updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithSulfurasAfterSellDate() {
        val input = listOf(Item(name = "Sulfuras, Hand of Ragnaros", sellIn = -10, quality = 80))
        val expectedOutput = listOf(Item(name = "Sulfuras, Hand of Ragnaros", sellIn = -10, quality = 80))

        val actualOutput = createGildedRose().updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    // MARK = Backstage Passes

    @Test
    fun testUpdateQuantityWithPassesLongBeforeSellDate() {
        val input = listOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 11, quality = 5))
        val expectedOutput =
            listOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 10, quality = 6))

        val actualOutput = createGildedRose().updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithPassesLongBeforeSellDateAtMaxQuality() {
        val input = listOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 11, quality = 50))
        val expectedOutput =
            listOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 10, quality = 50))

        val actualOutput = createGildedRose().updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithPassesBeforeSellDateUpperBound() {
        val input = listOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 10, quality = 5))
        val expectedOutput =
            listOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 9, quality = 7))

        val actualOutput = createGildedRose().updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithPassesBeforeSellDateLowerBound() {
        val input = listOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 6, quality = 5))
        val expectedOutput =
            listOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 5, quality = 7))

        val actualOutput = createGildedRose().updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithPassesJustBeforeSellDateUpperBound() {
        val input = listOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 5, quality = 5))
        val expectedOutput =
            listOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 4, quality = 8))

        val actualOutput = createGildedRose().updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithPassesJustBeforeSellDateLowerBound() {
        val input = listOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 1, quality = 5))
        val expectedOutput =
            listOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 0, quality = 8))

        val actualOutput = createGildedRose().updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithPassesOnSellDate() {
        val input = listOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 0, quality = 5))
        val expectedOutput =
            listOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = -1, quality = 0))

        val actualOutput = createGildedRose().updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithPassesAfterSellDate() {
        val input = listOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = -1, quality = 5))
        val expectedOutput =
            listOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = -2, quality = 0))

        val actualOutput = createGildedRose().updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    // MARK = Conjured Items

    @Test
    fun testUpdateQuantityWithConjuredItem() {
        val input = listOf(Item(name = "Conjured Mana Cake", sellIn = 5, quality = 5))
        val expectedOutput = listOf(Item(name = "Conjured Mana Cake", sellIn = 4, quality = 3))

        val actualOutput = createGildedRose().updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithConjuredItemAtLowQuality() {
        val input = listOf(Item(name = "Conjured Mana Cake", sellIn = 5, quality = 1))
        val expectedOutput = listOf(Item(name = "Conjured Mana Cake", sellIn = 4, quality = 0))

        val actualOutput = createGildedRose().updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithConjuredItemOnSellDate() {
        val input = listOf(Item(name = "Conjured Mana Cake", sellIn = 0, quality = 5))
        val expectedOutput = listOf(Item(name = "Conjured Mana Cake", sellIn = -1, quality = 1))

        val actualOutput = createGildedRose().updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithConjuredItemAfterSellDate() {
        val input = listOf(Item(name = "Conjured Mana Cake", sellIn = -1, quality = 5))
        val expectedOutput = listOf(Item(name = "Conjured Mana Cake", sellIn = -2, quality = 1))

        val actualOutput = createGildedRose().updateInventory(input)

        assertThat(actualOutput).isEqualTo(expectedOutput)
    }
}
