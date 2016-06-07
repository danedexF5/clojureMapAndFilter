(ns purchases.core
  (:require [clojure.string :as str]
            ;[clojure.walk]
            [compojure.core :as c]
            [ring.adapter.jetty :as j]
            [hiccup.core :as h])
  (:gen-class))


(defonce server (atom nil))

(defn break-line-apart [line] (str/split line #","))

;Read the file and parse it into a vector of lines
(defn read-purchases []
  (let [purchases (slurp "purchases.csv")

        ;Split each line into vectors (delimited by commas)
        purchases (str/split-lines purchases)
        ;Split each line into vectors (delimited by commas)
        purchases (map (fn [line] (str/split line #",")) purchases)
        ;Separate the header from the rest of the lines
        headers (first purchases)
        purchases (rest purchases)
        ;Make each line a hash map that associates each item in the header with each item in the line
        purchases (map (fn [purchase]
                         (zipmap headers purchase)) purchases)] purchases))

(defn purchases-html [category]
  (let [
        purchases (read-purchases)
        purchases (if (= 0 (count category))
                    purchases
                    (filter (fn [purchase]
                              (= (get purchase "category") category))
                            purchases))]


    [:ol (map (fn [purchase] [:li (str (get purchase "customer_id") ", " (get purchase "date") ", " (get purchase "credit_card") ", " (get purchase "cvv") ", " (get purchase "category"))]) purchases)]))

(defn category-links []
  (let [
        category (set (map #(get % "category") (read-purchases)))]
    [:div (map (fn [category] [:span [:a {:href (str "/" category)} category] " "]) category)]))
        ;purchases (clojure.walk/keywordize-keys purchases)
        ;category (read-line)
        ;purchases (filter (fn [purchase] (= (get purchase :category) category))purchases)
        ;file-text (pr-str purchases)]
    ;(spit (str category ".edn") file-text)))

;Create your / route
(c/defroutes app
             (c/GET "/:category{.*}" [category]
                    ;In the / route, use hiccup to generate html that displays your purchase data
                    (h/html [:html
                             [:body
                              (category-links)
                              (purchases-html category)]])))


;Move the code in your -main function to a separate function
(defn -main []
 (when @server
   (.stop @server))
 ;run jetty in your -main function
 (reset! server (j/run-jetty app {:port 3000 :join? false})))
;(defn -main []
  ;(println "Enter a category (Furniture, Alcohol, Toiletries, Shoes, Food, Jewelry):"))




;Add links at the top to filter by category.














