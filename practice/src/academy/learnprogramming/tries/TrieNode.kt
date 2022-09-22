package academy.learnprogramming.tries

//https://www.baeldung.com/trie-java
/*
Ordered Trie Structure which takes advantage of keys that it stores.
Node's position in the tree defines the key with which that node is associated.

Each node in binary search trees always has two children
whereas tries' nodes, on the other hand, can have more


 */
internal object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val trie = Trie()
        //trie.insert("Programming");
        trie.insert("is")
        /*
        trie.insert("a");
        trie.insert("way");
        trie.insert("of");
        trie.insert("life");
         */trie.delete("is")
    }
}

internal class TrieNode {
    val children: MutableMap<Char, TrieNode> = HashMap()
    var isEndOfWord = false
} //---------------------------------------------

internal class Trie {
    private val root: TrieNode

    init {
        root = TrieNode()
    }

    //Delete element
    fun delete(word: String): Boolean {
        return delete(root, word, 0)
    }

    private fun delete(current: TrieNode, word: String, index: Int): Boolean {
        if (index == word.length) {
            if (!current.isEndOfWord) {
                return false
            }
            current.isEndOfWord = false
            return current.children.isEmpty()
        }
        val ch = word[index]
        val node = current.children[ch] ?: return false
        val shouldDeleteCurrentNode = (delete(node, word, index + 1)
                && !node.isEndOfWord)
        if (shouldDeleteCurrentNode) {
            current.children.remove(ch)
            return current.children.isEmpty()
        }
        return false
    }

    //Time complexity: O(n) where N represents the key
    fun insert(word: String) {
        var current = root
        for (l in word.toCharArray()) {
            println(current)
            current = current.children
                .computeIfAbsent(l) { c: Char? -> TrieNode() }
        }
        current.isEndOfWord = true
    }

    //Finding element
    fun find(word: String): Boolean {
        var current = root
        for (i in 0 until word.length) {
            val ch = word[i]
            val node = current.children[ch] ?: return false
            current = node
        }
        return current.isEndOfWord
    }
}