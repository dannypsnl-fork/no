grammar No;

@header{
package lang.no.parser;
}

top : def
    ;

def : ID ':=' expr ;
expr : ID ;

ID : LETTER;
fragment LETTER : [a-zA-Z];
WS : [ \t\r\n]+ -> skip;
