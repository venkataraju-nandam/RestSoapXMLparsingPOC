xquery version "3.0" encoding "utf-8";
module namespace m = 'http://basex.org/modules/Hello';
declare function m:hello($world) {
  'Hello111 ' || $world
};