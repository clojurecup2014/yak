(ns yak.cards
  (:require [reagent.core :as reagent :refer [atom]]
            [reagent-bootstrap :as b]))

(defn edit-card-form [card]
  [:div
   [:input {:class "form-control" :type "text" :value (:title @card)
            :on-change #(swap! card assoc :title (-> % .-target .-value))
            :placeholder "Type your card name here.."}]
   [:textarea {:class "form-control" :rows 3
               :placeholder "Describe your card freely.."
               :on-change #(swap! card assoc :description (-> % .-target .-value))}
    (:description @card)]])

(defn show-create-card-dialog [cards]
  (let [new-card (atom {:title ""
                         :description ""})]
    (b/open-modal
     [b/modal
      "Create card" ; Title
      [edit-card-form new-card] ; Body
      [:div [b/modal-close-button "Close"] ; Footer
       [b/primary-button
        {:on-click #(swap! cards conj @new-card)
         :data-dismiss "modal"} "Create"]]])))

(defn card-as-list-item [card]
  [b/clickable-list-group-item
    [:span {:on-click #(.log js/console "card clicked")} (:title card)]
      (:description card)])

;(defn cards-view [cards-input]
;  (let [cards (atom cards-input)]
;    [b/list-group
;      (for [card @cards]
;        [b/clickable-list-group-item
;          [:span {:on-click #(.log js/console "card clicked")} "A card"]
;          "Short text ..."])
;      (b/clickable-list-group-item
;        [:span {:on-click #(show-create-card-dialog cards)} "Add new card"])]))