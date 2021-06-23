/*import edu.duke.FileResource;
import java.util.*;

public class VigenereBreakerTester {
    private VigenereBreaker myBreaker;
    private String message;
    private int[] testKeySet;

    public void setMessage(String message) {
        this.message = message;
    }

    public VigenereBreakerTester(String filename){
        myBreaker = new VigenereBreaker();
        FileResource fr = new FileResource(filename);
        this.message = fr.asString();
    }

    public VigenereBreakerTester(String message, int[] testKeySet){
        myBreaker = new VigenereBreaker();
        this.message = message;
        this.testKeySet = testKeySet;
    }



    public void testSliceString(int whichSlice, int totalSlices){
        System.out.println(myBreaker.sliceString(message,whichSlice, totalSlices));
    }

    public void testTryKeyLength(int klength, char mostCommon){
        System.out.println(Arrays.toString(myBreaker.tryKeyLength(message,klength,mostCommon)));
    }

    public void testBreakVigenere(int klength, char mostCommon){
        System.out.println(myBreaker.breakVigenere(message,klength,mostCommon));
    }

    public void testCountWords(String dictionaryName,char mostCommon){
        System.out.println(myBreaker.countWords(myBreaker.breakVigenere(message,dictionaryName,mostCommon), myBreaker.readDictionary(dictionaryName)) + " words found");
    }

    public void testFindKeyLength(String dictionaryName,char mostCommon){
        System.out.println("Key length is " + myBreaker.findKeyLength(message,dictionaryName,mostCommon));
    }
    public void testBreakVigenere(String dictionaryName,char mostCommon){
        System.out.println(myBreaker.breakVigenere(message,dictionaryName,mostCommon));
    }
}
*/