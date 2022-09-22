package patternsForCodingInterviews.mergeIntervals

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743634893_23Unit
object IntervalsIntersection {
    @JvmStatic
    fun main(args: Array<String>) {
        var input1 = arrayOf(
            Interval(1, 3), Interval(5, 6),
            Interval(7, 9)
        )
        var input2 = arrayOf(Interval(2, 3), Interval(5, 7))
        var result = merge(input1, input2)
        print("Intervals Intersection: ")
        for (interval in result) print("[" + interval.start + "," + interval.end + "] ")
        println()
        input1 = arrayOf(
            Interval(1, 3), Interval(5, 7),
            Interval(9, 12)
        )
        input2 = arrayOf(Interval(5, 10))
        result = merge(input1, input2)
        print("Intervals Intersection: ")
        for (interval in result) print("[" + interval.start + "," + interval.end + "] ")
    }

    fun merge(arr1: Array<Interval>, arr2: Array<Interval>): Array<Interval> {
        val result: MutableList<Interval> = ArrayList()
        var i = 0 //Pointer for the first array (arr1)
        var j = 0 //Pointer for the second array (arr2)
        while (i < arr1.size && j < arr2.size) {
            //Check if the interval arr[i] intersects with arr2[j]
            //Check if one of the interval's start time lies within the other interval
            if (( //Make sure arr1.start  is inside (overlap) arr2
                        arr1[i].start >= arr2[j].start
                                && arr1[i].start <= arr2[j].end)
                || (arr2[j].start >= arr1[i].start
                        && arr2[j].start <= arr1[i].end)
            ) {
                //Store the intersection part
                val start = Math.max(arr1[i].start, arr2[j].start)
                val end = Math.min(arr1[i].end, arr2[j].end)
                result.add(Interval(start, end))
            }
            //Move next from the interval which is finishing first
            if (arr1[i].end < arr2[j].end) {
                i++
            } else {
                j++
            }
        }
        return result.toTypedArray()
    }
}