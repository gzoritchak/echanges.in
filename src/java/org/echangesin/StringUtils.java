package org.echangesin;


import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang.StringUtils.isEmpty;

/**
 */
public class StringUtils {

    private static final char[] ACCENTS = new char[]{    'À', 'Ç', 'È', 'É', 'Ê', 'Ë', 'Ô', 'Ù', 'à', 'ç', 'è', 'é', 'ê', 'ë', 'Ï', 'î', 'ï', 'ô', 'ö', 'ù', 'Û', 'û', 'ü', 'Œ', 'œ'};
    private static final String[] ACC_REP = new String[]{"A", "C", "E", "E", "E", "E", "O", "U", "a", "c", "e", "e", "e", "e", "I", "i", "i", "o", "o", "u", "U","u", "u", "OE", "oe"};

    private static final char[] SIGNS = new char[]{      ' ','.','(',')','!','?',':',',',';','\'','+','»','«','"', '*','/','_','\''};
    private static final String[] SIG_REP = new String[]{"-","-","-","-","-","-","-","-","-","-" ,"-", "", "", "", "-","-","-","-"};

    /**
     * Remplace les prettyUrl français.
     */
    public static String cleanForUrl(String url) {
        if(url == null || isEmpty(url)) return url;
        return removeExtras(removeSigns(removeAccents(url))).toLowerCase().trim();
    }

    public static String removeAccents(String in){
        if(in == null || isEmpty(in)) return in;
        StringBuilder st = new StringBuilder();
        for (int i = 0; i < in.length(); i++) {
            char c = in.charAt(i);
            st.append(replacementFor(ACCENTS, ACC_REP, c));
        }
        return st.toString();
    }

    public static String removeSigns(CharSequence in){
        StringBuilder st = new StringBuilder();
        for (int i = 0; i < in.length(); i++) {
            char c = in.charAt(i);
            st.append(replacementFor(SIGNS, SIG_REP, c));
        }
        return st.toString();
    }

    private static String removeExtras(String st) {
        StringBuilder ret = new StringBuilder(st);

        //suppression des tirets multiples.
        for (int i = 1; i < ret.length(); ) {
            char car = ret.charAt(i++);
            if(car == '-' && ret.charAt(i-2) == '-')
                ret.deleteCharAt(--i);
        }

        char first = ret.charAt(0);
        while (first == '-'){
            ret.deleteCharAt(0);
            first = ret.charAt(0);
        }

        char last = ret.charAt(ret.length() -1);
        while (last == '-'){
            ret.deleteCharAt(ret.length() -1);
            last = ret.charAt(ret.length() -1);
        }
        return ret.toString();
    }

    private static String replacementFor(char[] from, String[] to, char c){
        checkArgument(from.length == to.length);
        for (int j = 0; j < from.length; j++) {
            char accent = from[j];
            if (accent == c) {
                return to[j];
            }
        }
        return String.valueOf(c);

    }


}
