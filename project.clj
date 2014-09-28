(defproject yak "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://yak.clojurecup.com"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2311"]
                 [prismatic/dommy "0.1.3"]
                 [reagent "0.4.2"]
                 [compojure "1.1.8"]
                 [org.clojure/java.jdbc "0.3.5"]
                 [liberator "0.10.0"]
                 [postgresql "9.3-1102.jdbc41"]
                 [reagent-bootstrap/reagent-bootstrap "0.1.0-SNAPSHOT"]]
  :plugins [[lein-ring "0.8.11"]
            [lein-cljsbuild "1.0.4-SNAPSHOT"]]
  :source-paths ["src/clj"]
  :cljsbuild {
    :builds [{:id "yak"
              :source-paths ["src/cljs"]
              :compiler {
                :output-to "resources/public/js/yak.js"
                :output-dir "resources/public/out"
                :optimizations :none
                :source-map true}}]}
  :ring {:handler yak.handler/app
         :port 3000}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
