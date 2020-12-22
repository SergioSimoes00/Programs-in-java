package hangman;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * WordGenerator implementa um gerador de palavras, keywords de Java.
 * As palavras são seleccionadas aleatoriamente de um ArrayList.
 *
 * @author POO 2019/2020
 * @version june/2020
 */
public class WordGenerator {

    private List<String> words;

    /**
     * Constructor for objects of class WordGenerator
     */
    public WordGenerator() {
        words = new ArrayList<>();
        fillArrayList();
    }

    private void fillArrayList() {
        words.add("abstract");
        words.add("boolean");
        words.add("break");
        words.add("byte");
        words.add("case");
        words.add("catch");
        words.add("char");
        words.add("class");
        words.add("continue");
        words.add("do");
        words.add("double");
        words.add("else");
        words.add("enum");
        words.add("extends");
        words.add("false");
        words.add("final");
        words.add("finally");
        words.add("float");
        words.add("for");
        words.add("if");
        words.add("implements");
        words.add("import");
        words.add("instanceof");
        words.add("int");
        words.add("interface");
        words.add("long");
        words.add("new");
        words.add("null");
        words.add("package");
        words.add("private");
        words.add("protected");
        words.add("public");
        words.add("return");
        words.add("static");
        words.add("super");
        words.add("switch");
        words.add("this");
        words.add("throw");
        words.add("throws");
        words.add("transient");
        words.add("true");
        words.add("try");
        words.add("void");
        words.add("while");
    }

    public String generateWord() {
        Random randomSequence = new Random();
        int index = randomSequence.nextInt(words.size());
        return words.get(index);
    }

    public void addWord(String additionalWord) {
        words.add(additionalWord);
    }
}
