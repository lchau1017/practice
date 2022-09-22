package patternsForCodingInterviews.slidingWindow

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628541078811_9Unit
object _4WordsConcatenation {
    @JvmStatic
    fun main(args: Array<String>) {
        val result: List<Int>
        //result = findWordConcatenation("catfoxcat", new String[] { "cat", "fox" });
        //System.out.println(result);
        result = findWordConcatenation(
            "catcatfoxfox", arrayOf("cat", "fox")
        )
        println(result)
    }

    /*
    Time: O(N * M * Len)
    N number of characters, M total of words and 'Len' is the length of the word
    Space: O(M), Overall O(M + N) -> 2 HashMaps
     */
    fun findWordConcatenation(str: String, words: Array<String>): List<Int> {
        val wordFrequency: MutableMap<String, Int> = HashMap()
        for (word in words) {
            wordFrequency[word] = wordFrequency.getOrDefault(word, 0) + 1
        }
        val resultIndices: MutableList<Int> = ArrayList()
        val wordCounts = words.size
        val wordLength = words[0].length
        //str.length() - wordCounts * wordLength because we have a second loop to look
        //the words from the list. Avoid indexOutOfBoundException when we expand the window
        for (i in 0..str.length - wordCounts * wordLength) {
            val wordsSeen: MutableMap<String, Int> = HashMap()
            //Look further in the string based on words we have
            for (j in 0 until wordCounts) {
                //get first index of the current string we looked for
                val nextWordIndex = i + j * wordLength
                //get the next word from the string
                //Get the entire word we looked for
                val word = str.substring(nextWordIndex, nextWordIndex + wordLength)
                if (!wordFrequency.containsKey(word)) { //break if we do not need this word
                    break
                }
                //add the word to the 'wordsSeen' map
                wordsSeen[word] = wordsSeen.getOrDefault(word, 0) + 1
                //No need to process further if the word has higher frequency than required
                if (wordsSeen[word]!! > wordFrequency.getOrDefault(word, 0)) {
                    break
                }
                if (j + 1 == wordCounts) { //Store index if we have found all the words
                    resultIndices.add(i)
                }
            }
        }
        return resultIndices
    }
}