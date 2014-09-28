(ns yak.state
  (:require [reagent.core :as reagent :refer [atom]]))

; Global state is found here :|

; ATOMS

(def boards (atom (sorted-map))) ; Boards that this user can see
(def boards-counter (atom 0)) ; An index hack before we get rest and real database
(def cards-counter (atom 0))

; DAO

(defn get-selected-board []
  (first (filter :selected (map second @boards))))

(defn swap-board! [id]
  (let [current (first (filter :selected (map second @boards)))
        selected (first (filter #(= (:id %) id) (map second @boards)))]
    (if (not (nil? current))
      (swap! boards assoc-in [(:id current) :selected] false))
    (if (not (nil? selected))
      (swap! boards assoc-in [(:id selected) :selected] true))))

(defn add-board! [board]
  (let [id (swap! boards-counter inc)
        id-board (assoc board :id id)] ; add new id to board object also
    (swap! boards assoc id id-board)
    (swap-board! id))) ; Autoselect new board

(defn add-card!
  ([list-id card]
    (let [board-id (:id (get-selected-board))]
      (add-card! board-id list-id card)))
  ([board-id list-id card]
    (let [card-id (swap! cards-counter inc)]
      (swap! boards assoc-in [board-id :lists list-id :cards card-id] card))))


