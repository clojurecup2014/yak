(ns yak.cards
  (:require [reagent.core :as reagent :refer [atom]]
            [reagent-bootstrap :as b]
            [yak.state :as db]))

(defn edit-card-form [card]
  [:div
   [:input {:class "form-control" :type "text" :value (:title @card)
            :on-change #(swap! card assoc :title (-> % .-target .-value))
            :placeholder "Type your card name here.."}]
   [:textarea {:class "form-control" :rows 3
               :placeholder "Describe your card freely.."
               :on-change #(swap! card assoc :description (-> % .-target .-value))}
    (:description @card)]])

(defn show-create-card-dialog [list-id]
  (let [new-card (atom {:title ""
                         :description ""})]
    (b/open-modal
     [b/modal
      "Create card" ; Title
      [edit-card-form new-card] ; Body
      [:div [b/modal-close-button "Close"] ; Footer
       [b/primary-button
        {:on-click #(db/add-card! list-id @new-card)
         :data-dismiss "modal"} "Create"]]])))

(defn card-as-list-item [card board]
  [b/clickable-list-group-item
    [:span {:on-click #(.log js/console "card clicked")} (:title card)]
      (:description card)])