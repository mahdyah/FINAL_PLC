import java.util.*;

public enum Token {

    ADD_OP ("+"),
    SUB_OP("-" ),
    MULT_OP("*"),
    DIV_OP("/"),
    MODULO("%"),
    LEFT_PAREN("("),
    RIGHT_PAREN(")"),
    LEFT_CURLY("{"),
    RIGHT_CURLY("}"),
    ASSIGN_OP("="),
    EQUAL("=="),
    NOT_EQUAL("!="),
    LESS_THAN("<"),
    GREATER_THAN(">"),
    LESS_EQUAL("<="),
    GREATER_EQUAL(">="),
    LOGICAL_AND("&&"),
    LOGICAL_OR("||"),
    WHILE("while"),
    DO("do"),
    IF("if" ),
    SEMICOLON(";"),
    INT("int"),
    FLOAT("float"),
    ELSE("else");

    final String symbol;

    private static final Map<String, Token> map = new HashMap<>(values().length, 1);
    static {
        for(Token t : values()) map.put(t.symbol,t);
    }
    Token(String symbol) {
        this.symbol= symbol;
    }
    public String getSymbol() {
        return symbol;
    }
    public static Token of(String name){
        Token result=map.get(name);
        if(result==null){
            throw new IllegalArgumentException("Invalid token name:" +name);
        }
        return result;
    }


}
