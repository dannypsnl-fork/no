grammar No;

@header{
package lang.no.parser;
import lang.no.concrete.*;
import lang.no.concrete.expr.*;
import lang.no.concrete.stmt.*;
}

prog locals [
    List<TopStmt> tops = new ArrayList<>();
    ]
    : top*
    ;
top returns [TopStmt s]
    : def { $prog::tops.add($def.self); }
    | defn { $prog::tops.add($defn.self); }
    ;
def returns [VarDef self]
    : ID ':=' expr { $self = new VarDef($ID.text, $expr.e); }
    ;
defn returns [FnDef self]
    locals [ List<Param> params = new ArrayList<Param>() ]
    : ID '(' param (',' param)* ')' ':=' expr
    { $self = new FnDef($ID.text, $params, $expr.e); }
    | ID '(' param (',' param)* ')' block
    { $self = new FnDef($ID.text, $params, $block::stmts); }
    ;

param : ID ':' type { $defn::params.add(new Param($ID.text)); };
stmt returns [Stmt s]
    : 'return' expr ';'
    { $block::stmts.add(new Return($expr.e)); }
    | block
    ;
block locals [ StatementList stmts = new StatementList() ]
     : '{' stmt+ '}'
     ;
type: ID;

expr returns [Expr e]
    : ID { $e = new Var($ID.text); }
    | INT { $e = Int.fromText($INT.text); }
    | l=expr op=('*'|'/') r=expr { $e = new Binary(Op.fromText($op.text), $l.e, $r.e); }
    | l=expr op=('+'|'-') r=expr { $e = new Binary(Op.fromText($op.text), $l.e, $r.e); }
    ;

INT : DIGIT+;
ID : LETTER;
fragment LETTER : [a-zA-Z];
fragment DIGIT : [0-9];
WS : [ \t\r\n]+ -> skip;
