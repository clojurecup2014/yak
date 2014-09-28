(ns yak.rest
  (:require [liberator.core :refer [resource defresource]]
            [cheshire.core :refer :all]
            [yak.rest-helpers :refer [parse-json check-content-type]]
            [yak.database :as db]))

(defn list-boards-as-json []
  (let [boards (db/list-boards)]
    (generate-string boards)))

(defresource boards
  :available-media-types ["application/json"]
  :allowed-methods [:get :post]
  :known-content-type? #(check-content-type % ["application/json"])
  :malformed? #(parse-json % ::data)
  :post! #()
  :post-redirect? true
  :handle-ok #(list-boards-as-json))

(defresource board [board_id]
  :available-media-types ["text/plain"]
  :handle-ok (fn [_] (format "Echo %s" board_id)))