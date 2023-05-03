import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
static String string;
 static ArrayList<String> list;
   Lexer(String string){
    Lexer.string =string;
       this.list= new ArrayList<>(){};
    }
    public static ArrayList<String> getList(){return list;}
    public static String getToken(String string) {
        String foundSymbol="";
        for (Token t : Token.values()) {
            if (string.equals(t.getSymbol())) {
                foundSymbol = String.valueOf(Token.of(string));
            } }
        return foundSymbol;
    }
    public static void handler() {
        String[] str = string.split("\\s+");
int next=0;
        while (next<str.length) {

            if (!isComment(str[next])) {
                if (isIntegerLiteral(str[next])) {
                    list.add("INT_LIT");
                } else if (isFloatingPointLiteral(str[next])) {
                    list.add("FP_LIT");
                }

                else if(isFunction(str[next])){
                    list.add("FUNC");
                }

                else {
                    boolean isKeyword = false;
                        for (Token t : Token.values()) {
                            if (str[next].equals(t.getSymbol())) {
                                list.add(t.toString());
                                isKeyword = true;
                                break;
                            }
                        }

                    if (!isKeyword && isIdentifier(str[next]) ) {
                             if (isFunction(str[next])) {
                                list.add("FUNC");
                             }else {
                                 list.add("ID");
                         }
                    }
                }
            }
next++;
        }
      System.out.println(list);

    }
    public static boolean isFunction(String string){
        String numberRegex = "(?:public|private|protected|static|\\s) +[\\w<>\\[\\],]+\\s+(\\w+)\\s*\\([^)]*\\)\\s*";
        Pattern pattern = Pattern.compile(numberRegex);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
    public static boolean isIdentifier(String string) {
        String numberRegex = "[_]?([a-zA-Z]+[_]?[a-zA-Z]*)";
        Pattern pattern = Pattern.compile(numberRegex);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
    public static boolean isIntegerLiteral(String number) {
        String numberRegex = "[0-9]|[1-9][0-9]*|[0-7]+|(0[xX][0-9a-fA-F]+)(i64|I64|u|U|l|L)?";
        Pattern pattern = Pattern.compile(numberRegex);
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }
    public static boolean isFloatingPointLiteral(String number) {
        String numberRegex = "[+-]?([0-9]*.[0-9]+|[0-9]+.)([eE][+-]?[0-9]+)?[LlFf]?|[+-]?[0-9]+[Ee][+-]?[0-9]+[LlFf]?";
        Pattern pattern = Pattern.compile(numberRegex);
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }
    public static boolean isComment(String line) {
        if (line.trim().startsWith("//")) {
            return true;
        }
        if (line.trim().startsWith("/*") &&line.trim().endsWith("*/") ) {
            return true;
        }

        return false;
    }
}
