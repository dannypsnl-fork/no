grammar No;

@header{
package lang.no.parser;
import lang.no.concrete.*;
import lang.no.concrete.expr.*;
import lang.no.concrete.stmt.*;
import lang.no.core.*;
}

prog locals [
    List<TopStmt> tops = new ArrayList<>();
    ]
    : top*
    ;
top returns [TopStmt s]
    : defn { $prog::tops.add($defn.self); }
    | def { $prog::tops.add($def.self); }
    ;
def returns [VarDef self]
    : ID ':=' expr ';'
      { $self = new VarDef($ID.text, null, $expr.e); }
    | ID ':' type ':=' expr ';'
      { $self = new VarDef($ID.text, $type.t, $expr.e); }
    ;
defn returns [FnDef self]
    locals [ List<Param> params = new ArrayList<Param>() ]
    : ID '(' (param (',' param)*)? ')' ':=' expr ';'
    { $self = new FnDef($ID.text, $params, $expr.e); }
    | ID '(' (param (',' param)*)? ')' block
    { $self = new FnDef($ID.text, $params, $block::stmts); }
    ;

param : ID ':' type { $defn::params.add(new Param($ID.text, $type.t)); };
stmt returns [Stmt s]
    : 'return' expr ';'
    { $block::stmts.add(new Return($expr.e)); }
    | block
    ;
block locals [ StatementList stmts = new StatementList() ]
    : '{' stmt+ '}'
    ;
type returns [Type t]
    : ID { $t = Type.fromText($ID.text); };

expr returns [Expr e]
    : ID { $e = new Var($ID.text); }
    | INT { $e = Int.fromText($INT.text); }
    | l=expr op=('*'|'/') r=expr { $e = new Binary(Op.fromText($op.text), $l.e, $r.e); }
    | l=expr op=('+'|'-') r=expr { $e = new Binary(Op.fromText($op.text), $l.e, $r.e); }
    ;

INT : DIGIT+;
ID : LETTER (LETTER|DIGIT)*;
fragment LETTER : [a-zA-Z];
fragment DIGIT : [0-9];
WS : [ \t\r\n]+ -> skip;
