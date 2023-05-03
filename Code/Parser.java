import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Parser {
    private static int currentToken;
    static Compiler compiler= new Compiler();
    Lexer lex=new Lexer(compiler.getInputStr());
static ArrayList<String> list;
Parser(){
    list= Lexer.getList();
}

static String nextToken(){
        String nexttoken="";
    System.out.println("nextToken check in!");
    System.out.println(list);
        if (!list.isEmpty()){
            nexttoken= list.get(currentToken);
            currentToken++;
            System.out.println(nexttoken);
        }
        return nexttoken;
    }

    //    <STMT> --> <IF_STMT> | <BLOCK> | <ASSIGN> | <DECLARE> |<WHILE_LOOP>
    public static void STMT() throws Exception {
        String token=nextToken();
        System.out.println(token);

        System.out.println("STMT check in !");

        if (token.equals("IF")) {
            IF_STMT(token);
            System.out.println("stmt " + token);
        }else if (token.equals("LEFT_CURLY"))
            BLOCK(token);
        else if (token.equals("="))
            token=nextToken();
        else if(token.equals("ID"))
            ASSIGN(token);

        else if (token.equals("WHILE"))
            while_loop(token);
        else
            throw new Exception("Unrecognized token");
    }
    //    <IF_STMT> --> `if` `(` <BOOL_EXPR> `)` <BLOCK>  [ `else`  <BLOCK> ]
    public static void IF_STMT(String token) throws Exception {
//        System.out.println("IF_STMT check in !");
        System.out.println("IF_STMT "+ nextToken());
        if (token.equals("IF")) {
//            System.out.println("here-> " + nextToken(index));
            if (nextToken().equals("LEFT_PAREN")) {

                BOOL_EXPR(nextToken());

                if (nextToken().equals("RIGHT_PAREN")){

                    BLOCK(nextToken());
                    if (nextToken().equals("ELSE")) {

                        BLOCK(nextToken());
                        STMT();
                    }else {
                        throw new Exception("else");
                    }
                } else {
                    throw new Exception("missing )");
                }
            }else {
                throw new Exception("missing (");
            }
        } else {
            throw new Exception("missing  If");
        }
    }
    public static boolean isIdentifier(String string) {
        String numberRegex = "[_]?([a-zA-Z]+[_]?[a-zA-Z]*)";
        Pattern pattern = Pattern.compile(numberRegex);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
    private static void DEClEAR(String Token) {
        System.out.println("DECLEAR check in !");
        if (isIdentifier(Token)){
            switch (Token) {
                case "int" -> System.out.print("int");
                case "boolean" -> System.out.print("boolean");
                case "double" -> System.out.print("double");
                case "float" -> System.out.print("float");
            }
        }}
    private static void ASSIGN(String Token) throws Exception {
        System.out.println("ASSIGN check in !");
        if (isIdentifier(Token)){

            if (nextToken().equals("=")){
                EXPR(Token);
            } else {
                throw new Exception("missing = ");
            }
        }

    }

    //    <WHILE_LOOP> --> `while` `(` <BOOL_EXPR> `)` <BLOCK>
    public static void while_loop(String Token) throws Exception {
        System.out.println("WHILE_LOOP check in !");
        if (nextToken().equals("WHILE")) {
            if (nextToken().equals("LEFT_PAREN")) {

                BOOL_EXPR(Token);
                if (nextToken().equals("RIGHT_PAREN")) {
                    BLOCK(Token);
                }
            } else {

                throw new Exception("missing ( ");
            }
        }
    }
    public static void BTERM(String Token) throws Exception {
        System.out.println("BTERM check in !");
        BAND(Token);
        String tkn=nextToken();
        if (tkn.equals("EQUAL")||tkn.equals("NOT_EQUAL") ) {

            BAND(Token);
        }else{
            throw new Exception(" Something wrong with Bterm");
        }
    }
    // <BAND> --> <BOR> {`&&` <BOR>}
    public static void BAND(String nextToken) throws Exception {
        System.out.println("BAND check in !");
        BOR(nextToken);
        if (nextToken.equals("LOGICAL_AND") ) {
            BOR(nextToken);
        }else{
            throw new Exception(" Something wrong with BAND");
        }
    }
    // <BOR> --> <EXPR> {`||` <EXPR>}
    public static void BOR(String nextToken) throws Exception {
        System.out.println("BOR check in !");
        EXPR(nextToken);
        if (nextToken.equals("LOGICAL_OR") ) {
            EXPR(nextToken);
        }else{
            throw new Exception(" Something wrong with BOR");
        }
    }

    // <STMT_LIST> --> { <STMT> `;` }
    public static void STMT_LIST(String nextToken) throws Exception {
        System.out.println("STMT_LIST check in !");
        STMT();
        if (!nextToken.equals("SEMICOLON")){
            throw new Exception("missing ; ");
        }}
    // <BOOL_EXPR> --> <BTERM> {(`>`|`<`|`>=`|`<=` | ==) <BTERM>}
    public static void BOOL_EXPR(String Token) throws Exception {
        System.out.println("BOOL_EXPR check in !");
        BTERM(Token);
            throw new Exception("missing something in Boolean expression");
        }

    // <BLOCK> --> `{` <STMT_LIST> `}`
    public static void BLOCK(String nextToken) throws Exception {
        System.out.println("block check in !");
        if (nextToken.equals("LEFT_CURLY"))
            STMT_LIST(nextToken);
        if (!nextToken().equals("RIGHT_CURLY"))
           throw new Exception("missing }");
    }

    //    <EXPR> --> <TERM> {(`+`|`-`) <TERM>}
    public static void EXPR(String Token) throws Exception {
        System.out.println("EXPR check in !");
        term(Token);
        String tkn=nextToken();
        if (tkn.equals("+" )|| tkn.equals("-")) {
            term(nextToken());
        }

    }

    public static void term(String Token) throws Exception {
        System.out.println("term check in !");
        FACT(Token);
        String tkn=nextToken();
        if (tkn.equals("*") || tkn.equals( "/")|| tkn.equals("%")) {
            FACT(nextToken());
        }
    }

    public static void  FACT(String Token) throws Exception {
        System.out.println("factor check in !");
        String tkn=nextToken();
        if (Token.equals("ID")|| Token.equals("INT_LIT") || Token.equals("FLO_LIT")) {
            tkn = nextToken();
        }
            else
                if (tkn.equals("LEFT_PAREN")) {
                    tkn=nextToken();
                EXPR(tkn);
        }
    }

}






