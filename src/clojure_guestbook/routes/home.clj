(ns clojure-guestbook.routes.home
  (:require [compojure.core :refer :all]
            [clojure-guestbook.views.layout :as layout]
            [hiccup.form :refer :all]))

(defn save-message [name message]
  (cond
     (empty? name)
     (home name message "Come On Ya Big Dummy - Enter Your Name Fool")

     (empty? message)
     (home name message "What Good Are You - Enter a Message Fool")

     :else
     (do
       (println name message)
       (home))))

(defn show-guests []
  [:ul.guests
   (for [{:keys [message name timestamp]}
         [{:message "Howdy" :name "Bob" :timestamp nil}
          {:message "Hi" :name "Bob" :timestamp nil}]]
     [:li
      [:blockquote message]
      [:p "-" [:cite name]]
      [:time timestamp]])])


(defn home [& [name message error]]
  (layout/common
   [:h1 "Guestbook"]
   [:p "Leave a message"]
   [:p error]

   (show-guests)
   [:hr]

   (form-to [:post "/"]
     [:p "Name:"]
     (text-field "name" name)
     [:p "Message:"]
     (text-area {:rows 10 :cols 40} "message" message)
     [:br]
     (submit-button "comment"))))


(defroutes home-routes
  (GET "/" [] (home))
  (POST "/" [name message] (save-message name message)))
