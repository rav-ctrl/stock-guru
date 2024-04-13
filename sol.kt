fun main() {
    println(maxProfit(intArrayOf(7,6,4,3,1)))
}

fun maxProfit(prices: IntArray): Int {
    val dipsIndices = getDipIndex(prices)
    val profitArray = getProfitArray(prices, dipsIndices.toIntArray())
    return getMaxProfit(profitArray.toIntArray())
}

fun getDipIndex(prices: IntArray): MutableList<Int> {
    val dips = mutableListOf<Int>()
    for (j in prices.size - 1 downTo 0) {
        if (j == 0) {
            if (prices[0] < prices[1]) {
                dips.add(0)
            }
            break
        }

        if (prices[j] < prices[j - 1]) {
            dips.add(j)
        }
    }
    return dips
}

fun getProfitArray(prices: IntArray, dips: IntArray): MutableList<Int> {
    val profitArray = mutableListOf<Int>()
    val mDips = dips.reversedArray()
    for (i in mDips.indices) {
        if (i == mDips.size - 1) {
            if (getLocalProfit(mDips[i], prices.size - 1, prices) > 0)
                profitArray.add(getLocalProfit(mDips[i], prices.size - 1, prices))
            break
        }

        if (getLocalProfit(mDips[i], mDips[i + 1], prices) > 0)
            profitArray.add(getLocalProfit(mDips[i], mDips[i + 1], prices))
    }

    return profitArray
}

fun getLocalProfit(i: Int, i1: Int, prices: IntArray): Int {
    val localMin = prices[i]
    val localMax = prices.slice(i + 1..<i1).maxOrNull() ?: return 0
    return localMax - localMin
}

fun getMaxProfit(profits: IntArray): Int {
    return profits.maxOrNull() ?: 0
}
