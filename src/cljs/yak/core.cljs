(ns yak.core
  (:require [reagent.core :as reagent :refer [atom]]
            [yak.boards :refer [show-kanban-board boards-dropdown]]))

(defn by-id [id]
  (. js/document (getElementById id)))

(defn ^:export run []
  (reagent/render-component [show-kanban-board] (by-id "kanban-board"))
  (reagent/render-component [boards-dropdown] (by-id "boards-dropdown")))