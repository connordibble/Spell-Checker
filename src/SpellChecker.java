import java.io.File;
import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;

public class SpellChecker {
    public static void main(String[] args) {
        reportTreeStats(makeDictionary());
        System.out.println();
        mispelledWords();
    }

    public static void reportTreeStats(BinarySearchTree<String> tree) {
        System.out.println("-- Tree Stats --");
        System.out.printf("Total Nodes : %d\n", tree.numberNodes());
        System.out.printf("Leaf Nodes  : %d\n", tree.numberLeafNodes());
        System.out.printf("Tree Height : %d\n", tree.height());
    }

    public static BinarySearchTree makeDictionary(){
        ArrayList<String> list = new ArrayList<>();
        BinarySearchTree<String> spellChecker = new BinarySearchTree<>();

        try {
            java.util.Scanner input = new Scanner(new File("Dictionary.txt"));

            while (input.hasNext()) {
                String line = input.next();
                String word = line.toLowerCase().replaceAll("[“,:.!?()]","");
                list.add(word);
            }
        }
        catch (java.io.IOException ex) {
            System.out.println("File exception: " + ex.getMessage());
        }

        Collections.shuffle(list);

        for (String i:list)
        {
            spellChecker.insert(i);
        }

        return spellChecker;

    }

    public static void mispelledWords(){
        ArrayList<String> list = new ArrayList<>();

        try {
            java.util.Scanner input = new Scanner(new File("Letter.txt"));

            while (input.hasNext()) {
                String line = input.next();
                String word = line.toLowerCase().replaceAll("[“,:.!?()]","");
                if (!makeDictionary().search(word)){
                    System.out.println(word);
                }
            }
        }
        catch (java.io.IOException ex) {
            System.out.println("File exception: " + ex.getMessage());
        }
    }



}