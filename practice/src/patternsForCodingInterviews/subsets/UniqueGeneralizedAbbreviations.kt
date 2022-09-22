package patternsForCodingInterviews.subsets

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744084164_72Unit
object UniqueGeneralizedAbbreviations {
    @JvmStatic
    fun main(args: Array<String>) {
        val result = generateGeneralizedAbbreviationRecursive("BAT")
        println("Generalized abbreviation are: $result")

        /*
        result = generateGeneralizedAbbreviation("code");
        System.out.println("Generalized abbreviation are: " + result);

         */
    }

    /*
    Recursive
     */
    fun generateGeneralizedAbbreviationRecursive(word: String): List<String> {
        val result: MutableList<String> = ArrayList()
        generateAbbreviationRecursive(word, StringBuilder(), 0, 0, result)
        return result
    }

    private fun generateAbbreviationRecursive(
        word: String, abWord: StringBuilder,
        start: Int, count: Int, result: MutableList<String>
    ) {
        if (start == word.length) {
            if (count != 0) abWord.append(count)
            result.add(abWord.toString())
        } else {
            // continue abbreviating by incrementing the current abbreviation count
            generateAbbreviationRecursive(
                word, StringBuilder(abWord),
                start + 1, count + 1, result
            )

            // restart abbreviating, append the count and the current character to the string
            if (count != 0) abWord.append(count)
            generateAbbreviationRecursive(
                word,
                StringBuilder(abWord).append(word[start]), start + 1, 0, result
            )
        }
    }

    /*
    Iterative
     */
    fun generateGeneralizedAbbreviation(word: String): List<String> {
        val wordLen = word.length
        val result: MutableList<String> = ArrayList()
        val queue: Queue<AbbreviatedWord> = LinkedList()
        //Start with empty word
        queue.add(AbbreviatedWord(StringBuilder(), 0, 0))
        while (!queue.isEmpty()) {
            //Take combinations from the previous step
            val abWord = queue.poll()
            //Abbrevate
            if (abWord.start == wordLen) { //Abbreviate the current character
                if (abWord.count != 0) {
                    abWord.str.append(abWord.count)
                }
                result.add(abWord.str.toString())
            } else {
                //Skip
                // continue abbreviating by incrementing the current abbreviation count
                queue.add(
                    AbbreviatedWord(
                        StringBuilder(abWord.str),
                        abWord.start + 1, abWord.count + 1
                    )
                )

                // restart abbreviating, append the count and the current character to the string
                if (abWord.count != 0) abWord.str.append(abWord.count)
                queue.add(
                    AbbreviatedWord(
                        StringBuilder(
                            abWord.str
                        ).append(word[abWord.start]),
                        abWord.start + 1,
                        0
                    )
                )
            }
        }
        return result
    }

    internal class AbbreviatedWord(var str: StringBuilder, var start: Int, var count: Int)
}