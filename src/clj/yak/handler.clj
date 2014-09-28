(ns yak.handler
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [liberator.core :refer [resource defresource]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.util.response :refer [redirect]]
            [yak.rest :refer [board boards]]))

(defroutes app-routes
  ; Rest URL:s (din't have time to make a proper backend)
  (ANY "/rest/board/:board_id" [board_id] (board board_id))
  (ANY "/rest/board" [] (boards "foo"))

  ; Static resources
  (GET "/" [] (redirect "/index.html"))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
