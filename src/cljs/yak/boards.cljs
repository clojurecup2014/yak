(ns yak.boards
  (:require
    [reagent.core :as reagent :refer [atom]]
    [dommy.utils :as utils]
    [dommy.core :as dommy]
    [reagent-bootstrap :as b]
    [yak.state :as db]
    [yak.lists :refer [lists-view predefined-lists]])
  (:use-macros
    [dommy.macros :only [sel1]]))

(defn- swap-privacy! [board]
  (let [personal-active (sel1 [".active" :#personal])]
    ; Notice, that we are checking "past" state which will
    ; change just in a moment, so we swap to the future
    (if (not (nil? personal-active))
      (swap! board assoc :privacy :member)
      (swap! board assoc :privacy :personal))))

(defn edit-board-form [board]
  [:div
   [:input {:class "form-control" :type "text" :value (:title @board)
            :on-change #(swap! board assoc :title (-> % .-target .-value))
            :placeholder "Type your board name here.."}]
   [:textarea {:class "form-control" :rows 3
               :placeholder "Describe your board freely.."
               :on-change #(swap! board assoc :description (-> % .-target .-value))}
    (:description @board)]
   [:div {:class "btn-group" :data-toggle "buttons" :on-click #(swap-privacy! board)}
     [:label {:class "btn btn-primary active"}
       [:input {:type "radio" :name "options" :id "personal" :checked "checked"}] "Personal board"]
     [:label {:class "btn btn-primary"}
       [:input {:type "radio" :name "options" :id "member"}] "Team board"]]])

(defn show-create-board-dialog []
  (let [new-board (atom {:title ""
                         :description ""
                         :privacy :personal
                         :selected false
                         :lists (predefined-lists)})]
    (b/open-modal
     [b/modal
      "Create board" ; Title
      [edit-board-form new-board] ; Body
      [:div [b/modal-close-button "Close"] ; Footer
       [b/primary-button
        {:on-click #(db/add-board! @new-board)
         :data-dismiss "modal"} "Create"]]])))

(defn board-view []
  (let [board (db/get-selected-board)]
    [:div
      [:h3 (:title board)]
      [lists-view board]]))

(defn no-board-view []
  [:div {:class "jumbotron"}
    [:h1 "No board?!"]
    [:p "It seems that you have not selected a board. Select a board."]])

(defn introductory-view []
  [:div {:class "jumbotron"}
    [:h1 "No board?!"]
    [:p "You can create a new board from boards menu."]])

(defn boards-view []
  (cond
    (= (count @db/boards) 0) [introductory-view]
    (nil? (db/get-selected-board)) [no-board-view]
    :else [board-view]))

(defn board-dropdown-item [board]
  [:li {:on-click #(db/swap-board! (:id board))}
    [:a {:href "#"} (:title board)]])

(defn boards-dropdown []
  [:li {:class "dropdown"}
    [:a {:href "#" :data-toggle "dropdown"} "Boards" [:b {:class "caret"}]]
    [:ul {:class "dropdown-menu"}
      [:li {:on-click #(show-create-board-dialog)}
        [:a {:href "#"} "Add new"]]
      [:li {:class "divider"}]
      (for [board (filter #(= (:privacy %) :member) (map second @db/boards))]
        [board-dropdown-item board])
      [:li {:class "dropdown-header"} "Personal boards"]
      (for [board (filter #(= (:privacy %) :personal) (map second @db/boards))]
        [board-dropdown-item board])]])
