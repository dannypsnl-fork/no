grammar No;

@header{
package lang.no.parser;
}

top : def
    ;

def : ID ':=' expr ;
expr : ID
    | INTEGER;

INTEGER : DIGIT+;
ID : LETTER;
fragment LETTER : [a-zA-Z];
fragment DIGIT : [0-9];
WS : [ \t\r\n]+ -> skip;
