import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class WordMap {
    String entry;
    Multimap<WordType, String> typeMap= ArrayListMultimap.create();
    public WordMap(String entry, WordType wordType, String meaning){
        this.entry = entry;
        this.typeMap.put(wordType,meaning);
    }
    public void add(WordType wordType, String meaning){
        this.typeMap.put(wordType, meaning);
    }
    public void print(){
        for (Map.Entry definitions: this.typeMap.entries() ) {
            System.out.println(definitions);
        }
    }
    public List<String> get(WordType wordType){
        return (List<String>) this.typeMap.get(wordType);
    }
    private String capitalize(String original){
        return original.substring(0,1).toUpperCase()+original.substring(1).toLowerCase();
    }

    public String[]  getMeanings(){
        List<String> definitionList = new ArrayList<String>() ;
        for (Map.Entry entry : this.typeMap.entries()) {
            definitionList.add(this.capitalize(this.entry) +" ["+entry.getKey().toString().toLowerCase() + "] : " + entry.getValue());
        }
        String[] definitionArray = new String[definitionList.size()];
        definitionArray = definitionList.toArray(definitionArray);
        return definitionArray;
    }
    public String[]  getMeanings(WordType wordType){
        List<String> definitionList = new ArrayList<String>() ;
        for (String meaningWithType : this.typeMap.get(wordType)) {
            definitionList.add(this.capitalize(this.entry) +" ["+ wordType.toString().toLowerCase() + "] : " + meaningWithType);
        }
        String[] definitionArray = new String[definitionList.size()];
        definitionArray = definitionList.toArray(definitionArray);
        return definitionArray;
    }
    public boolean hasType(String wordType){
        if (WordType.check(wordType) && this.typeMap.containsKey(WordType.valueOf(wordType.toUpperCase())))
            return true;
        return false;
    }
}
