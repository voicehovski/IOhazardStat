package goit.hw10;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordStatisticator {
    public void count ( String filePath ) throws Exception {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader ( new FileReader( filePath ) );

            String line = "";
            Map<String, Integer> dictionary = new HashMap<> (  );

            while ((line = reader.readLine()) != null) {
                for ( String word : getWords ( line ) ) {
                    if ( word .length (  ) == 0 ) {
                        continue;
                    }
                    if ( dictionary .containsKey ( word ) ) {
                        int value = dictionary .get ( word );
                        dictionary .put ( word, value + 1 );
                    } else {
                        dictionary .put ( word, 1 );
                    }
                }
            }

            System.out.println ( sortMap ( dictionary ) );
        } finally {
            if ( reader != null ) {
                reader .close (  );
            }
        }
    }

    private String [] getWords ( String s ) {
        return s .split ( "\\s" );
    }

    // Я же правильно понял, что нужно было обязательно сделать сортировку? А то оно и так выводило в нужном порядке...
    private List sortMap ( Map<String, Integer> map ) {
        List list = new ArrayList(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                return  b.getValue() - a.getValue();
            }
        });
        return list;
    }

    public static void main(String[] args) {
        try {
            new WordStatisticator (  ) .count ("words.txt");
        } catch ( FileNotFoundException fnfe ) {
            System.err.println ( "File not found" );
        } catch ( IOException ioe ) {
            System.err.println ( "Error while reading from file" );
        } catch ( Exception e ) {
            System.err.println ( "Common error" );
        }
    }
}
