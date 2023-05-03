import java.io.*;
public class Compiler {
    Lexer lex=new Lexer(inputStr);
    Parser pars=new Parser();
    private static String inputStr;

    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new FileReader("file.txt"));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append("\n");
        }
        reader.close();
        inputStr = stringBuilder.toString();
        Lexer lex= new Lexer(inputStr);
        Lexer.handler();
        Parser.STMT();
    }
    public String getInputStr() {
        return inputStr;
    }

}
