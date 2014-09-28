(ns yak.cards
  (:require [reagent.core :as reagent :refer [atom]]
            [reagent-bootstrap :as b]))

(defn cards-view [cards]
  [b/list-group
    (for [id card]
      [b/clickable-list-group-item
        [:span {:on-click #(.log js/console "card clicked")} "A card"]
        "Short text ..."])
    (b/clickable-list-group-item
      [:span {:on-click #(.log js/console "New card")} "Add new card"])])