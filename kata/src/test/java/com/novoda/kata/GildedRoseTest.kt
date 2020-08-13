package com.novoda.kata

import org.junit.Test

class GildedRoseTest {

    private fun createGildedRose() = GildedRose()

    @Test
    fun testUpdateQuantityWithNormalItem() {
        val input = listOf(Item("+5 Dexterity Vest", 10, 20))
        val expectedOutput = listOf(Item("+5 Dexterity Vest", 9, 19))

        testGildedRoseItems(input, expectedOutput)
    }


    @Test
    fun testUpdateQuantityWithAgedBrie() {
        val input = listOf(Item(name = "Aged Brie", sellIn = 10, quality = 15))
        val expectedOutput = listOf(Item(name = "Aged Brie", sellIn = 9, quality = 16))

        testGildedRoseItems(input, expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithAgedBrieAtMaxQuality() {
        val input = Item(name = "Aged Brie", sellIn = 10, quality = 50)
        val expectedOutput = listOf(Item(name = "Aged Brie", sellIn = 9, quality = 50))

        testSingleGildedRoseItem(input, expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithAgedBrieOnSellDate() {
        val input = Item(name = "Aged Brie", sellIn = 0, quality = 15)
        val expectedOutput = listOf(Item(name = "Aged Brie", sellIn = -1, quality = 17))

        testSingleGildedRoseItem(input, expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithAgedBrieOnSellDateNearMaxQuality() {
        val input = Item(name = "Aged Brie", sellIn = 0, quality = 49)
        val expectedOutput = listOf(Item(name = "Aged Brie", sellIn = -1, quality = 50))

        testSingleGildedRoseItem(input, expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithAgedBrieOnSellDateAtMaxQuality() {
        val input = Item(name = "Aged Brie", sellIn = 0, quality = 50)
        val expectedOutput = listOf(Item(name = "Aged Brie", sellIn = -1, quality = 50))

        testSingleGildedRoseItem(input, expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithAgedBrieAfterSellDate() {
        val input = Item(name = "Aged Brie", sellIn = -10, quality = 15)
        val expectedOutput = listOf(Item(name = "Aged Brie", sellIn = -11, quality = 17))

        testSingleGildedRoseItem(input, expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithAgedBrieAfterSellDateNearMaxQuality() {
        val input = Item(name = "Aged Brie", sellIn = -10, quality = 49)
        val expectedOutput = listOf(Item(name = "Aged Brie", sellIn = -11, quality = 50))

        testSingleGildedRoseItem(input, expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithAgedBrieAfterSellDateAtMaxQuality() {
        val input = Item(name = "Aged Brie", sellIn = -10, quality = 50)
        val expectedOutput = listOf(Item(name = "Aged Brie", sellIn = -11, quality = 50))

        testSingleGildedRoseItem(input, expectedOutput)
    }

    // MARK = Sulfuras

    @Test
    fun testUpdateQuantityWithSulfuras() {
        val input = listOf(Item(name = "Sulfuras, Hand of Ragnaros", sellIn = 10, quality = 80))
        val expectedOutput = listOf(Item(name = "Sulfuras, Hand of Ragnaros", sellIn = 10, quality = 80))

        testGildedRoseItems(input, expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithSulfurasOnSellDate() {
        val input = listOf(Item(name = "Sulfuras, Hand of Ragnaros", sellIn = 0, quality = 80))
        val expectedOutput = listOf(Item(name = "Sulfuras, Hand of Ragnaros", sellIn = 0, quality = 80))

        testGildedRoseItems(input, expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithSulfurasAfterSellDate() {
        val input = listOf(Item(name = "Sulfuras, Hand of Ragnaros", sellIn = -10, quality = 80))
        val expectedOutput = listOf(Item(name = "Sulfuras, Hand of Ragnaros", sellIn = -10, quality = 80))

        testGildedRoseItems(input, expectedOutput)
    }

    // MARK = Backstage Passes

    @Test
    fun testUpdateQuantityWithPassesLongBeforeSellDate() {
        val input = listOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 11, quality = 5))
        val expectedOutput =
            listOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 10, quality = 6))

        testGildedRoseItems(input, expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithPassesLongBeforeSellDateAtMaxQuality() {
        val input = listOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 11, quality = 50))
        val expectedOutput =
            listOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 10, quality = 50))

        testGildedRoseItems(input, expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithPassesBeforeSellDateUpperBound() {
        val input = listOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 10, quality = 5))
        val expectedOutput =
            listOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 9, quality = 7))

        testGildedRoseItems(input, expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithPassesBeforeSellDateLowerBound() {
        val input = listOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 6, quality = 5))
        val expectedOutput =
            listOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 5, quality = 7))

        testGildedRoseItems(input, expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithPassesJustBeforeSellDateUpperBound() {
        val input = listOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 5, quality = 5))
        val expectedOutput =
            listOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 4, quality = 8))

        testGildedRoseItems(input, expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithPassesJustBeforeSellDateLowerBound() {
        val input = listOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 1, quality = 5))
        val expectedOutput =
            listOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 0, quality = 8))

        testGildedRoseItems(input, expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithPassesOnSellDate() {
        val input = listOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 0, quality = 5))
        val expectedOutput =
            listOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = -1, quality = 0))

        testGildedRoseItems(input, expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithPassesAfterSellDate() {
        val input = listOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = -1, quality = 5))
        val expectedOutput =
            listOf(Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = -2, quality = 0))

        testGildedRoseItems(input, expectedOutput)
    }

    // MARK = Conjured Items

    @Test
    fun testUpdateQuantityWithConjuredItem() {
        val input = listOf(Item(name = "Conjured Mana Cake", sellIn = 5, quality = 5))
        val expectedOutput = listOf(Item(name = "Conjured Mana Cake", sellIn = 4, quality = 3))

        testGildedRoseItems(input, expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithConjuredItemAtLowQuality() {
        val input = listOf(Item(name = "Conjured Mana Cake", sellIn = 5, quality = 1))
        val expectedOutput = listOf(Item(name = "Conjured Mana Cake", sellIn = 4, quality = 0))

        testGildedRoseItems(input, expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithConjuredItemOnSellDate() {
        val input = listOf(Item(name = "Conjured Mana Cake", sellIn = 0, quality = 5))
        val expectedOutput = listOf(Item(name = "Conjured Mana Cake", sellIn = -1, quality = 1))

        testGildedRoseItems(input, expectedOutput)
    }

    @Test
    fun testUpdateQuantityWithConjuredItemAfterSellDate() {
        val input = listOf(Item(name = "Conjured Mana Cake", sellIn = -1, quality = 5))
        val expectedOutput = listOf(Item(name = "Conjured Mana Cake", sellIn = -2, quality = 1))

        testGildedRoseItems(input, expectedOutput)
    }

    private fun testGildedRoseItems(inputItems: List<Item>, expectedOutput: List<Item>) {
        val gildedRose = createGildedRose()
        val testObserver = gildedRose.inventory.test()
        gildedRose.updateInventoryy(inputItems)

        testObserver.assertValueSequence(expectedOutput)
            .assertNotComplete()
    }

    private fun testSingleGildedRoseItem(singleItem: Item, expectedOutput: List<Item>) {
        val gildedRose = createGildedRose()
        val testObserver = gildedRose.inventory.test()
        gildedRose.updateInventoryy(singleItem)

        testObserver.assertValueSequence(expectedOutput)
            .assertNotComplete()
    }
}
