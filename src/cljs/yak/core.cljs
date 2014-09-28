(ns yak.core
  (:require [reagent.core :as reagent :refer [atom]]
            [yak.boards :refer [boards-view boards-dropdown]]))

(defn by-id [id]
  (. js/document (getElementById id)))

(defn ^:export run []
  (reagent/render-component [boards-view] (by-id "kanban-board"))
  (reagent/render-component [boards-dropdown] (by-id "boards-dropdown")))