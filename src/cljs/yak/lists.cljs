(ns yak.lists
  (:require [reagent.core :as reagent :refer [atom]]
            [reagent-bootstrap :as b]
            [yak.cards :refer [cards-view]]))

(defn predefined-lists []
  ; Returns a default set of lists to be used
  (sorted-map 1 {:name "ToDo" :description "Things that should be done." :cards []},
              2 {:name "Doing" :description "Things that are in progress." :cards []},
              3 {:name "Done" :description "Things that are ready." :cards []}))

(defn list-view [list-input]
  [b/panel (:name list-input) [cards-view list-input] :primary])

(defn select-width [board]
  (let [length (count (:lists board))]
    (cond
      (< length 3) "col-md-6"
      (< length 4) "col-md-4"
      (< length 5) "col-md-3"
      (< length 7) "col-md-2")))

(defn lists-view [board]
  (let [col-class (select-width board)]
    [:div {:class "row"}
      (for [a-list (vals (:lists board))] ; Do not worry about list order for now
        [:div {:class col-class}
          [list-view a-list]])]))