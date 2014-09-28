(ns yak.lists
  (:require [reagent.core :as reagent :refer [atom]]
            [reagent-bootstrap :as b]))

(defn lists-view [board-input]
  (let [board (atom board-input)])
  [:div {:class "row"}
    [:div {:class "col-md-2"}
      [b/panel "Todo" "Lists goes here" :primary]]
    [:div {:class "col-md-2"}
      [b/panel "Doing" "Lists here" :primary]]
    [:div {:class "col-md-2"}
      [b/panel "Done" "List here" :primary]]
    [:div {:class "col-md-2"}
      [b/panel "Foo 1" "Lists here" :primary]]
    [:div {:class "col-md-2"}
      [b/panel "Foo 2" "Lists here" :primary]]
    [:div {:class "col-md-2"}
      [b/panel "Foo 3" "Lists here" :primary]]])