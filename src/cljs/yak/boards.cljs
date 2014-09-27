(ns yak.boards
  (:require [reagent.core :as reagent :refer [atom]]))

; Yes I'm deffing stuff here, but I'm in a hurry.. will fix it later.. I promise :)
(def boards (atom (sorted-map))) ; Boards that this user can see
(def boards-counter (atom 0)) ; An index hack before we get rest and real database
(def board (atom nil)) ; Selected board

; A sort of DAO
(defn add-board [board]
  (let [id (swap! boards-counter inc)
        id-board (assoc board :id id)]
    (swap! boards assoc id id-board)))

; Some dummy stuff here
(add-board {:title "Incidents"
            :description "This board keeps track of incidents"})
(add-board {:title "Some cool project"
            :description "This is some very cool project"})


(defn show-board []
  [:div "Sorry but this is not ready yet"])

(defn show-no-board []
  [:div {:class "jumbotron"}
    [:h1 "No board?!"]
    [:p "It seems that you have not selected a board. Select a board or create a new one."]])

(defn show-introductory []
  [:div {:class "jumbotron"}
    [:h1 "No board?!"]
    [:p "You can create a new board from boards menu (when I finish implementing it :D). Now you can just surf this stub-site."]])

(defn show-kanban-board []
  (cond
    (= (count @boards) 0) [show-introductory]
    (nil? @board) [show-no-board]
    :else [show-board]))