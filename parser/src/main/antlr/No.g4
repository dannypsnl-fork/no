grammar No;

@header{
package lang.no.parser;
import lang.no.concrete.*;
import lang.no.core.expr.*;
import lang.no.concrete.stmt.*;
import lang.no.core.*;
}

prog locals [
    List<TopStmt> tops = new ArrayList<>();
    ]
    : top*
    ;
top returns [TopStmt s]
    : using { $prog::tops.add($using.self); }
    | defn { $prog::tops.add($defn.self); }
    | def { $prog::tops.add($def.self); }
    | expr { $prog::tops.add($expr.e); }
    ;

using returns [Using self]
    : 'using' ID ';'
      { $self = new Using($ID.text); }
    ;
def returns [VarDef self]
    : ID ':=' expr ';'
      { $self = new VarDef(new Loc($start, $stop), $ID.text, null, $expr.e); }
    | ID ':' type ':=' expr ';'
      { $self = new VarDef(new Loc($start, $stop), $ID.text, $type.t, $expr.e); }
    ;
defn returns [FnDef self]
    locals [ List<Param> params = new ArrayList<Param>() ]
    : ID '(' (param (',' param)*)? ')' ':=' expr ';'
      { $self = new FnDef($ID.text, $params, null, $expr.e); }
    | ID '(' (param (',' param)*)? ')' ':' type block
      { $self = new FnDef($ID.text, $params, $type.t, $block.self); }
    ;

param : ID ':' type { $defn::params.add(new Param($ID.text, $type.t)); };
block returns [Body self]
    locals [ Block stmts = new Block(new ArrayList()) ]
    : '{' stmt+ '}' { $self = $stmts; }
    ;
stmt returns [Stmt s]
    : 'return' expr ';'
    { $block::stmts.add(new Return($expr.e)); }
    | def { $block::stmts.add($def.self); }
    | expr { $block::stmts.add($expr.e); }
    | block { $block::stmts.add($block.self); }
    ;

type returns [Type t]
    : ID { $t = Type.fromText($ID.text); };

expr returns [Expr e]
    : ID { $e = new Var($ID.text); }
    | INT { $e = Int.fromText($INT.text); }
    | STRING { $e = new Str($STRING.text); }
    // function call
    | expr '(' (expr (',' expr)*)? ')'
      { $e = new FnCall(_localctx.expr().stream().map(ctx -> ctx.e).toList()); }
    | l=expr op=('*'|'/') r=expr { $e = new Binary(Op.fromText($op.text), $l.e, $r.e); }
    | l=expr op=('+'|'-') r=expr { $e = new Binary(Op.fromText($op.text), $l.e, $r.e); }
    ;

INT : DIGIT+;
STRING : '"' .*? '"';
ID : LETTER (LETTER|DIGIT)*;
fragment LETTER : [a-zA-Z];
fragment DIGIT : [0-9];
WS : [ \t\r\n]+ -> skip;
