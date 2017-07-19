package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int): Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int = when {
        other.year != year -> year - other.year
        other.month != month -> month - other.month
        else -> dayOfMonth - other.dayOfMonth
    }
}
operator fun MyDate.rangeTo(other: MyDate) = DateRange(this, other)
operator fun MyDate.plus(time: TimeInterval) = this.addTimeIntervals(time, 1)
operator fun MyDate.plus(time: RepeatedTimeInterval) = this.addTimeIntervals(time.time, time.n)

class RepeatedTimeInterval(val time: TimeInterval, val n: Int)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}
operator fun TimeInterval.times(n: Int) = RepeatedTimeInterval(this, n)

class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate>, Iterable<MyDate> {
    override fun iterator() = DateIterator(this)
    override fun contains(value: MyDate) = start <= value && value <= endInclusive
}

class DateIterator(val dateRange: DateRange) : Iterator<MyDate> {
    var current: MyDate = dateRange.start
    override fun next(): MyDate {
        val result = current
        current = current.nextDay()
        return result
    }
    override fun hasNext(): Boolean = current <= dateRange.endInclusive
}
