(ns yak.core
  (:require [reagent.core :as reagent :refer [atom]]
            [yak.boards :refer [show-kanban-board]]))

(defn main-view []
  (show-kanban-board))

(defn ^:export run []
  (reagent/render-component [main-view] (. js/document (getElementById "app"))))