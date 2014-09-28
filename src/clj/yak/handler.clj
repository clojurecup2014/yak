(ns yak.handler
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [liberator.core :refer [resource defresource]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.util.response :refer [redirect]]
            [yak.rest :refer [board]]))

(defroutes app-routes
  (ANY "/rest/board/:board_id" [board_id] (board board_id))
  (GET "/" [] (redirect "/index.html"))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
