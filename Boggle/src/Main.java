import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;


public class Main {
    //To store all the valid String into this list
    private static List<String> list = new ArrayList<>();
    public static List<String> boggle(int N, char[][] arrays) {
        List<String> result = new ArrayList<>();
        if(arrays == null || arrays.length == 0 || arrays[0] == null || arrays[0].length == 0
                || N != arrays.length || N != arrays[0].length) {
            return result;
        }
        //Initialize the root
        Trie root = new Trie();
        //Add all the valid string input the root chain
        for(String word : list) {
            root.addWord(word);
        }
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                //If the first character exist in the root,
                // we will do the dfs to search the valid word start with this character
                if(root.startsWith(Character.toString(arrays[i][j]))) {
                    dfs(i, j, root, arrays, result, "");
                }
            }
        }
        System.out.println("printout the result: " + result);
        return result;
    }
    private static void dfs(int x, int y, Trie root, char[][] arrays, List<String> result, String s) {
        //To see if the current position is in the boundary and if this character has been visited or not
        if(x < 0 || x >= arrays.length || y < 0 || y >= arrays.length || !Character.isLetter(arrays[x][y])) {
            return;
        }
        //If this is a character we add to the string
        s += arrays[x][y];
        //Determine this prefix string is valid or not
        if(!root.startsWith(s.toString())) {
            return;
        }
        //To see if the string has length at least 3 and it's valid in our root
        if(root.search(s) && s.length() >= 3) {
            result.add(s);
        }
        //Store this position's character
        char temp = arrays[x][y];
        //Mark this position as visited
        arrays[x][y] = '#';
        //dfs for horizontally, vertically, and diagonally
        dfs(x + 1, y, root, arrays, result, s);
        dfs(x, y + 1, root, arrays, result, s);
        dfs(x - 1, y, root, arrays, result, s);
        dfs(x, y - 1, root, arrays, result, s);
        dfs(x + 1, y + 1, root, arrays, result, s);
        dfs(x + 1, y - 1, root, arrays, result, s);
        dfs(x - 1, y - 1, root, arrays, result, s);
        dfs(x - 1, y + 1, root, arrays, result, s);
        arrays[x][y] = temp;
    }
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        //Fetch the txt from URL
        try {
            URL url = new URL("https://raw.githubusercontent.com/dwyl/english-words/master/words_alpha.txt");
            URLConnection uc;
            uc = url.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            System.out.println("Wrong URL address");
        }

        //Initialize the input for testing
        int N = 3;
        char[][] arrays = {{'c', 'j', 'e'}, {'e', 'a', 'o'}, {'i', 't', 'e'}};
        boggle(N, arrays);

    }

}
