package com.helixo.syntax.tokens;

public enum TokenType {
   NUMBER,
   HEX_NUMBER, // 0F
   WORD,
   TEXT, // ""

   //Keyword
   INPUT,
   WITHDRAW,
   WITHDRAWLN,
   IF,
   ELSE,
   WHILE,
   FOR,
   DO,
   BREAK,
   CONTINUE,
   METHOD,
   TAKE,

   PLUS, // +
   MINUS, // -
   STAR, // *
   SLASH, // - /
   EQ,
   EQEQ, // ==
   EXCL,
   EXCLEQ, // !=
   LT, // <
   LTEQ, // <=
   GT, // >
   GTEQ, // >=
   BAR,
   BARBAR,
   AMP,
   APMAMP,
   COMMA,
   RETURN,

   LPAREN, // (
   RPAREN, // )
   LBRACE, // {
   RBRACE, // }
   TWO_DOT, // :

   EOF // ;
}
