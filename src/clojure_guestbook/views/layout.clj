(ns clojure-guestbook.views.layout
  (:require [hiccup.page :refer [html5 include-css]]))

(defn common [& body]
  (html5
    [:head
     [:title "Welcome to clojure-guestbook"]
     (include-css "/css/screen.css")]
    [:body body]))
