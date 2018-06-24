package solution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class ConcatenatedWords {
    public static void main(String... args) {
        try {
            ConcatenatedWords test = new ConcatenatedWords();
            Result result = readFromFile();
            Set<String> words = result.wordDict;
            Integer maxLength = result.maxLength;
            TreeNode root = test.buildTree(words);
            List<String> concatenatedWords = new ArrayList<>();
            for (String word : words) {
                if (word == null || word.length() == 0) continue;
                test.remove(word, root);
                int n = word.length();
                boolean[] bool = new boolean[n + 1];
                bool[0] = true;

                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= i && j <= maxLength; j++) {
                        if (!bool[i - j]) continue;

                        String subWord = word.substring(i - j, i);
                        if (test.contains(subWord, root)) {
                            bool[i] = true;
                            break;
                        }
                    }
                }
                if (bool[n]) concatenatedWords.add(word);
                test.undoRemove(word, root);
            }

            //Note: we're only interested in the first two longest words, so we can iterate through validConcatenatedWords once and find those two
            String firstLongest = "";
            String secondLongest = "";
            for (String word : concatenatedWords) {
                if (word.length() > secondLongest.length()) {
                    if (word.length() > firstLongest.length()) {
                        secondLongest = firstLongest;
                        firstLongest = word;
                    } else {
                        secondLongest = word;
                    }
                }
            }
            //print out the result we're interested in: longest concatenated word, second concatenated word, and the total number of all concatenated words
            System.out.println("1)The largest concatenated word is: " + firstLongest + ".\nLength of first word is: "
                    + firstLongest.length() + ".\n2)The second longest concatenated word is: " +
                    secondLongest + ".\nlength of second word is: " + secondLongest.length() +
                    ".\n3)Total of " + concatenatedWords.size() + " valid concatenated words in file.");
            System.out.println();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    // Returns true if the word is in the trie.
    public boolean contains(String word, TreeNode root) {
        TreeNode node = root;
        for (int i = 0; i < word.length(); i++) {
            if (node.child[word.charAt(i) - 'a'] == null) return false;
            node = node.child[word.charAt(i) - 'a'];
        }
        return node.isWord;
    }

    
    private TreeNode buildTree(Set<String> words) {
        TreeNode root = new TreeNode();
        for (String word : words) {
            char[] chars = word.toCharArray();
            TreeNode node = root;
            for (char c : chars) {
                if (node.child[c - 'a'] == null) {
                    node.child[c - 'a'] = new TreeNode();
                }
                node = node.child[c - 'a'];
            }
            node.isWord = true;
        }
        return root;
    }
    
    
     // mark that word off, we are not really deleting that word
    private void remove(String word, TreeNode root) {
        TreeNode node = root;
        for (int i = 0; i < word.length(); i++) {
            node = node.child[word.charAt(i) - 'a'];
        }
        node.isWord = false;
    }

    // mark that word on
    private void undoRemove(String word, TreeNode root) {
        TreeNode node = root;
        for (int i = 0; i < word.length(); i++) {
            node = node.child[word.charAt(i) - 'a'];
        }
        node.isWord = true;
    }

    
     private static Result readFromFile() throws Exception {
        Set<String> wordD = new HashSet<>();
        int maxLength = Integer.MIN_VALUE;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader
                ("C:\\Users\\Olejkoo\\Desktop\\wordsforproblem\\src\\solution\\words.txt"))) {
            String line = bufferedReader.readLine();

            while (line != null) {
                String word = line;
                maxLength = (maxLength > word.length()) ? maxLength : word.length();
                if (word.length() != 0)
                    wordD.add(word.trim());
                line = bufferedReader.readLine();
            }
        }
        return new Result(wordD, maxLength);
    }
}
