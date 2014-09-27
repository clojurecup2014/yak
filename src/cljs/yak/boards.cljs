(ns yak.boards
  (:require
    [reagent.core :as reagent :refer [atom]]
    [dommy.utils :as utils]
    [dommy.core :as dommy]
    [reagent-bootstrap :as b])
  (:use-macros
    [dommy.macros :only [sel1]]))

; Yes I'm deffing stuff here, but I'm in a hurry.. will fix it later.. I promise :)
(def boards (atom (sorted-map))) ; Boards that this user can see
(def boards-counter (atom 0)) ; An index hack before we get rest and real database

; A sort of DAO
(defn get-selected-board []
  (first (filter :selected (map second @boards))))

(defn- create-board! [board]
  (let [id (swap! boards-counter inc)
        id-board (assoc board :id id)]
    (swap! boards assoc id id-board)))

(defn- swap-privacy! [board]
  (let [personal-active (sel1 [".active" :#personal])]
    ; Notice, that we are checking "past" state which will
    ; change just in a moment, so we swap to the future
    (if (not (nil? personal-active))
      (swap! board assoc :privacy :member)
      (swap! board assoc :privacy :personal))))

(defn- swap-board! [id]
  (let [current (first (filter :selected (map second @boards)))
        selected (first (filter #(= (:id %) id) (map second @boards)))]
    (if (not (nil? current))
      (swap! boards assoc-in [(:id current) :selected] false))
    (if (not (nil? selected))
      (swap! boards assoc-in [(:id selected) :selected] true))))

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
                         :selected false})]
    (b/open-modal
     [b/modal
      "Create board" ; Title
      [edit-board-form new-board] ; Body
      [:div [b/modal-close-button "Close"] ; Footer
       [b/primary-button
        {:on-click #(create-board! @new-board)
         :data-dismiss "modal"} "Create"]]])))

(defn show-board []
  (let [selected (get-selected-board)]
    [:div
      [:h3 (:title selected)]
      [b/panel "Create new list" ""]]))

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
    (nil? (get-selected-board)) [show-no-board]
    :else [show-board]))

(defn board-dropdown-item [board]
  [:li {:on-click #(swap-board! (:id board))}
    [:a {:href "#"} (:title board)]])

(defn boards-dropdown []
  [:li {:class "dropdown"}
    [:a {:href "#" :data-toggle "dropdown"} "Boards" [:b {:class "caret"}]]
    [:ul {:class "dropdown-menu"}
      [:li {:on-click #(show-create-board-dialog)}
        [:a {:href "#"} "Add new"]]
      [:li {:class "divider"}]
      (for [board (filter #(= (:privacy %) :member) (map second @boards))]
        [board-dropdown-item board])
      [:li {:class "dropdown-header"} "Personal boards"]
      (for [board (filter #(= (:privacy %) :personal) (map second @boards))]
        [board-dropdown-item board])]])
