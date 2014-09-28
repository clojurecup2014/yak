(ns yak.cards
  (:require [reagent.core :as reagent :refer [atom]]
            [reagent-bootstrap :as b]))

(defn create-card [cards card]
  (swap! cards conj card))

(defn edit-card-form []
  )

(defn show-create-card-dialog [cards]
  (let [new-card (atom {:title ""
                         :description ""})]
    (b/open-modal
     [b/modal
      "Create card" ; Title
      [edit-card-form new-card] ; Body
      [:div [b/modal-close-button "Close"] ; Footer
       [b/primary-button
        {:on-click #(create-card! cards @new-card)
         :data-dismiss "modal"} "Create"]]])))

(defn cards-view [cards-input]
  (let [cards (atom cards-input)]
    [b/list-group
      (for [card @cards]
        [b/clickable-list-group-item
          [:span {:on-click #(.log js/console "card clicked")} "A card"]
          "Short text ..."])
      (b/clickable-list-group-item
        [:span {:on-click #(show-create-card-dialog cards)} "Add new card"])]))