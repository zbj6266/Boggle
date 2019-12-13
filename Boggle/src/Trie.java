public class Trie {
    TrieNode root;
    public Trie() {
        root = new TrieNode();
    }
    //add valid word to root TrieNode
    public void addWord(String word) {
        TrieNode node = root;
        for(char a : word.toCharArray()) {
            if(node.next[a - 'a'] == null) {
                node.next[a - 'a'] = new TrieNode();
            }
            node = node.next[a - 'a'];
        }
        node.word = word;
    }

    //Start serching the prefix of the word is valid
    public boolean startsWith(String word) {
        TrieNode node = root;
        for(char a : word.toCharArray()) {
            if(node.next[a - 'a'] == null) {
                return false;
            }
            node = node.next[a - 'a'];
        }
        return true;
    }

    //Search if the node.word has equal with the input word
    public boolean search(String word) {
        TrieNode node = root;
        for(char a : word.toCharArray()) {
            if(node.next[a - 'a'] == null) {
                return false;
            }
            node = node.next[a - 'a'];
        }
        return node.word.equals(word);
    }
}
