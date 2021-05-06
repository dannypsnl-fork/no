grammar No;

@header{
package lang.no.parser;
import lang.no.concrete.*;
import lang.no.concrete.expr.*;
}

prog : top*
    ;

top : def
    ;

def : ID ':=' expr ;
expr returns [Expr e]
    : ID
    | INT
    | expr ('*'|'/') expr
    | expr ('+'|'-') expr;

INT : DIGIT+;
ID : LETTER;
fragment LETTER : [a-zA-Z];
fragment DIGIT : [0-9];
WS : [ \t\r\n]+ -> skip;
