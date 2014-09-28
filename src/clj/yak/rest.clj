(ns yak.rest
  (:require [liberator.core :refer [resource defresource]]))

(defresource board [board_id]
  :available-media-types ["text/plain"]
  :handle-ok (fn [_] (format "The text is %s" board_id)))