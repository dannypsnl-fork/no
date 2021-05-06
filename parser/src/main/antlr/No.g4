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
    : ID { $e = new Var($ID.text); }
    | INT { $e = Int.fromText($INT.text); }
    | l=expr op=('*'|'/') r=expr { $e = new Binary($op.text, $l.e, $r.e); }
    | l=expr op=('+'|'-') r=expr { $e = new Binary($op.text, $l.e, $r.e); }
    ;

INT : DIGIT+;
ID : LETTER;
fragment LETTER : [a-zA-Z];
fragment DIGIT : [0-9];
WS : [ \t\r\n]+ -> skip;
