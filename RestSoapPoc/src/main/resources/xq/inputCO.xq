xquery version "3.0" encoding "UTF-8";
import module namespace mmm = "http://oce.att.com/OCE/Namespaces/xquery/oce-schema-validations" at "/oce-schema-validations2.xq";

return
  <results timestamp="{current-dateTime()}">
     <message>{$mmm:validateOrder()}</message>
  </results>