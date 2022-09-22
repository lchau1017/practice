package patternsForCodingInterviews.subsets

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744066603_70Unit
object StringPermutationsByChangingCase {
    @JvmStatic
    fun main(args: Array<String>) {
        val result = findLetterCaseStringPermutations("ad52")
        println(" String permutations are: $result")
        /*
        result = findLetterCaseStringPermutations("ab7c");
        System.out.println(" String permutations are: " + result);

 */
    }

    /*
    Time: O(N * 2N)
    Space: O(N * 2N)
     */
    fun findLetterCaseStringPermutations(str: String?): List<String> {
        val permutations: MutableList<String> = ArrayList()
        if (str == null) {
            return permutations
        }
        permutations.add(str)
        //process evert characters if the string one by one
        for (i in 0 until str.length) {
            if (Character.isLetter(str[i])) { //Process only characters, skip digit
                //we will take all existing permutations and change the letter case appropriately
                val n = permutations.size
                for (j in 0 until n) {
                    val chs = permutations[j].toCharArray()
                    //If the current char is in upper case, change it to lower case and vice versa
                    if (Character.isUpperCase(chs[i])) {
                        chs[i] = chs[i].lowercaseChar()
                    } else {
                        chs[i] = chs[i].uppercaseChar()
                    }
                    permutations.add(String(chs))
                }
            }
        }
        return permutations
    }
}