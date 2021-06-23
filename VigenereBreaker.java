import java.util.*;
import edu.duke.*;
import java.io.File;

public class VigenereBreaker {


    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for(int i = whichSlice;i<message.length();i+=totalSlices){
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for(int i =0;i<klength;i+=1){
            CaesarCracker caesarCracker = new CaesarCracker(mostCommon);
            String piece = sliceString(encrypted,i,klength);
            key[i] = caesarCracker.getKey(piece);
        }
        return key;
    }

    public String breakVigenere(String source,int klength, char mostCommon){
        VigenereCipher vc = new VigenereCipher(tryKeyLength(source,klength,mostCommon));
        return vc.decrypt(source);
    }

    public HashSet<String> readDictionary(String fileName){
        HashSet<String> myWords = new HashSet<String>();
        FileResource fr = new FileResource(fileName);
        for(String line : fr.lines()){
            myWords.add(line.toLowerCase());
        }
        return myWords;
    }
    public HashSet<String> readDictionary(File f){
        HashSet<String> myWords = new HashSet<String>();
        FileResource fr = new FileResource(f);
        for(String line : fr.lines()){
            myWords.add(line.toLowerCase());
        }
        return myWords;
    }

    public int countWords(String source,HashSet<String> dictionary){
        int count = 0;
        for(String word : source.split("\\W")){
            String lowerWord = word.toLowerCase();
            if(dictionary.contains(lowerWord)){
                count+=1;
            }
        }
        return count;
    }

    public int findKeyLength(String source, HashSet<String> dictionary,char mostCommon){
        int wordsNumber ;
        int maxWordsNumber = 0;
        int maxKeyLength = 100;
        int possibleKeyLength = 0;
        for(int klength = 1;klength<maxKeyLength;klength+=1){
            String currentString = breakVigenere(source,klength,mostCommon);
            wordsNumber = countWords(currentString,dictionary);
            if(wordsNumber>maxWordsNumber){
                maxWordsNumber = wordsNumber;
                possibleKeyLength = klength;
            }
        }

        return possibleKeyLength;
    }

    public String breakVigenere(String source, HashSet<String> dictionary,char mostCommon){
        int klength = findKeyLength(source,dictionary,mostCommon);
        System.out.println("Key length found: " + klength);
        return breakVigenere(source,klength,mostCommon);
    }

    public char mostCommonCharIn(HashSet<String> dictionary){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        CaesarCracker countLetters = new CaesarCracker();
        for(String word: dictionary){
            for(int i = 0;i<word.length();i+=1){
                int dex = alph.indexOf(Character.toLowerCase(word.charAt(i)));
                if (dex != -1){
                    counts[dex] += 1;
                }
            }
        }
        return alph.charAt(countLetters.maxIndex(counts));
    }

    public HashMap<String,HashSet<String>>dictionariesWordsMap(){
        HashMap<String,HashSet<String>> myMap = new HashMap<String,HashSet<String>>();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            myMap.put(f.getName(),readDictionary(f));
            System.out.println("Finished reading dictionary:" + f.getName());
        }
        System.out.println();
        return myMap;
    }

    public HashMap<String,Character> langCommonCharactersMap(HashMap<String,HashSet<String>>dictionaries){
        HashMap<String,Character> myMap = new HashMap<String,Character>();
        for(String lang : dictionaries.keySet()){
            myMap.put(lang,mostCommonCharIn(dictionaries.get(lang)));
        }
        return myMap;
    }

    public  void breakForAllLangs(String encrypted, HashMap<String,HashSet<String>> dictionaries){
        HashMap<String,Character> commonLetters = langCommonCharactersMap(dictionaries);
        int wordsCount;
        for(String lang : dictionaries.keySet()){
            System.out.println("Language processing: " + lang);
            String currString = breakVigenere(encrypted,dictionaries.get(lang),commonLetters.get(lang));
            wordsCount = countWords(currString,dictionaries.get(lang));
            System.out.println("Real-words in translation: " + wordsCount);
            System.out.println();
            System.out.println(currString);
            System.out.println();
        }
    }

    public void breakVigenere(String encrypted,HashMap<String,HashSet<String>> dictionaries){
        HashMap<String,Character> commonLetters = langCommonCharactersMap(dictionaries);
        int wordsCount;
        int maxCount = 0;
        String bestTranslation = "NOT FOUND";
        for(String lang : dictionaries.keySet()){
            System.out.println("Language processing: " + lang);
            String currString = breakVigenere(encrypted,dictionaries.get(lang),commonLetters.get(lang));
            wordsCount = countWords(currString,dictionaries.get(lang));
            System.out.println("Real-words in translation: " + wordsCount);
            System.out.println();
            if(wordsCount>maxCount){
                maxCount = wordsCount;
                bestTranslation = currString;
            }
        }
        System.out.println(bestTranslation);
    }
}
