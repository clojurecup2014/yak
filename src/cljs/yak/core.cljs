(ns yak.core
    (:require [reagent.core :as reagent :refer [atom]]))

(defn main-view []
  [:div
    [:h3 "Cljs is working fine"]])

(defn ^:export run []
  (reagent/render-component [main-view] (. js/document (getElementById "app"))))