(defproject purchases "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  ;Add the necessary dependencies to project.clj
  ;[ring "1.4.0"]
  ;[hiccup "1.0.5"]
  ;[compojure "1.5.0"]
  :dependencies [[org.clojure/clojure "1.8.0"][ring "1.4.0"]
                 [hiccup "1.0.5"]
                 [compojure "1.5.0"]]
  :main purchases.core)
