(ns clojure-guestbook.routes.home
  (:require [compojure.core :refer :all]
            [clojure-guestbook.views.layout :as layout]
            [hiccup.form :refer :all]))

(defn show-guest []
  [:ul.guests
   (for [{:keys [message name timestamp]}
         [{:message "Howdy" :name "Bob" :timestamp nil}
          {message "Hi" :name "Bob" :timestamp nil}]]
     [:li
      [:blockquote message]
      [:p "-" [:cite name]]
      [:time timestammp]])])


(defn home []
  (layout/common
   [:h1 "Guestbook"]
   [:p "Leave a message"]
   [:hr]
   [:form
     [:p "Name:"]
     [:input]
     [:p "Message:"]
     [:textarea {:rows 10 :cols 40}]]))

(defroutes home-routes
  (GET "/" [] (home)))
