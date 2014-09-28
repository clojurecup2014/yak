(ns yak.database
  (:require [clojure.java.jdbc :as j]))

(let [db-host "localhost"
      db-port 5432
      db-name "yak"]

  (def db {:classname "org.postgresql.Driver" ; must be in classpath
           :subprotocol "postgresql"
           :subname (str "//" db-host ":" db-port "/" db-name)
           ; Any additional keys are passed to the driver
           ; as driver-specific properties.
           :user "yak"
           :password "yak"}))

(defn insert-board [board]
  (j/insert! db :BOARD
    board))

(defn list-boards []
  (j/query db
    ["SELECT * FROM BOARD"]))