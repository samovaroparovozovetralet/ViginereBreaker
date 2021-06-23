import edu.duke.FileResource;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String message = "Hello World, re-enter";
        String filename = "VigenereTestData/secretmessage4.txt";
        String dictionary = "dictionaries/English";
        int[] testKeySet = {0, 1, 2, 3, 4};
        //VigenereBreakerTester tester = new VigenereBreakerTester(message,testKeySet);
        //VigenereBreakerTester tester = new VigenereBreakerTester(filename);
        //tester.testSliceString(0,3);
        //tester.testTryKeyLength(4,'e');
        //tester.testBreakVigenere(4,'e');
        //tester.testFindKeyLength(dictionary,'e');
        //tester.testCountWords(dictionary,'e');
        //tester.testBreakVigenere(dictionary,'e');
        //FileResource fr = new FileResource(filename);
        //String encrypted = fr.asString();
        //VigenereBreaker breaker = new VigenereBreaker();
        //int klength = breaker.findKeyLength(encrypted,dictionary,'e');
        //String decrypted = breaker.breakVigenere(encrypted,klength,'e');
        //int wordCount = breaker.countWords(decrypted, breaker.readDictionary(dictionary));
        //System.out.println(wordCount);
        //System.out.println(klength);
        //System.out.println(decrypted);
        VigenereBreaker breaker = new VigenereBreaker();
        FileResource fr = new FileResource(filename);
        breaker.breakVigenere(fr.asString(),breaker.dictionariesWordsMap());
    }
}
